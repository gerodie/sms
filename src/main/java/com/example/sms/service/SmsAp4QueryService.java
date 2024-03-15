package com.example.sms.service;

import java.util.List;
import com.example.sms.entity.SmsAp4;

public interface SmsAp4QueryService {

  public void querySmsResult(SmsAp4 smsAp4);

  public List<SmsAp4> findAllByUdt();

}
