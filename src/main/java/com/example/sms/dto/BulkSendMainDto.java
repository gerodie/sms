package com.example.sms.dto;

import java.util.List;

public class BulkSendMainDto {

  private String clientId;
  private String msgId;
  private int packageCnt;
  private String sysTime;
  private String timeUp;
  private int expiryMinutes;
  private List<BulkSendDetailsDto> smsBody;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
  }

  public int getPackageCnt() {
    return packageCnt;
  }

  public void setPackageCnt(int packageCnt) {
    this.packageCnt = packageCnt;
  }

  public String getSysTime() {
    return sysTime;
  }

  public void setSysTime(String sysTime) {
    this.sysTime = sysTime;
  }

  public String getTimeUp() {
    return timeUp;
  }

  public void setTimeUp(String timeUp) {
    this.timeUp = timeUp;
  }

  public int getExpiryMinutes() {
    return expiryMinutes;
  }

  public void setExpiryMinutes(int expiryMinutes) {
    this.expiryMinutes = expiryMinutes;
  }

  public List<BulkSendDetailsDto> getSmsBody() {
    return smsBody;
  }

  public void setSmsBody(List<BulkSendDetailsDto> smsBody) {
    this.smsBody = smsBody;
  }

  @Override
  public String toString() {
    return "BulkSendMainDto [clientId=" + clientId + ", msgId=" + msgId + ", packageCnt="
        + packageCnt + ", sysTime=" + sysTime + ", timeUp=" + timeUp + ", expiryMinutes="
        + expiryMinutes + ", smsBody=" + smsBody + "]";
  }

}
