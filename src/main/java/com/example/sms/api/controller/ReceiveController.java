package com.example.sms.api.controller;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sms.common.ApiResponseState;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.BulkSendDetailsDto;
import com.example.sms.dto.BulkSendMainDto;
import com.example.sms.service.AccountService;
import com.example.sms.service.BulkSendErrorService;
import com.example.sms.service.BulkSendService;

@RestController
@RequestMapping(path = "/api/receive")
@Validated
public class ReceiveController {

  private static final Logger log = LogManager.getLogger(ReceiveController.class);

  @Autowired
  private BulkSendService bulkSendService;

  @Autowired
  private AccountService accountService;

  @Autowired
  private BulkSendErrorService bulkSendErrorService;

  @PostMapping(path = "/bulkSend", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Map<String, Object> bulkSend(@RequestParam @Valid @NotBlank String username,
      @RequestParam @Valid @NotBlank String password,
      @RequestBody @Valid @NotNull BulkSendMainDto bulkSendMainDto) {

    log.info("receive date,msgId:" + bulkSendMainDto.getMsgId());
    Map<String, Object> responseMap = new HashMap<>(5);
    responseMap.put(GlobalVariables.COMMON_MSGID, bulkSendMainDto.getMsgId());

    log.info("check account:" + username);
    boolean isAccountExist = accountService.validateAccountInfo(username, password);
    if (!isAccountExist) {
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS,
          ApiResponseState.ACCOUNT_ERROR.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.ACCOUNT_ERROR.getDescription());
      bulkSendErrorService.createBulkSendError(bulkSendMainDto, responseMap, 0);
      return responseMap;
    }

    LocalTime systemTime = LocalTime.now();
    log.info("check request time:" + systemTime);
    LocalTime routingStartTime = LocalTime.parse(GlobalVariables.ROUTING_STARTTIME);
    LocalTime routingEndTime = LocalTime.parse(GlobalVariables.ROUTING_ENDTIME);
    if (!systemTime.isBefore(routingStartTime) && !systemTime.isAfter(routingEndTime)) {
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS,
          ApiResponseState.SERVICE_UNAVAILABLE.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.SERVICE_UNAVAILABLE.getDescription());
      bulkSendErrorService.createBulkSendError(bulkSendMainDto, responseMap, 0);
      return responseMap;
    }

    // check msgId
    boolean isMsgIdExist = bulkSendService.checkMsgIdExist(bulkSendMainDto.getMsgId());
    if (isMsgIdExist) {
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS,
          ApiResponseState.MSGID_DUPLICATE.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.MSGID_DUPLICATE.getDescription());
      bulkSendErrorService.createBulkSendError(bulkSendMainDto, responseMap, 0);
      return responseMap;
    }

    // check post data count
    int packageCnt = bulkSendMainDto.getPackageCnt();
    List<BulkSendDetailsDto> bulkSendDetailsDtos = bulkSendMainDto.getSmsBody();
    log.info("check data count,packageCnt:" + packageCnt + ",SmsBodyCnt:"
        + bulkSendMainDto.getSmsBody().size());
    if (packageCnt > GlobalVariables.PAYLOAD_MAX_COUNT
        || packageCnt != bulkSendDetailsDtos.size()) {
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS, ApiResponseState.COUNT_ERROR.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.COUNT_ERROR.getDescription());
      bulkSendErrorService.createBulkSendError(bulkSendMainDto, responseMap, 0);
      return responseMap;
    }

    // check mobile and smsText data
    List<Map<String, Object>> dataMapList = bulkSendService.validateBulkSendDto(bulkSendMainDto);
    if (CollectionUtils.isEmpty(dataMapList)) {
      responseMap.put(GlobalVariables.COMMON_PACKAGECNT, packageCnt);
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS,
          ApiResponseState.RECEIVE_SUCCESS.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.RECEIVE_SUCCESS.getDescription());
    } else {
      responseMap.put(GlobalVariables.COMMON_PACKAGECNT, packageCnt - dataMapList.size());
      responseMap.put(GlobalVariables.RESPONSE_TTCSTATUS,
          ApiResponseState.HANDLE_EXCEPTION.getState());
      responseMap.put(GlobalVariables.COMMON_STATUSMSG,
          ApiResponseState.HANDLE_EXCEPTION.getDescription());
      responseMap.put(GlobalVariables.RESPONSE_ERRORUIDS, dataMapList);
    }

    return responseMap;
  }

}
