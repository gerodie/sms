package com.example.sms.common;

/**
 * SNS Protocol參數資訊(中華電信提供)
 */
public interface IConstants {

  /**
   * 發訊封包size
   */
  public static final int SUBMIT_BUFFER_SIZE_V1 = 217;
  public static final int SUBMIT_BUFFER_SIZE_V3 = 238;

  /**
   * 回傳封包size
   */
  public static final int RECV_BUFFER_SIZE_V1 = 189;

  /**
   * 當Login時，將type設成SERV_LOGIN
   */
  public static final byte SERV_LOGIN = 0;

  /**
   * 當要change password時，將type設為SERV_CHANGE_PASSWORD
   */
  public static final byte SERV_CHANGE_PASSWORD = 1;

  /**
   * 當要傳送訊息時，將type設為SERV_SUBMIT_MSG
   */
  public static final byte SERV_SUBMIT_MSG = 2;

  /**
   * 當要查詢簡訊傳送結果時，將type設為SERV_QUERY_STATE
   */
  public static final byte SERV_QUERY_STATE = 3;

  /**
   * 當要接收簡訊時，將type設為SERV_GET_MSG
   */
  public static final byte SERV_GET_MSG = 4;

  /**
   * 一般將簡訊編碼格式設成TEXT_CODING_VALUE
   */
  public static final byte TEXT_CODING_VALUE = 1;

  /**
   * 預設簡訊傳送方式
   */
  public static final byte DEFAULT_TRAN_TYPE = 100;

  /**
   * 每個封包的簡訊內容最大值
   */
  public static final int MAX_MSG_SIZE = 160;
}
