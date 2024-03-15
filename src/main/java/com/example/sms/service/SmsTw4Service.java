package com.example.sms.service;

import java.util.List;
import com.example.sms.entity.SmsTw4;

public interface SmsTw4Service {

  public List<SmsTw4> findAllBySendStatus(String clientId, String msgId);

  public List<SmsTw4> updateSendStatus(List<SmsTw4> smsTw4List);

  public void sendSms(List<SmsTw4> smsTw4List);

}
