package com.example.sms.service.impl;

import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sms.common.GlobalVariables;
import com.example.sms.common.IConstants;
import com.example.sms.dto.RecvDataModel;
import com.example.sms.dto.SubmitDataModel;
import com.example.sms.entity.SmsCh4;
import com.example.sms.entity.SmsCh4Query;
import com.example.sms.repository.SmsCh4QueryRepository;
import com.example.sms.service.SmsCh4QueryService;
import com.example.sms.service.SmsCh4Service;

@Service
public class SmsCh4QueryServiceImpl implements SmsCh4QueryService {

  private static final Logger log = LogManager.getLogger(SmsCh4QueryServiceImpl.class);

  private SnsClient_V1 sns = null;

  @Autowired
  private SmsCh4QueryRepository smsCh4QueryRepository;

  @Autowired
  private SmsCh4Service smsCh4Service;

  @Override
  public void querySmsResult(SmsCh4 smsCh4) {
    log.info("start querySmsResult");
    String messageId = smsCh4.getMessageId();

    sns = new SnsClient_V1(GlobalVariables.SOCKET_IP, GlobalVariables.SOCKET_PORT);
    SubmitDataModel sdm = smsCh4Service.loginSnsServer(sns);
    sdm.reset();
    sdm.setType(IConstants.SERV_QUERY_STATE);
    sdm.setRcvMsisdn(smsCh4.getRcvMsisdn());
    sdm.setMessageID(messageId);

    log.info("start qryMessageStatus,messageId:" + messageId);
    RecvDataModel rdm = sns.qryMessageStatus(sdm);
    try {
      if (rdm != null) {
        log.info(rdm.toString());
        SmsCh4Query smsCh4Query = new SmsCh4Query();
        smsCh4Query.setMessageId(messageId);
        smsCh4Query.setCode(String.valueOf(rdm.getCode()));
        smsCh4Query.setBuffer(new String(rdm.getDesc(), StandardCharsets.UTF_8).trim());
        smsCh4QueryRepository.saveAndFlush(smsCh4Query);
      } else {
        log.error("query fail:" + sns.getLastMessage());
      }
    } catch (Exception e) {
      log.error(e);
    }
    smsCh4Service.logoutSnsServer(sdm);
  }

}
