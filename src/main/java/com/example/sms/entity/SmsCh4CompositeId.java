package com.example.sms.entity;

import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@EqualsAndHashCode
public class SmsCh4CompositeId implements Serializable {

  private static final long serialVersionUID = 7782385323420862941L;

  private String clientId;
  private long msgId;
  private String uId;

  @Override
  public String toString() {
    return "SmsCh4CompositeId [clientId=" + clientId + ", msgId=" + msgId + ", uId=" + uId + "]";
  }

}
