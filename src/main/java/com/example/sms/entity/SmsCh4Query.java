package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_ch4_query")
public class SmsCh4Query implements Serializable {

  private static final long serialVersionUID = 5541067988206589225L;

  @Id
  @Column(name = "message_id", nullable = false, length = 20)
  private String messageId;

  @Column(name = "code", length = 20)
  private String code;

  @Column(name = "buffer", length = 500)
  private String buffer;

  public String getMessageId() {
    return messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getBuffer() {
    return buffer;
  }

  public void setBuffer(String buffer) {
    this.buffer = buffer;
  }

  @Override
  public String toString() {
    return "SmsCh4Query [messageId=" + messageId + ", code=" + code + ", buffer=" + buffer + "]";
  }

}
