package com.example.sms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sms_ch4")
@IdClass(SmsCh4CompositeId.class)
public class SmsCh4 extends Auditable implements Serializable {

  private static final long serialVersionUID = -497233253220987498L;

  @Id
  @Column(name = "client_id", nullable = false, length = 20)
  private String clientId;

  @Id
  @Column(name = "msg_id", nullable = false, length = 19)
  private long msgId;

  @Id
  @Column(name = "u_id", nullable = false, length = 20)
  private String uId;

  @Column(name = "coding", length = 20)
  private String coding;

  @Column(name = "rcv_Msisdn", length = 20)
  private String rcvMsisdn;

  @Column(name = "message", length = 160)
  private String message;

  @Column(name = "message_id", length = 20)
  private String messageId;

  @Column(name = "expiry_hours", length = 20)
  private String expiryHours;

  @Column(name = "expiry_minutes", length = 20)
  private String expiryMinutes;

  @Column(name = "res_code", length = 20)
  private String resCode;

  @Column(name = "buffer", length = 500)
  private String buffer;

  @Column(name = "ttc_send_status", length = 10)
  private String ttcSendStatus;

  @Column(name = "ttc_send_time")
  private Date ttcSendTime;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public long getMsgId() {
    return msgId;
  }

  public void setMsgId(long msgId) {
    this.msgId = msgId;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getCoding() {
    return coding;
  }

  public void setCoding(String coding) {
    this.coding = coding;
  }

  public String getRcvMsisdn() {
    return rcvMsisdn;
  }

  public void setRcvMsisdn(String rcvMsisdn) {
    this.rcvMsisdn = rcvMsisdn;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getExpiryHours() {
    return expiryHours;
  }

  public void setExpiryHours(String expiryHours) {
    this.expiryHours = expiryHours;
  }

  public String getExpiryMinutes() {
    return expiryMinutes;
  }

  public void setExpiryMinutes(String expiryMinutes) {
    this.expiryMinutes = expiryMinutes;
  }

  public String getResCode() {
    return resCode;
  }

  public void setResCode(String resCode) {
    this.resCode = resCode;
  }

  public String getTtcSendStatus() {
    return ttcSendStatus;
  }

  public void setTtcSendStatus(String ttcSendStatus) {
    this.ttcSendStatus = ttcSendStatus;
  }

  public Date getTtcSendTime() {
    return ttcSendTime;
  }

  public void setTtcSendTime(Date ttcSendTime) {
    this.ttcSendTime = ttcSendTime;
  }

  public String getBuffer() {
    return buffer;
  }

  public void setBuffer(String buffer) {
    this.buffer = buffer;
  }

  @Override
  public String toString() {
    return "SmsCh4 [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + ", coding="
        + coding + ", rcvMsisdn=" + rcvMsisdn + ", message=" + message + ", messageId=" + messageId
        + ", expiryHours=" + expiryHours + ", expiryMinutes=" + expiryMinutes + ", resCode="
        + resCode + ", buffer=" + buffer + ", ttcSendStatus=" + ttcSendStatus + ", ttcSendTime="
        + ttcSendTime + "]";
  }

}
