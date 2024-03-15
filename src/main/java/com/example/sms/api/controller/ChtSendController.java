package com.example.sms.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
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
import com.example.sms.entity.SmsCh4;
import com.example.sms.service.SmsCh4QueryService;
import com.example.sms.service.SmsCh4Service;

@RestController
@RequestMapping(path = "/api/sendsms-cht")
public class ChtSendController {

  private static final Logger log = LogManager.getLogger(ChtSendController.class);

  @Autowired
  private SmsCh4Service smsCh4Service;

  @Autowired
  private SmsCh4QueryService smsCh4QueryService;

  @PostMapping(path = "/wakeup")
  public ResponseEntity<?> wakeup(@RequestParam String CLIENT_ID, @RequestParam String MSG_ID) {
    log.info("start wakeup");
    List<SmsCh4> list = smsCh4Service.updateSendStatus(CLIENT_ID, MSG_ID);

    log.info("start smsSend");
    if (!CollectionUtils.isEmpty(list)) {
      List<CompletableFuture<Void>> futures = new ArrayList<>();
      for (SmsCh4 smsCh4 : list) {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
          smsCh4Service.sendSms(smsCh4);
        });
        futures.add(future);
      }

      CompletableFuture<Void> allOfFuture =
          CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
      allOfFuture.thenRun(() -> {
        String msgId = String.valueOf(list.get(0).getMsgId());
        String clientId = smsCh4Service.getClientIdByMsgId(msgId);
        try {
          log.info("start updateBulkSendTtcStatus msgId:" + msgId);
          smsCh4Service.updateBulkSendTtcStatus(GlobalVariables.TELECOM_OPERATOR_CHT, clientId,
              msgId);
        } catch (Exception e) {
          log.error("callResultCode fail msgId:" + msgId + ";" + e);
        }
      });
      return new ResponseEntity<>(HttpStatus.OK);
    } else {
      log.info("updateSendStatus not find data");
    }
    return null;
  }

  @Scheduled(fixedRate = 600000)
  public void querySmsResult() {
    List<SmsCh4> list = smsCh4Service.findStayQuerySmsResult();
    if (!CollectionUtils.isEmpty(list)) {
      for (SmsCh4 smsCh4 : list) {
        smsCh4QueryService.querySmsResult(smsCh4);
      }
    } else {
      log.info("querySmsResult not find data");
    }
  }

}
