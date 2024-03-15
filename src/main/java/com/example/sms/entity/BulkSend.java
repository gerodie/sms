package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sms_bulksend")
@IdClass(BulkSendCompositeId.class)
public class BulkSend extends Auditable implements Serializable {

  private static final long serialVersionUID = -9108756973144529944L;

  @Id
  @Column(name = "client_id", nullable = false, length = 20)
  private String clientId;

  @Id
  @Column(name = "msg_id", nullable = false, length = 19)
  private String msgId;

  @Id
  @Column(name = "u_id", nullable = false, length = 20)
  private String uId;

  @Column(name = "sys_time", nullable = false, length = 14)
  private String sysTime;

  @Column(name = "time_up", nullable = false, length = 12)
  private String timeUp;

  @Column(name = "expiry_minutes", nullable = false)
  private int expiryMinutes = 1440;

  @Column(name = "phone_number", nullable = false, length = 10)
  private String mobile;

  @Column(name = "sms_text", nullable = false, length = 160)
  private String smsText;

  @Column(name = "operator_id", nullable = false, length = 3)
  private String operatorId;

  @Column(name = "ttc_status", nullable = false, length = 20)
  private String ttcStatus;

  @Column(name = "client_status", nullable = false, length = 20)
  private String clientStatus;

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

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
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

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getSmsText() {
    return smsText;
  }

  public void setSmsText(String smsText) {
    this.smsText = smsText;
  }

  public String getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(String operatorId) {
    this.operatorId = operatorId;
  }

  public String getTtcStatus() {
    return ttcStatus;
  }

  public void setTtcStatus(String ttcStatus) {
    this.ttcStatus = ttcStatus;
  }

  public String getClientStatus() {
    return clientStatus;
  }

  public void setClientStatus(String clientStatus) {
    this.clientStatus = clientStatus;
  }

  @Override
  public String toString() {
    return "BulkSend [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + ", sysTime="
        + sysTime + ", timeUp=" + timeUp + ", expiryMinutes=" + expiryMinutes + ", mobile=" + mobile
        + ", smsText=" + smsText + ", operatorId=" + operatorId + ", ttcStatus=" + ttcStatus
        + ", clientStatus=" + clientStatus + "]";
  }

}
