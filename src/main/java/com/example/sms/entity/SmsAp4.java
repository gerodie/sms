package com.example.sms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sms_ap4")
@IdClass(SmsAp4CompositeId.class)
public class SmsAp4 extends Auditable implements Serializable {

  private static final long serialVersionUID = -4334253004723911076L;

  @Id
  @Column(name = "client_id", nullable = false, length = 20)
  private String clientId;

  @Id
  @Column(name = "msg_id", nullable = false, length = 19)
  private long msgId;

  @Id
  @Column(name = "u_id", nullable = false, length = 20)
  private String uId;

  @Column(name = "mdn", length = 10)
  private String mdn;

  @Column(name = "uid", length = 20)
  private String uIdAccount;

  @Column(name = "upass", length = 20)
  private String upass;

  @Column(name = "content", length = 20)
  private String content;

  @Column(name = "subject", length = 20)
  private String subject;

  @Column(name = "stop_date_time", length = 20)
  private String stopDateTime;

  @Column(name = "message", length = 160)
  private String message;

  @Column(name = "msisdn", length = 20)
  private String msisdn;

  @Column(name = "rtn_date_time", length = 20)
  private String rtnDateTime;

  @Column(name = "task_id", length = 20)
  private String taskId;

  @Column(name = "code", length = 10)
  private String code;

  @Column(name = "reason", length = 20)
  private String reason;

  @Column(name = "ttc_send_status", length = 10)
  private String ttcSendStatus;

  @Column(name = "ttc_send_time", updatable = false)
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

  public String getMdn() {
    return mdn;
  }

  public void setMdn(String mdn) {
    this.mdn = mdn;
  }

  public String getuIdAccount() {
    return uIdAccount;
  }

  public void setuIdAccount(String uIdAccount) {
    this.uIdAccount = uIdAccount;
  }

  public String getUpass() {
    return upass;
  }

  public void setUpass(String upass) {
    this.upass = upass;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getStopDateTime() {
    return stopDateTime;
  }

  public void setStopDateTime(String stopDateTime) {
    this.stopDateTime = stopDateTime;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getRtnDateTime() {
    return rtnDateTime;
  }

  public void setRtnDateTime(String rtnDateTime) {
    this.rtnDateTime = rtnDateTime;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
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

  @Override
  public String toString() {
    return "SmsAp4 [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + ", mdn=" + mdn
        + ", uIdAccount=" + uIdAccount + ", upass=" + upass + ", content=" + content + ", subject="
        + subject + ", stopDateTime=" + stopDateTime + ", message=" + message + ", msisdn=" + msisdn
        + ", rtnDateTime=" + rtnDateTime + ", taskId=" + taskId + ", code=" + code + ", reason="
        + reason + ", ttcSendStatus=" + ttcSendStatus + ", ttcSendTime=" + ttcSendTime + "]";
  }

}
