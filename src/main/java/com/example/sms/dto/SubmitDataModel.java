package com.example.sms.dto;

import com.example.sms.common.IConstants;

/**
 * SNS Protocol傳送命令的參數(中華電信提供)
 */
public class SubmitDataModel {

  /**
   * SNS Protocol命令代碼. 0->登入, 2->傳送簡訊, 3->查詢狀態
   */
  protected byte type;

  /**
   * 簡訊編碼方式.一般文字請設為1
   */
  protected byte coding;

  /**
   * 簡訊長度.以byte計算
   */
  protected byte length;

  /**
   * 訊息傳送方式.立即傳送為100, 預約傳送為101
   */
  protected byte tranType;

  /**
   * 登入帳號
   */
  protected String account;

  /**
   * 登入密碼
   */
  protected String password;

  /**
   * 接收簡訊的手機號碼, 09xxxxxxxx
   */
  protected String rcvMsisdn;

  /**
   * 訊息查詢代碼,運用在查詢簡訊傳送狀態
   */
  protected String messageID;

  /**
   * 簡訊傳送的預約時間,格式為yymmddhhmm00,如果是立即傳送,此欄位不用填
   */
  protected String dlvTime;

  /**
   * 簡訊內容
   */
  protected String message;
  protected byte[] byMsg;
  private boolean isByMsg = false;

  private boolean isSetPchid = false;
  protected byte pclid;
  protected byte[] pchid = new byte[9];

  private String m_strLastMessage = "";

  public SubmitDataModel() {
    reset();
  }

  /**
   * 傳回錯誤訊息
   */
  public String getLastMessage() {
    return m_strLastMessage;
  }

  /**
   * 重設欄位資料於初始值
   */
  public void reset() {
    type = (byte) 0;
    coding = (byte) 1;
    length = (byte) 0;
    tranType = (byte) 100;
    account = "";
    password = "";
    rcvMsisdn = "";
    messageID = "";
    dlvTime = "";
    message = "";

    isByMsg = false;
    isSetPchid = false;
    for (int i = 0; i < pchid.length; i++) {
      pchid[i] = (byte) 0;
    }
  }

  /**
   * 將物件轉成位元串流.如果轉換失敗,將會傳回null,呼叫getLastMessage()取得失敗原因
   * 
   * @return byte[] - 物件的位元串流
   */
  public byte[] toByteStream_V1() {
    // modified by yengopan, 2014/05/10, extending rcvMsisdn(13->22) and messageID(9->21) for
    // international format
    // byte[] stream = new byte[IConstants.SUBMIT_BUFFER_SIZE_V1];
    byte[] stream = new byte[IConstants.SUBMIT_BUFFER_SIZE_V3];

    stream[0] = type;
    stream[1] = coding;
    stream[2] = length;
    stream[3] = tranType;
    for (int i = 0; i < account.getBytes().length; i++) {
      stream[i + 4] = account.getBytes()[i];
    }
    for (int i = 0; i < password.getBytes().length; i++) {
      stream[i + 13] = password.getBytes()[i];
    }
    for (int i = 0; i < rcvMsisdn.getBytes().length; i++) {
      if ((rcvMsisdn.length() > 12) && (rcvMsisdn.charAt(0) != '+'))
        rcvMsisdn = "+" + rcvMsisdn;
      stream[i + 22] = rcvMsisdn.getBytes()[i];
    }
    for (int i = 0; i < messageID.getBytes().length; i++) {
      // stream[i+35] = messageID.getBytes()[i];
      stream[i + 44] = messageID.getBytes()[i];
    }
    if (isByMsg == false) {
      for (int i = 0; i < message.getBytes().length; i++) {
        // stream[i+44] = message.getBytes()[i];
        stream[i + 65] = message.getBytes()[i];
      }
    } else {
      for (int i = 0; i < byMsg.length; i++) {
        // stream[i+44] = byMsg[i];
        stream[i + 65] = byMsg[i];
      }
    }
    for (int i = 0; i < dlvTime.getBytes().length; i++) {
      // stream[i+204] = dlvTime.getBytes()[i];
      stream[i + 225] = dlvTime.getBytes()[i];
    }
    if (isSetPchid)
      for (int i = 0; i < pchid.length; i++) {
        stream[i + 4] = pchid[i];
      }
    return stream;
  }

  /**
   * 傳回訊息查詢代碼
   */
  public String getMessageID() {
    return messageID;
  }

  public byte getCoding() {
    return coding;
  }

  public byte getLength() {
    return length;
  }

  public String getDlvTime() {
    return dlvTime;
  }

  public String getMessage() {
    return message;
  }

  public String getAccount() {
    return account;
  }

  public byte getType() {
    return type;
  }

  public byte getTranType() {
    return tranType;
  }

  public String getRcvMsisdn() {
    return rcvMsisdn;
  }

  /**
   * 設定登入密碼
   * 
   * @param password String
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * 設定訊息查詢代碼
   * 
   * @param messageID String - 訊息查詢代碼,此代碼由SNS回傳給程式
   */
  public void setMessageID(String messageID) {
    this.messageID = messageID;
  }

  /**
   * 設定簡訊編碼格式
   * 
   * @param coding byte
   */
  public void setCoding(byte coding) {
    this.coding = coding;
  }

  /**
   * 設定簡訊內容長度
   * 
   * @param length byte
   */
  public void setLength(byte length) {
    this.length = length;
  }

  /**
   * 設定預約傳送時間
   * 
   * @param dlvTime String
   */
  public void setDlvTime(String dlvTime) {
    this.dlvTime = dlvTime;
  }

  /**
   * 設定簡訊內容
   * 
   * @param message String
   */
  public void setMessage(String message) {
    this.message = message;
  }

  public void setMessage(byte[] bymsg) {
    isByMsg = true;
    this.byMsg = bymsg;
  }

  /**
   * 設定登入帳號
   * 
   * @param account String
   */
  public void setAccount(String account) {
    this.account = account;
  }

  /**
   * 設定服務類型
   * 
   * @param type byte
   */
  public void setType(byte type) {
    this.type = type;
  }

  /**
   * 設定簡訊傳送方式
   * 
   * @param tranType byte
   */
  public void setTranType(byte tranType) {
    this.tranType = tranType;
  }

  /**
   * 設定接收訊息之手機號碼
   * 
   * @param rcvMsisdn String
   */
  public void setRcvMsisdn(String rcvMsisdn) {
    this.rcvMsisdn = rcvMsisdn;
  }

  public String getPassword() {
    return password;
  }

  public void setExpire(byte hour, byte min) {
    if ((hour > 0) || (min > 0)) {
      isSetPchid = true;
      this.pchid[0] = hour;
      this.pchid[1] = min;
    }
  }

  public void setUdhi(byte udhi) {
    if (udhi == 1) {
      isSetPchid = true;
      this.pchid[2] |= 0x01;
    }
  }

  public void setPclid(byte pclid) {
    if (pclid > 0) {
      isSetPchid = true;
      this.pchid[2] |= 0x04;
      this.pchid[4] = pclid;
    }
  }

}
