package com.example.sms.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.SmsAp4;

@Repository
public interface SmsAp4QueryRepository extends JpaRepository<SmsAp4, String> {

  /**
   * 查詢主動查詢簡訊結果
   * 
   * @param ttcSendStatus
   * @param udt
   * @return
   */
  @Query(nativeQuery = true,
      value = "select ap4.* from SMS_AP4 ap4 where not exists(select 1 from SMS_AP4_Query query "
          + "where query.Message_ID = ap4.Message_ID and query.Recv_Msisdn = ap4.Rcv_Msisdn) "
          + "and ap4.TTC_Send_Status = 'S' and ap4.udt < getdate()")
  public List<SmsAp4> findAllByUdt();
}
