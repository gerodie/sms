package com.example.sms.dto;

import java.util.List;

public class RequestMainDto {

  private String Subject;
  private String StopDateTime;
  private String Message;
  private List<RequestDetailDto> MDNList;

  public String getSubject() {
    return Subject;
  }

  public void setSubject(String subject) {
    Subject = subject;
  }

  public String getStopDateTime() {
    return StopDateTime;
  }

  public void setStopDateTime(String stopDateTime) {
    StopDateTime = stopDateTime;
  }

  public String getMessage() {
    return Message;
  }

  public void setMessage(String message) {
    Message = message;
  }

  public List<RequestDetailDto> getMDNList() {
    return MDNList;
  }

  public void setMDNList(List<RequestDetailDto> mDNList) {
    MDNList = mDNList;
  }

  @Override
  public String toString() {
    return "RequestMainDto [Subject=" + Subject + ", StopDateTime=" + StopDateTime + ", Message="
        + Message + ", MDNList=" + MDNList + "]";
  }

}
