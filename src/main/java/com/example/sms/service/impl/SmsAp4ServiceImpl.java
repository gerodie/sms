package com.example.sms.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.RequestDetailDto;
import com.example.sms.dto.RequestMainDto;
import com.example.sms.dto.ResponseDto;
import com.example.sms.entity.SmsAp4;
import com.example.sms.repository.SmsAp4Repository;
import com.example.sms.service.SmsAp4Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Service
public class SmsAp4ServiceImpl implements SmsAp4Service {

  private static final Logger log = LogManager.getLogger(SmsAp4ServiceImpl.class);

  @Autowired
  private SmsAp4Repository smsAp4Repository;

  @Override
  public List<SmsAp4> updateSendStatus(String clientId, String msgId) {
    log.info("start updateSendStatus");
    try {
      List<SmsAp4> list = smsAp4Repository.findAllSendStatus(clientId, msgId);
      for (SmsAp4 smsAp4 : list) {
        smsAp4.setTtcSendStatus(GlobalVariables.TTC_SEND_PROCESS);
      }
      return smsAp4Repository.saveAllAndFlush(list);
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }

  @Override
  @Async("executor")
  public void sendSms(SmsAp4 smsAp4) {
    String threadName = Thread.currentThread().getName();
    log.info(threadName + ":start sendSms");
    RequestMainDto mainDto = new RequestMainDto();
    RequestDetailDto detailDto = new RequestDetailDto();
    List<RequestDetailDto> list = new ArrayList<>();
    detailDto.setMSISDN(smsAp4.getMsisdn());
    list.add(detailDto);
    mainDto.setSubject(smsAp4.getSubject());
    mainDto.setStopDateTime(smsAp4.getStopDateTime());
    mainDto.setMessage(smsAp4.getMessage());// encode ISO-8859-1
    mainDto.setMDNList(list);
    // Content=<Request>;
    // <MDNList>

    XmlMapper xmlMapper = new XmlMapper();
    String xml = null;
    try {
      xml = xmlMapper.writeValueAsString(mainDto);
    } catch (JsonProcessingException e1) {
      log.error("convert xml failed uId:" + smsAp4.getuId(), e1);
    }
    System.out.println(xml);
    ResponseDto responseDto = new RestTemplate().postForObject(GlobalVariables.CALL_APT_API, null,
        ResponseDto.class, xml);

    smsAp4.setRtnDateTime(responseDto.getRtnDateTime());
    smsAp4.setTaskId(responseDto.getTaskID());
    smsAp4.setCode(responseDto.getCode());
    smsAp4.setReason(responseDto.getReason());
    smsAp4.setTtcSendStatus("");
    smsAp4Repository.saveAndFlush(smsAp4);
  }

  @Override
  public String getClientIdByMsgId(String msgId) {
    log.info("start getClientIdByMsgId");
    return smsAp4Repository.getClientIdByMsgId(msgId);
  }

  @Override
  public void updateBulkSendTtcStatus(String op, String clientId, String msgId) {
    log.info("start updateBulkSendTtcStatus");
    smsAp4Repository.updateBulkSendTtcStatus(op, clientId, msgId);
  }

}
