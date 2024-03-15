package com.example.sms.dto;

public class BulkSendDetailsDto {

  private String uId;
  private String mobile;
  private String smsText;

  public String getuId() {
    return uId;
  }

  public void setuId(String uId) {
    this.uId = uId;
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
    return "BulkSendDetailsDto [uId=" + uId + ", mobile=" + mobile + ", smsText=" + smsText + "]";
  }

}
