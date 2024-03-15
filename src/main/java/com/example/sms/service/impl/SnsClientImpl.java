package com.example.sms.service.impl;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import com.example.sms.dto.RecvDataModel;
import com.example.sms.dto.SubmitDataModel;

/**
 * 實現SNS Protocol的類別繼承使用(中華電信提供)
 */
public abstract class SnsClientImpl {

  protected String m_strSnsIp = "";
  protected int m_nSnsPort = 0;
  protected byte[] m_recvBuffer = null;
  protected OutputStream m_socketOut = null;
  protected DataInputStream m_socketIn = null;
  protected Socket m_smsSocket = null;
  protected String m_strLastMessage = "";

  public abstract boolean login(SubmitDataModel sdm);

  public abstract RecvDataModel submitMessage(SubmitDataModel sdn);

  public abstract RecvDataModel qryMessageStatus(SubmitDataModel sdm);

  public abstract RecvDataModel getMessage(SubmitDataModel sdm);

  public abstract void logout();

  public SnsClientImpl(String ip, int port) {
    m_strSnsIp = ip;
    m_nSnsPort = port;
  }

  /**
   * 回傳上次函示執行失敗的錯誤訊息
   * 
   * @return 上次函示執行失敗的錯誤訊息
   */
  public String getLastMessage() {
    return m_strLastMessage;
  }

  protected boolean connectServer() {
    boolean bRet = true;
    try {
      if ((m_smsSocket == null) || m_smsSocket.isClosed() == true) {
        m_smsSocket = new Socket(m_strSnsIp, m_nSnsPort);
        m_smsSocket.setSoTimeout(10000);
        m_socketOut = m_smsSocket.getOutputStream();
        m_socketIn = new DataInputStream(m_smsSocket.getInputStream());
        bRet = true;
      }
    } catch (IOException e) {
      m_strLastMessage = "Connect server fail(" + e.toString() + ")";
      disconnectServer();
      bRet = false;
    }
    return bRet;
  }

  protected void disconnectServer() {
    try {
      if (m_smsSocket != null)
        m_smsSocket.close();
      m_smsSocket = null;
      if (m_socketOut != null)
        m_socketOut.close();
      m_socketOut = null;
      if (m_socketIn != null)
        m_socketIn.close();
      m_socketIn = null;
    } catch (IOException e) {
      m_strLastMessage = "disconnect server fail(" + e.toString() + ")";
    }
  }

}
