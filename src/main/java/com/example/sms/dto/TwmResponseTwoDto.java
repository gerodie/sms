package com.example.sms.dto;

public class TwmResponseTwoDto {

  private String msgid;
  private String srcaddr;
  private String dstaddr;
  private String dlvtime;
  private String donetime;
  private String statusstr;
  private String statuscode;

  public String getMsgid() {
    return msgid;
  }

  public void setMsgid(String msgid) {
    this.msgid = msgid;
  }

  public String getSrcaddr() {
    return srcaddr;
  }

  public void setSrcaddr(String srcaddr) {
    this.srcaddr = srcaddr;
  }

  public String getDstaddr() {
    return dstaddr;
  }

  public void setDstaddr(String dstaddr) {
    this.dstaddr = dstaddr;
  }

  public String getDlvtime() {
    return dlvtime;
  }

  public void setDlvtime(String dlvtime) {
    this.dlvtime = dlvtime;
  }

  public String getDonetime() {
    return donetime;
  }

  public void setDonetime(String donetime) {
    this.donetime = donetime;
  }

  public String getStatusstr() {
    return statusstr;
  }

  public void setStatusstr(String statusstr) {
    this.statusstr = statusstr;
  }

  public String getStatuscode() {
    return statuscode;
  }

  public void setStatuscode(String statuscode) {
    this.statuscode = statuscode;
  }

  @Override
  public String toString() {
    return "TwmResponseTwoDto [msgid=" + msgid + ", srcaddr=" + srcaddr + ", dstaddr=" + dstaddr
        + ", dlvtime=" + dlvtime + ", donetime=" + donetime + ", statusstr=" + statusstr
        + ", statuscode=" + statuscode + "]";
  }

}
