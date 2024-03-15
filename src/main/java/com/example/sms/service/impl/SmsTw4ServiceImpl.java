package com.example.sms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.sms.common.GlobalVariables;
import com.example.sms.dto.TwmResponseOneDto;
import com.example.sms.entity.SmsTw4;
import com.example.sms.repository.SmsTw4Repository;
import com.example.sms.service.SmsTw4Service;

@Service
public class SmsTw4ServiceImpl implements SmsTw4Service {

  private static final Logger log = LogManager.getLogger(SmsTw4ServiceImpl.class);

  @Autowired
  private SmsTw4Repository smsTw4Repository;

  @Override
  public List<SmsTw4> findAllBySendStatus(String clientId, String msgId) {
    return smsTw4Repository.findAllBySendStatus(clientId, msgId);
  }

  @Override
  public List<SmsTw4> updateSendStatus(List<SmsTw4> smsTw4List) {
    for (SmsTw4 smsTw4 : smsTw4List) {
      smsTw4.setEncoding("ASCII");
      smsTw4.setResponse(GlobalVariables.CALL_TTC_API);
      smsTw4.setTtcSendStatus(GlobalVariables.TTC_SEND_PROCESS);
      smsTw4.setTtcSendTime(new Date());
    }
    return smsTw4Repository.saveAllAndFlush(smsTw4List);
  }

  @Override
  public void sendSms(List<SmsTw4> smsTw4List) {
    Map<String, Object> params = null;
    for (SmsTw4 smsTw4 : smsTw4List) {
      params = new HashMap<String, Object>(10);
      params.put(GlobalVariables.REQUEST_USERNAME, smsTw4.getUsername());
      params.put(GlobalVariables.REQUEST_PASSWORD, smsTw4.getPassword());
      params.put(GlobalVariables.REQUEST_RATEPLAN, smsTw4.getRatePlan());
      params.put(GlobalVariables.REQUEST_SRCADDR, smsTw4.getSrcAddr());
      // 以上參數台哥大提供
      params.put(GlobalVariables.REQUEST_DSTADDR, smsTw4.getDstAddr());
      params.put(GlobalVariables.REQUEST_ENCODING, smsTw4.getEncoding());
      params.put(GlobalVariables.REQUEST_SMBODY, smsTw4.getSmBody());
      params.put(GlobalVariables.REQUEST_DLVTIME, smsTw4.getDlvTime());
      params.put(GlobalVariables.REQUEST_VLDTIME, smsTw4.getVldTime());
      params.put(GlobalVariables.REQUEST_RESPONSE, smsTw4.getResponse());

      try {
        TwmResponseOneDto dto = new RestTemplate().getForObject(GlobalVariables.CALL_TWM_API,
            TwmResponseOneDto.class, params);
        // handle phase I response
        String ttcSendStatus = "0".equals(dto.getStatuscode()) ? GlobalVariables.TTC_SEND_SUCCESS
            : GlobalVariables.TTC_SEND_FAIL;
        smsTw4.setResponseMsgId(dto.getMsgid());
        smsTw4.setStatusCode(dto.getStatuscode());
        smsTw4.setStatusStr(dto.getStatusstr());
        smsTw4.setTtcSendStatus(ttcSendStatus);
      } catch (Exception e) {
        smsTw4.setTtcSendStatus(GlobalVariables.TTC_SEND_ERROR);
        log.error(e.toString());
      }
      String msgId = String.valueOf(smsTw4.getMsgId());
      String clientId = smsTw4Repository.getClientIdByMsgId(msgId);
      smsTw4Repository.updateBulkSendTtcStatus(GlobalVariables.TELECOM_OPERATOR_TWM, clientId,
          msgId);
    }
    smsTw4Repository.saveAllAndFlush(smsTw4List);
  }

}
