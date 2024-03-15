package com.example.sms.dto;

public class TwmResponseOneDto {

  private String msgid;
  private String statuscode;
  private String statusstr;

  public String getMsgid() {
    return msgid;
  }

  public void setMsgid(String msgid) {
    this.msgid = msgid;
  }

  public String getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(String statuscode) {
    this.statuscode = statuscode;
  }

  public String getStatusstr() {
    return statusstr;
  }

  public void setStatusstr(String statusstr) {
    this.statusstr = statusstr;
  }

  @Override
  public String toString() {
    return "TwmResponseOneDto [msgid=" + msgid + ", statuscode=" + statuscode + ", statusstr="
        + statusstr + "]";
  }

}
