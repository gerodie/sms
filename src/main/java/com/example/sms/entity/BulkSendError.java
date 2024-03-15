package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_bulksend_error")
public class BulkSendError extends Auditable implements Serializable {

  private static final long serialVersionUID = -5487533155396073456L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "sequence_no", nullable = false)
  private int sequenceNo;

  @Column(name = "client_id", nullable = false, length = 20)
  private String clientId;

  @Column(name = "msg_id", nullable = false, length = 19)
  private String msgId;

  @Column(name = "ttc_error_status", length = 4)
  private String ttcErrorStatus;

  @Column(name = "u_id", length = 22)
  private String uId;

  @Column(name = "uid_status", length = 4)
  private String uidStatus;

  @Column(name = "phone_number", nullable = false, length = 10)
  private String mobile;

  @Column(name = "sms_text", nullable = false, length = 160)
  private String smsText;

  public int getSequenceNo() {
    return sequenceNo;
  }

  public void setSequenceNo(int sequenceNo) {
    this.sequenceNo = sequenceNo;
  }

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

  public String getTtcErrorStatus() {
    return ttcErrorStatus;
  }

  public void setTtcErrorStatus(String ttcErrorStatus) {
    this.ttcErrorStatus = ttcErrorStatus;
  }

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
  }

  public String getUidStatus() {
    return uidStatus;
  }

  public void setUidStatus(String uidStatus) {
    this.uidStatus = uidStatus;
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

  @Override
  public String toString() {
    return "BulkSendError [sequenceNo=" + sequenceNo + ", clientId=" + clientId + ", msgId=" + msgId
        + ", ttcErrorStatus=" + ttcErrorStatus + ", uId=" + uId + ", uidStatus=" + uidStatus
        + ", mobile=" + mobile + ", smsText=" + smsText + "]";
  }

}
