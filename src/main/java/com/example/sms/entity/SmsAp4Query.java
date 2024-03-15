package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_ap4_query")
public class SmsAp4Query implements Serializable {

  private static final long serialVersionUID = -3011534167656364307L;

  @Id
  @Column(name = "mdn", nullable = false, length = 10)
  private String mdn;

  @Column(name = "rtn_date_time", nullable = false, length = 20)
  private String rtnDateTime;

  @Column(name = "task_id", nullable = false, length = 20)
  private String taskId;

  @Column(name = "create_time", length = 20)
  private String createTime;

  @Column(name = "total_rec", length = 20)
  private String totalRec;

  @Column(name = "task_status", length = 20)
  private String taskStatus;

  @Column(name = "msisdn", length = 20)
  private String msisdn;

  @Column(name = "status", length = 2)
  private String status;

  @Column(name = "dr_date_time", length = 12)
  private String drDateTime;

  @Column(name = "code", length = 10)
  private String code;

  @Column(name = "reason", length = 50)
  private String reason;

  public String getMdn() {
    return mdn;
  }

  public void setMdn(String mdn) {
    this.mdn = mdn;
  }

  public String getRtnDateTime() {
    return rtnDateTime;
  }

  public void setRtnDateTime(String rtnDateTime) {
    this.rtnDateTime = rtnDateTime;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getTotalRec() {
    return totalRec;
  }

  public void setTotalRec(String totalRec) {
    this.totalRec = totalRec;
  }

  public String getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(String taskStatus) {
    this.taskStatus = taskStatus;
  }

  public String getMsisdn() {
    return msisdn;
  }

  public void setMsisdn(String msisdn) {
    this.msisdn = msisdn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDrDateTime() {
    return drDateTime;
  }

  public void setDrDateTime(String drDateTime) {
    this.drDateTime = drDateTime;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  @Override
  public String toString() {
    return "SmsAp4Query [mdn=" + mdn + ", rtnDateTime=" + rtnDateTime + ", taskId=" + taskId
        + ", createTime=" + createTime + ", totalRec=" + totalRec + ", taskStatus=" + taskStatus
        + ", msisdn=" + msisdn + ", status=" + status + ", drDateTime=" + drDateTime + ", code="
        + code + ", reason=" + reason + "]";
  }

}
