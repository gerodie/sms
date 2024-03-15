package com.example.sms.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.sms.dto.TwmResponseTwoDto;
import com.example.sms.entity.SmsTw4PushDR;
import com.example.sms.repository.SmsTw4PushDRRepository;
import com.example.sms.service.SmsTw4PushDRService;

@Service
public class SmsTw4PushDRServiceImpl implements SmsTw4PushDRService {

  private static final Logger log = LogManager.getLogger(SmsTw4PushDRServiceImpl.class);

  @Autowired
  private SmsTw4PushDRRepository smsTw4PushDRRepository;

  @Override
  public void createSmsTw4PushDR(TwmResponseTwoDto twmResponseTwoDto) {
    SmsTw4PushDR smsTw4PushDR = new SmsTw4PushDR();
    String msgId = twmResponseTwoDto.getMsgid();
    try {
      smsTw4PushDR.setMsgId(msgId);
      smsTw4PushDR.setSrcAddr(twmResponseTwoDto.getSrcaddr());
      smsTw4PushDR.setDstAddr(twmResponseTwoDto.getDstaddr());
      smsTw4PushDR.setDlvTime(twmResponseTwoDto.getDlvtime());
      smsTw4PushDR.setDoneTime(twmResponseTwoDto.getDonetime());
      smsTw4PushDR.setStatusStr(twmResponseTwoDto.getStatusstr());
      smsTw4PushDR.setStatusCode(twmResponseTwoDto.getStatuscode());
      smsTw4PushDRRepository.saveAndFlush(smsTw4PushDR);
    } catch (Exception e) {
      log.error(twmResponseTwoDto.toString(), e.toString());
    }
  }
}
