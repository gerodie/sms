package com.example.sms.service.impl;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sms.entity.SmsAp4;
import com.example.sms.repository.SmsAp4QueryRepository;
import com.example.sms.service.SmsAp4QueryService;

@Service
public class SmsAp4QueryServiceImpl implements SmsAp4QueryService {

  private static final Logger log = LogManager.getLogger(SmsAp4QueryServiceImpl.class);

  @Autowired
  private SmsAp4QueryRepository smsAp4QueryRepository;

  @Override
  public void querySmsResult(SmsAp4 smsAp4) {
    log.info("start querySmsResult");
  }

  @Override
  public List<SmsAp4> findAllByUdt() {
    log.info("start findAllByUdt");
    try {
      return smsAp4QueryRepository.findAllByUdt();
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }

}
