package com.example.sms.service;

import java.util.List;
import com.example.sms.entity.SmsAp4;

public interface SmsAp4Service {

  public List<SmsAp4> updateSendStatus(String clientId, String msgId);

  public void sendSms(SmsAp4 smsCh4);

  public String getClientIdByMsgId(String msgId);

  public void updateBulkSendTtcStatus(String op, String clientId, String msgId);

}
