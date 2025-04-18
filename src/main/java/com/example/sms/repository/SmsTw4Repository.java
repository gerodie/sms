package com.example.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.SmsTw4;
import com.example.sms.entity.SmsTw4CompositeId;

@Repository
public interface SmsTw4Repository extends JpaRepository<SmsTw4, SmsTw4CompositeId> {

  /**
   * 查詢門號狀態W(業者路由分類)的資料
   * 
   * @param clientId
   * @param msgId
   * @return
   */
  @Query(nativeQuery = true,
      value = "select * from sms_tw4 where client_id = :clientId and msg_id = :msgId and ttc_send_status = 'W'")
  public List<SmsTw4> findAllBySendStatus(@Param("clientId") String clientId,
      @Param("msgId") String msgId);

  /**
   * 更新BulkSend TTC_STATUS狀態
   * 
   * @param client_id
   * @param msg_id
   * @param op
   */
  @Procedure(value = "usp_callResultCode")
  public void updateBulkSendTtcStatus(String op, String client_id, String msg_id);

  @Query(nativeQuery = true,
      value = "select client_id from sms_tw4 where msg_id = :msgId group by client_id")
  public String getClientIdByMsgId(String msgId);

}
