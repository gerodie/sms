package com.example.sms.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "sms_tw4")
@IdClass(SmsTw4CompositeId.class)
public class SmsTw4 extends Auditable implements Serializable {

  private static final long serialVersionUID = 2752331175825876074L;

  @Id
  @Column(name = "client_id", nullable = false, length = 20)
  private String clientId;

  @Id
  @Column(name = "msg_id", nullable = false, length = 19)
  private long msgId;

  @Id
  @Column(name = "u_id", nullable = false, length = 20)
  private String uId;

  @Column(name = "username", length = 20)
  private String username;

  @Column(name = "password", length = 20)
  private String password;

  @Column(name = "rate_plan", length = 20)
  private String ratePlan;

  @Column(name = "src_addr", length = 20)
  private String srcAddr;

  @Column(name = "dst_addr", length = 20)
  private String dstAddr;

  @Column(name = "encoding", length = 10)
  private String encoding;

  @Column(name = "smbody", length = 160)
  private String smBody;

  @Column(name = "dlv_time", length = 20)
  private String dlvTime;

  @Column(name = "vld_time", length = 20)
  private String vldTime;

  @Column(name = "response", length = 100)
  private String response;

  @Column(name = "response_msg_id", length = 20)
  private String responseMsgId;

  @Column(name = "status_code", length = 20)
  private String statusCode;

  @Column(name = "status_str", length = 100)
  private String statusStr;

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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRatePlan() {
    return ratePlan;
  }

  public void setRatePlan(String ratePlan) {
    this.ratePlan = ratePlan;
  }

  public String getSrcAddr() {
    return srcAddr;
  }

  public void setSrcAddr(String srcAddr) {
    this.srcAddr = srcAddr;
  }

  public String getDstAddr() {
    return dstAddr;
  }

  public void setDstAddr(String dstAddr) {
    this.dstAddr = dstAddr;
  }

  public String getEncoding() {
    return encoding;
  }

  public void setEncoding(String encoding) {
    this.encoding = encoding;
  }

  public String getSmBody() {
    return smBody;
  }

  public void setSmBody(String smBody) {
    this.smBody = smBody;
  }

  public String getDlvTime() {
    return dlvTime;
  }

  public void setDlvTime(String dlvTime) {
    this.dlvTime = dlvTime;
  }

  public String getVldTime() {
    return vldTime;
  }

  public void setVldTime(String vldTime) {
    this.vldTime = vldTime;
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    this.response = response;
  }

  public String getResponseMsgId() {
    return responseMsgId;
  }

  public void setResponseMsgId(String responseMsgId) {
    this.responseMsgId = responseMsgId;
  }

  public String getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
  }

  public String getStatusStr() {
    return statusStr;
  }

  public void setStatusStr(String statusStr) {
    this.statusStr = statusStr;
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
    return "SmsTw4 [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + ", username="
        + username + ", password=" + password + ", ratePlan=" + ratePlan + ", srcAddr=" + srcAddr
        + ", dstAddr=" + dstAddr + ", encoding=" + encoding + ", smBody=" + smBody + ", dlvTime="
        + dlvTime + ", vldTime=" + vldTime + ", response=" + response + ", responseMsgId="
        + responseMsgId + ", statusCode=" + statusCode + ", statusStr=" + statusStr
        + ", ttcSendStatus=" + ttcSendStatus + ", ttcSendTime=" + ttcSendTime + "]";
  }

}
