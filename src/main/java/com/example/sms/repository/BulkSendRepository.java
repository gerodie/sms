package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.BulkSend;
import com.example.sms.entity.BulkSendCompositeId;

@Repository
public interface BulkSendRepository extends JpaRepository<BulkSend, BulkSendCompositeId> {

  @Query(nativeQuery = true, value = "select dbo.fn_RT(:phoneNumber)")
  public String getOperatorId(@Param("phoneNumber") String phoneNumber);

  @Query(nativeQuery = true, value = "select count(1) from sms_bulksend where msg_id = :msgId")
  public int checkMsgIdExist(@Param("msgId") String msgId);

  @Query(nativeQuery = true, value = "select count(1) from sms_bulksend where u_id = :uId")
  public int checkuIdExist(@Param("uId") String uId);

  @Procedure(value = "usp_splitRT")
  public void bulkSendDispatchForOperator(String client_id, String msg_id);

}
