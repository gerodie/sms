package com.example.sms.service.impl;

import java.io.DataInputStream;
import com.example.sms.common.IConstants;
import com.example.sms.dto.RecvDataModel;
import com.example.sms.dto.SubmitDataModel;

/**
 * SNS Protocol的訊息傳送/查詢命令(中華電信提供)
 */
public class SnsClient_V1 extends SnsClientImpl {

  /**
   * @param ip - SNS Server的IP位址
   * @param port - SNS Server的通訊埠
   */
  public SnsClient_V1(String ip, int port) {
    super(ip, port);
    m_recvBuffer = new byte[IConstants.RECV_BUFFER_SIZE_V1];
  }

  /**
   * 登入SNS Server, 回傳值為true, 表示登入認證成功, 可以進行後續簡訊傳送/查詢命令.如果是false,表示網路障礙或是帳號資訊錯誤
   * 
   * @param sdm SubmitDataModel - 傳入SNS Server的參數物件, 必須包含帳號與密碼資訊
   * @return boolean - true, 表示登入認證成功; false, 表示登入認證失敗, 呼叫getLastMessage()取得失敗原因
   */
  public boolean login(SubmitDataModel sdm) {
    if (m_smsSocket == null)
      if (connectServer() == false)
        return false;
    try {
      m_socketOut.write(sdm.toByteStream_V1());
      m_socketOut.flush();
      m_socketIn = new DataInputStream(m_smsSocket.getInputStream());
      m_socketIn.readFully(m_recvBuffer);
      RecvDataModel dm = RecvDataModel.parse_v1(m_recvBuffer);
      if (dm.getCode() != 0) {
        m_strLastMessage =
            "Server return code : " + dm.getCode() + ":" + new String(dm.getDesc()).trim();
        disconnectServer();
        return false;
      }
      return true;
    } catch (Exception e) {
      m_strLastMessage = "Exception occurs in login(" + e.getMessage() + ")";
      disconnectServer();
      return false;
    }
  }

  /**
   * 傳送簡訊
   * 
   * @param sdm - 傳入SNS Server的參數物件, 必須包含手機門號及欲傳送訊息
   * @return - null, 表示網路異常造成運作中斷, 呼叫getLastMessage()取得失敗原因; 如果執行成功, 透過回傳物件的getCode()與getDesc(),
   *         判斷訊息傳送是否成功
   */
  public RecvDataModel submitMessage(SubmitDataModel sdm) {
    try {
      m_socketOut.write(sdm.toByteStream_V1());
      m_socketOut.flush();
      m_socketIn = new DataInputStream(m_smsSocket.getInputStream());
      m_socketIn.readFully(m_recvBuffer);
      RecvDataModel dm = RecvDataModel.parse_v1(m_recvBuffer);
      return dm;
    } catch (Exception e) {
      m_strLastMessage = "Exception occurs in submitMessage(" + e.getMessage() + ")";
      disconnectServer();
      return null;
    }
  }

  /**
   * 查詢簡訊傳送狀態
   * 
   * @param sdm 傳入SNS Server的參數物件, 包含受訊手機號碼以及message_id
   * @return null, 表示網路異常造成運作中斷, 呼叫getLastMessage()取得失敗原因; 否則透過回傳物件的getCode()與getDesc(), 判斷訊息的傳送狀態
   */
  public RecvDataModel qryMessageStatus(SubmitDataModel sdm) {
    try {
      m_socketOut.write(sdm.toByteStream_V1());
      m_socketOut.flush();
      m_socketIn = new DataInputStream(m_smsSocket.getInputStream());
      m_socketIn.readFully(m_recvBuffer);
      RecvDataModel dm = RecvDataModel.parse_v1(m_recvBuffer);
      return dm;
    } catch (Exception e) {
      m_strLastMessage = "Exception occurs in qryMessageStatus(" + e.getMessage() + ")";
      disconnectServer();
      return null;
    }
  }

  /**
   * 接收簡訊
   * 
   * @param sdm - 傳入SNS Server的參數物件
   * @return - null, 表示網路異常造成運作中斷, 呼叫getLastMessage()取得失敗原因; 如果執行成功, 透過回傳物件的getCode()與getDesc(),
   *         判斷訊息傳送是否成功
   */
  public RecvDataModel getMessage(SubmitDataModel sdm) {
    try {
      m_socketOut.write(sdm.toByteStream_V1());
      m_socketOut.flush();
      m_socketIn = new DataInputStream(m_smsSocket.getInputStream());
      m_socketIn.readFully(m_recvBuffer);
      RecvDataModel dm = RecvDataModel.parse_v1(m_recvBuffer);
      return dm;
    } catch (Exception e) {
      m_strLastMessage = "Exception occurs in getMessage(" + e.getMessage() + ")";
      disconnectServer();
      return null;
    }
  }

  /**
   * 中斷與SNS Server的連線
   */
  public void logout() {
    disconnectServer();
  }

}
