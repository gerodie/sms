package com.example.sms.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.example.sms.common.GlobalVariables;
import com.example.sms.common.IConstants;
import com.example.sms.dto.RecvDataModel;
import com.example.sms.dto.SubmitDataModel;
import com.example.sms.entity.SmsCh4;
import com.example.sms.repository.SmsCh4Repository;
import com.example.sms.service.SmsCh4Service;

@Service
public class SmsCh4ServiceImpl implements SmsCh4Service {

  private static final Logger log = LogManager.getLogger(SmsCh4ServiceImpl.class);

  private SnsClient_V1 sns = null;

  @Autowired
  private SmsCh4Repository smsCh4Repository;

  @Override
  public List<SmsCh4> updateSendStatus(String clientId, String msgId) {
    log.info("start updateSendStatus");
    try {
      List<SmsCh4> list = smsCh4Repository.findAllSendStatus(clientId, msgId);
      for (SmsCh4 smsCh4 : list) {
        smsCh4.setTtcSendStatus(GlobalVariables.TTC_SEND_PROCESS);
        smsCh4.setTtcSendTime(new Date());
      }
      return smsCh4Repository.saveAllAndFlush(list);
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }

  @Override
  @Async("executor")
  public void sendSms(SmsCh4 smsCh4) {
    String threadName = Thread.currentThread().getName();
    log.info(threadName + ":start sendSms");

    sns = new SnsClient_V1(GlobalVariables.SOCKET_IP, GlobalVariables.SOCKET_PORT);
    SubmitDataModel sdm = this.loginSnsServer(sns);

    byte[] message = smsCh4.getMessage().getBytes(StandardCharsets.UTF_16BE);
    sdm.reset();
    sdm.setType(IConstants.SERV_SUBMIT_MSG);
    sdm.setTranType(IConstants.DEFAULT_TRAN_TYPE);
    sdm.setCoding(Byte.parseByte(smsCh4.getCoding()));
    sdm.setRcvMsisdn(smsCh4.getRcvMsisdn());
    sdm.setMessage(message);
    sdm.setLength((byte) message.length);
    sdm.setExpire(Byte.parseByte(smsCh4.getExpiryHours()),
        Byte.parseByte(smsCh4.getExpiryMinutes()));

    log.info("start submitMessage,uId:" + smsCh4.getuId());
    RecvDataModel rdm = sns.submitMessage(sdm);

    log.info("save RecvDataModel");
    String messageId = null;
    String resCode = null;
    String buffer = null;
    String ttcSendStatus = null;
    try {
      if (rdm != null) {
        log.info(rdm.toString());
        String code = String.valueOf(rdm.getCode());
        String desc = new String(rdm.getDesc(), StandardCharsets.UTF_8).trim();
        messageId = "0".equals(code) ? desc : null;
        resCode = code;
        buffer = desc;
        ttcSendStatus =
            "0".equals(code) ? GlobalVariables.TTC_SEND_SUCCESS : GlobalVariables.TTC_SEND_FAIL;
      } else {
        log.error("Submit fail:" + sns.getLastMessage());
        ttcSendStatus = GlobalVariables.TTC_SEND_FAIL;
      }
    } catch (Exception e) {
      log.error(e);
      ttcSendStatus = GlobalVariables.TTC_SEND_ERROR;
    }
    log.info("start updatePhaseIResponse");
    smsCh4Repository.updatePhaseIResponse(messageId, resCode, buffer, ttcSendStatus,
        smsCh4.getClientId(), String.valueOf(smsCh4.getMsgId()), smsCh4.getuId());
    this.logoutSnsServer(sdm);
  }

  @Override
  public SubmitDataModel loginSnsServer(SnsClient_V1 sns) {
    log.info("start loginSnsServer");
    SubmitDataModel sdm = new SubmitDataModel();
    sdm.setType(IConstants.SERV_LOGIN);
    sdm.setAccount(GlobalVariables.SERVER_ACCOUNT);
    sdm.setPassword(GlobalVariables.SERVER_PASSWORD);
    if (sns.login(sdm) == false) {
      log.error("login server fail(" + sns.getLastMessage() + ")");
      sns.logout();
      sdm = null;
      return sdm;
    }
    return sdm;
  }

  @Override
  public void logoutSnsServer(SubmitDataModel sdm) {
    log.info("start logoutSnsServer");
    sns.logout();
    sdm = null;
  }

  @Override
  public String getClientIdByMsgId(String msgId) {
    log.info("start getClientIdByMsgId");
    return smsCh4Repository.getClientIdByMsgId(msgId);
  }

  @Override
  public void updateBulkSendTtcStatus(String op, String clientId, String msgId) {
    log.info("start updateBulkSendTtcStatus");
    smsCh4Repository.updateBulkSendTtcStatus(op, clientId, msgId);
    smsCh4Repository.flush();
  }

  @Override
  public List<SmsCh4> findStayQuerySmsResult() {
    log.info("start findStayQuerySmsResult");
    return smsCh4Repository.findStayQuerySmsResult();
  }

}
