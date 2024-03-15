package com.example.sms.service;

import java.util.Map;
import com.example.sms.dto.BulkSendMainDto;

public interface BulkSendErrorService {

  public void createBulkSendError(BulkSendMainDto bulkSendMainDto, Map<String, Object> responseMap,
      int bulkSendDetailDtoIndex);

}
