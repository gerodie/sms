package com.example.sms.entity;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class BulkSendCompositeId implements Serializable {

  private static final long serialVersionUID = 5300351382527709454L;

  private String clientId;
  private String msgId;
  private String uId;

  @Override
  public String toString() {
    return "BulkSendCompositeId [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + "]";
  }

}
