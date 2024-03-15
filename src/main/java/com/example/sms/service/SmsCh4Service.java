package com.example.sms.service;

import java.util.List;
import com.example.sms.dto.SubmitDataModel;
import com.example.sms.entity.SmsCh4;
import com.example.sms.service.impl.SnsClient_V1;

public interface SmsCh4Service {

  public List<SmsCh4> updateSendStatus(String clientId, String msgId);

  public void sendSms(SmsCh4 smsCh4);

  public SubmitDataModel loginSnsServer(SnsClient_V1 sns);

  public void logoutSnsServer(SubmitDataModel sdm);

  public String getClientIdByMsgId(String msgId);

  public void updateBulkSendTtcStatus(String op, String clientId, String msgId);

  public List<SmsCh4> findStayQuerySmsResult();

}
