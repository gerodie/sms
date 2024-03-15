package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_tw4_pushdr")
public class SmsTw4PushDR implements Serializable {

  private static final long serialVersionUID = 3439434197645383214L;

  @Id
  @Column(name = "msg_id", nullable = false, length = 20)
  private String msgId;

  @Column(name = "src_addr", nullable = false, length = 20)
  private String srcAddr;

  @Column(name = "dst_addr", nullable = false, length = 20)
  private String dstAddr;

  @Column(name = "dlv_time", nullable = false, length = 20)
  private String dlvTime;

  @Column(name = "done_time", nullable = false, length = 20)
  private String doneTime;

  @Column(name = "status_code", nullable = false, length = 20)
  private String statusCode;

  @Column(name = "status_str", nullable = false, length = 20)
  private String statusStr;

  public String getMsgId() {
    return msgId;
  }

  public void setMsgId(String msgId) {
    this.msgId = msgId;
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

  public String getDlvTime() {
    return dlvTime;
  }

  public void setDlvTime(String dlvTime) {
    this.dlvTime = dlvTime;
  }

  public String getDoneTime() {
    return doneTime;
  }

  public void setDoneTime(String doneTime) {
    this.doneTime = doneTime;
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

  @Override
  public String toString() {
    return "SmsTw4PushDR [msgId=" + msgId + ", srcAddr=" + srcAddr + ", dstAddr=" + dstAddr
        + ", dlvTime=" + dlvTime + ", doneTime=" + doneTime + ", statusCode=" + statusCode
        + ", statusStr=" + statusStr + "]";
  }

}
