package com.example.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.example.sms.entity.SmsCh4;

@Repository
public interface SmsCh4Repository extends JpaRepository<SmsCh4, String> {

  /**
   * 查詢發送狀態W(待TTC發送簡訊給業者)
   * 
   * @param clientId
   * @param msgId
   * @return
   */
  @Query(nativeQuery = true,
      value = "select * from sms_ch4 where client_id = :clientId and msg_id = :msgId and ttc_send_status = 'W'")
  public List<SmsCh4> findAllSendStatus(@Param("clientId") String clientId,
      @Param("msgId") String msgId);

  /**
   * 更新BulkSend TTC Status
   * 
   * @param op
   * @param client_id
   * @param msg_id
   */
  @Procedure(value = "usp_callResultCode")
  public void updateBulkSendTtcStatus(String op, String client_id, String msg_id);

  /**
   * 查詢 ClientId
   * 
   * @param msgId
   * @return
   */
  @Query(nativeQuery = true,
      value = "select client_id from sms_ch4 where msg_id = :msgId group by client_id")
  public String getClientIdByMsgId(String msgId);

  /**
   * 查詢主動查詢簡訊結果
   * 
   * @return
   */
  @Query(nativeQuery = true,
      value = "select * from SMS_CH4 ch4 where not exists("
          + "select 1 from SMS_CH4_Query query where query.Message_ID = ch4.Message_ID) "
          + "and ch4.TTC_Send_Status = 'S' and ch4.udt < getdate()")
  public List<SmsCh4> findStayQuerySmsResult();

  /**
   * 更新業者回傳第一階段資料
   * 
   * @param messageId
   * @param resCode
   * @param buffer
   * @param ttcSendStatus
   * @param clientId
   * @param msgId
   * @param uId
   */
  @Transactional
  @Modifying
  @Query(nativeQuery = true, value = "update sms_ch4 "
      + "SET message_id= :messageId, res_code= :resCode, buffer= :buffer, ttc_send_status= :ttcSendStatus "
      + "WHERE client_id= :clientId and msg_id= :msgId and u_id= :uId")
  public void updatePhaseIResponse(@Param("messageId") String messageId,
      @Param("resCode") String resCode, @Param("buffer") String buffer,
      @Param("ttcSendStatus") String ttcSendStatus, @Param("clientId") String clientId,
      @Param("msgId") String msgId, @Param("uId") String uId);

}
