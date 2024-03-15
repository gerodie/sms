package com.example.sms.service;

import java.util.List;
import java.util.Map;
import com.example.sms.dto.BulkSendDetailsDto;
import com.example.sms.dto.BulkSendMainDto;

public interface BulkSendService {

  public void createBulkSend(BulkSendMainDto bulkSendMainDto,
      BulkSendDetailsDto bulkSendDetailsDto);

  public String getOperatorId(String phoneNumber);

  public List<Map<String, Object>> validateBulkSendDto(BulkSendMainDto bulkSendMainDto);

  public boolean checkMsgIdExist(String msgId);

}
