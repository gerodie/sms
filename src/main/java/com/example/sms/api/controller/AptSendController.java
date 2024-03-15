package com.example.sms.api.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sms.common.GlobalVariables;
import com.example.sms.entity.SmsAp4;
import com.example.sms.service.SmsAp4QueryService;
import com.example.sms.service.SmsAp4Service;

@RestController
@RequestMapping(path = "/api/sendsms-apt")
public class AptSendController {

  private static final Logger log = LogManager.getLogger(AptSendController.class);

  @Autowired
  private SmsAp4Service smsAp4Service;

  @Autowired
  private SmsAp4QueryService smsAp4QueryService;

  @PostMapping(path = "/wakeup")
  public ResponseEntity<?> wakeup(@RequestParam String CLIENT_ID, @RequestParam String MSG_ID) {
    List<SmsAp4> list = smsAp4Service.updateSendStatus(CLIENT_ID, MSG_ID);
    if (!CollectionUtils.isEmpty(list)) {
      for (SmsAp4 smsAp4 : list) {
        smsAp4Service.sendSms(smsAp4);
      }
      String msgId = String.valueOf(list.get(0).getMsgId());
      String clientId = smsAp4Service.getClientIdByMsgId(msgId);
      try {
        log.info("start updateBulkSendTtcStatus msgId:" + msgId);
        smsAp4Service.updateBulkSendTtcStatus(GlobalVariables.TELECOM_OPERATOR_APT, clientId,
            msgId);
      } catch (Exception e) {
        log.error("callResultCode fail msgId:" + msgId + ";" + e);
      }
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.info("querySmsResult not find data");
    }
    return null;
  }

  @Scheduled(fixedRate = 600000)
  public void querySmsResult() {
    List<SmsAp4> list = smsAp4QueryService.findAllByUdt();
    if (!CollectionUtils.isEmpty(list)) {
      for (SmsAp4 smsAp4 : list) {
        smsAp4QueryService.querySmsResult(smsAp4);
      }
    } else {
      log.info("querySmsResult not find data");
    }
  }

}
