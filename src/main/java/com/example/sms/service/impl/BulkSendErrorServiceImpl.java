package com.example.sms.service.impl;

import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sms.common.ApiResponseState;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.BulkSendDetailsDto;
import com.example.sms.dto.BulkSendMainDto;
import com.example.sms.entity.BulkSendError;
import com.example.sms.repository.BulkSendErrorRepository;
import com.example.sms.service.BulkSendErrorService;

@Service
public class BulkSendErrorServiceImpl implements BulkSendErrorService {

  private static final Logger log = LogManager.getLogger(BulkSendErrorServiceImpl.class);

  @Autowired
  private BulkSendErrorRepository bulkSendErrorRepository;

  @Override
  public void createBulkSendError(BulkSendMainDto bulkSendMainDto, Map<String, Object> responseMap,
      int bulkSendDetailDtoIndex) {
    log.info("start createBulkSendError");
    BulkSendError bulkSendError = new BulkSendError();
    try {
      BeanUtils.copyProperties(bulkSendMainDto, bulkSendError, GlobalVariables.COMMON_PACKAGECNT,
          GlobalVariables.REQUEST_SYSTIME, GlobalVariables.REQUEST_TIMEUP,
          GlobalVariables.REQUEST_EXPIRYMINUTES, GlobalVariables.COMMON_UID,
          GlobalVariables.REQUEST_MOBILE, GlobalVariables.REQUEST_SMSTEXT);

      String ttcStatus = "";
      if (responseMap.containsKey(GlobalVariables.RESPONSE_UIDSTATUS)) {
        ttcStatus = ApiResponseState.HANDLE_EXCEPTION.getState();
        bulkSendError.setTtcErrorStatus(ttcStatus);
        bulkSendError.setuId(responseMap.get(GlobalVariables.COMMON_UID).toString());
        bulkSendError.setUidStatus(responseMap.get(GlobalVariables.RESPONSE_UIDSTATUS).toString());
        BulkSendDetailsDto bulkSendDetailsDto =
            bulkSendMainDto.getSmsBody().get(bulkSendDetailDtoIndex);
        bulkSendError.setMobile(bulkSendDetailsDto.getMobile());
        bulkSendError.setSmsText(bulkSendDetailsDto.getSmsText());
      } else {
        ttcStatus = responseMap.get(GlobalVariables.RESPONSE_TTCSTATUS).toString();
        bulkSendError.setTtcErrorStatus(ttcStatus);
      }
      bulkSendErrorRepository.saveAndFlush(bulkSendError);
    } catch (Exception e) {
      log.error(bulkSendError.toString(), e);
    }
  }

}
