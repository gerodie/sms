package com.example.sms.dto;

public class ResponseDto {

  private String MDN;
  private String RtnDateTime;
  private String TaskID;
  private String Code;
  private String Reason;

  public String getMDN() {
    return MDN;
  }

  public void setMDN(String mDN) {
    MDN = mDN;
  }

  public String getRtnDateTime() {
    return RtnDateTime;
  }

  public void setRtnDateTime(String rtnDateTime) {
    RtnDateTime = rtnDateTime;
  }

  public String getTaskID() {
    return TaskID;
  }

  public void setTaskID(String taskID) {
    TaskID = taskID;
  }

  public String getCode() {
    return Code;
  }

  public void setCode(String code) {
    Code = code;
  }

  public String getReason() {
    return Reason;
  }

  public void setReason(String reason) {
    Reason = reason;
  }

  @Override
  public String toString() {
    return "ResponseDto [MDN=" + MDN + ", RtnDateTime=" + RtnDateTime + ", TaskID=" + TaskID
        + ", Code=" + Code + ", Reason=" + Reason + "]";
  }

}
