package com.example.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.SmsAp4;

@Repository
public interface SmsAp4Repository extends JpaRepository<SmsAp4, String> {

  /**
   * 查詢發送狀態W(待TTC發送簡訊給業者)
   * 
   * @param clientId
   * @param msgId
   * @return
   */
  @Query(nativeQuery = true,
      value = "select * from sms_ap4 where client_id = :clientId and msg_id = :msgId and ttc_send_status = 'W'")
  public List<SmsAp4> findAllSendStatus(@Param("clientId") String clientId,
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
      value = "select client_id from sms_ap4 where msg_id = :msgId group by client_id")
  public String getClientIdByMsgId(String msgId);

}
