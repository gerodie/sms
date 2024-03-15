package com.example.sms.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.TwmResponseTwoDto;
import com.example.sms.entity.SmsTw4;
import com.example.sms.service.SmsTw4PushDRService;
import com.example.sms.service.SmsTw4Service;

@RestController
@RequestMapping(path = "/api/sendsms-twm")
public class TwmSendController {

  @Autowired
  private SmsTw4Service smsTw4Service;

  @Autowired
  private SmsTw4PushDRService smsTw4PushDRService;

  @PostMapping(path = "/wakeup")
  public ResponseEntity<?> wakeup(@RequestParam String clientId, @RequestParam String msgId) {
    List<SmsTw4> list = smsTw4Service.findAllBySendStatus(clientId, msgId);
    if (!CollectionUtils.isEmpty(list)) {
      list = smsTw4Service.updateSendStatus(list);
      smsTw4Service.sendSms(list);
      return new ResponseEntity<>(HttpStatus.OK);
    }
    return null;
  }

  @GetMapping(path = "/phasetwo-response", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity<?> phaseIIResponse(TwmResponseTwoDto twmResponseTwoDto) {
    smsTw4PushDRService.createSmsTw4PushDR(twmResponseTwoDto);
    Map<String, Object> responseMap = new HashMap<String, Object>();
    responseMap.put(GlobalVariables.RESPONSE_MAGICID, GlobalVariables.RESPONSE_PHASE_II_DONE);
    responseMap.put(GlobalVariables.RESPONSE_MSGID, twmResponseTwoDto.getMsgid());
    return new ResponseEntity<>(responseMap.toString(), HttpStatus.OK);
  }

  @GetMapping(path = "/send.cgi")
  public ResponseEntity<?> twmTestPhaseOneApi() {
    Map<String, Object> responseMap = new HashMap<String, Object>();
    responseMap.put("msgid", "1152000");
    responseMap.put("statuscode", "0");
    responseMap.put("statusstr", "ParseOK");
    return new ResponseEntity<>(responseMap, HttpStatus.OK);
  }

}
