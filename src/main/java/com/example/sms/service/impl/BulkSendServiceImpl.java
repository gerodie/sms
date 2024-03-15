package com.example.sms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.sms.common.ApiResponseState;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.BulkSendDetailsDto;
import com.example.sms.dto.BulkSendMainDto;
import com.example.sms.entity.BulkSend;
import com.example.sms.repository.BulkSendRepository;
import com.example.sms.service.BulkSendErrorService;
import com.example.sms.service.BulkSendService;

@Service
public class BulkSendServiceImpl implements BulkSendService {

  private static final Logger log = LogManager.getLogger(BulkSendServiceImpl.class);

  @Autowired
  private BulkSendRepository bulkSendRepository;

  @Autowired
  private BulkSendErrorService bulkSendErrorService;

  @Override
  public void createBulkSend(BulkSendMainDto bulkSendMainDto,
      BulkSendDetailsDto bulkSendDetailsDto) {
    log.info("start createBulkSend");
    BulkSend bulkSend = new BulkSend();
    try {
      BeanUtils.copyProperties(bulkSendMainDto, bulkSend, GlobalVariables.COMMON_UID,
          GlobalVariables.REQUEST_MOBILE, GlobalVariables.REQUEST_SMSTEXT,
          GlobalVariables.ENTITY_OPID, GlobalVariables.RESPONSE_TTCSTATUS);

      BeanUtils.copyProperties(bulkSendDetailsDto, bulkSend, GlobalVariables.REQUEST_CLIENTID,
          GlobalVariables.COMMON_MSGID, GlobalVariables.REQUEST_SYSTIME,
          GlobalVariables.REQUEST_TIMEUP, GlobalVariables.REQUEST_EXPIRYMINUTES,
          GlobalVariables.ENTITY_OPID, GlobalVariables.RESPONSE_TTCSTATUS);
      bulkSend.setOperatorId(this.getOperatorId(bulkSendDetailsDto.getMobile()));
      bulkSend.setTtcStatus(GlobalVariables.RECEIVE_TRADEVAN_SUCCESS);
      bulkSendRepository.saveAndFlush(bulkSend);
    } catch (Exception e) {
      log.error(bulkSendMainDto.toString(), bulkSend.toString(), e);
    }
  }

  @Override
  public String getOperatorId(String phoneNumber) {
    log.info("start getOperatorId");
    String operatorId = GlobalVariables.DB_RT_RETURNMSG;
    try {
      operatorId = bulkSendRepository.getOperatorId(phoneNumber);
      if (ObjectUtils.isEmpty(operatorId)) {
        operatorId = GlobalVariables.DB_RT_RETURNMSG;
      }
    } catch (Exception e) {
      log.error(e);
    }
    return operatorId;
  }

  @Override
  @Async("executor")
  public List<Map<String, Object>> validateBulkSendDto(BulkSendMainDto bulkSendMainDto) {
    String threadName = Thread.currentThread().getName();
    log.info(threadName + ":start validateBulkSendDto");

    List<Map<String, Object>> dataList = new ArrayList<>(500);
    Map<String, Object> dataMap = null;
    int index = 0;

    try {
      // check smsBody
      for (BulkSendDetailsDto bulkSendDetailsDto : bulkSendMainDto.getSmsBody()) {
        String uId = bulkSendDetailsDto.getuId();
        String mobile = bulkSendDetailsDto.getMobile();
        String smsText = bulkSendDetailsDto.getSmsText();
        log.info("validate data,uId:" + uId + ",mobile:" + mobile + ",smsText:" + smsText);
        log.info("smsText string count:" + smsText.length());
        log.info("smsText byte count:" + smsText.getBytes().length);

        int count = bulkSendRepository.checkuIdExist(uId);
        if (count != 0) {
          dataMap = new HashMap<>(3);
          dataMap.put(GlobalVariables.COMMON_UID, uId);
          dataMap.put(GlobalVariables.RESPONSE_UIDSTATUS,
              ApiResponseState.UID_DUPLICATE.getState());
          dataMap.put(GlobalVariables.COMMON_STATUSMSG,
              ApiResponseState.UID_DUPLICATE.getDescription());
          dataList.add(dataMap);
          bulkSendErrorService.createBulkSendError(bulkSendMainDto, dataMap, index);
        } else {
          if (ObjectUtils.isEmpty(mobile) || ObjectUtils.isEmpty(smsText)) {
            dataMap = new HashMap<>(3);
            dataMap.put(GlobalVariables.COMMON_UID, uId);
            dataMap.put(GlobalVariables.RESPONSE_UIDSTATUS, ApiResponseState.DATA_NULL.getState());
            dataMap.put(GlobalVariables.COMMON_STATUSMSG,
                ApiResponseState.DATA_NULL.getDescription());
            dataList.add(dataMap);
            bulkSendErrorService.createBulkSendError(bulkSendMainDto, dataMap, index);
          } else if (mobile.length() != 10 || smsText.length() > 70) {
            dataMap = new HashMap<>(3);
            dataMap.put(GlobalVariables.COMMON_UID, uId);
            dataMap.put(GlobalVariables.RESPONSE_UIDSTATUS,
                ApiResponseState.DATA_LENGTH.getState());
            dataMap.put(GlobalVariables.COMMON_STATUSMSG,
                ApiResponseState.DATA_LENGTH.getDescription());
            dataList.add(dataMap);
            bulkSendErrorService.createBulkSendError(bulkSendMainDto, dataMap, index);
          } else {
            this.createBulkSend(bulkSendMainDto, bulkSendDetailsDto);
          }
        }
        index++;
      }
    } catch (Exception e) {
      log.error(bulkSendMainDto.getSmsBody().get(index), e);
    }

    log.info("start bulkSendDispatchForOperator");
    String clientId = bulkSendMainDto.getClientId();
    String msgId = bulkSendMainDto.getMsgId();
    try {
      bulkSendRepository.bulkSendDispatchForOperator(clientId, msgId);
    } catch (Exception e) {
      log.error("dispatch for operator error", e);
    }
    return dataList;
  }

  @Override
  public boolean checkMsgIdExist(String msgId) {
    log.info("start checkMsgIdExist");
    int count = 0;
    try {
      count = bulkSendRepository.checkMsgIdExist(msgId);
    } catch (Exception e) {
      log.error(e);
    }
    return count != 0;
  }

}
