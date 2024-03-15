package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.BulkSendError;

@Repository
public interface BulkSendErrorRepository extends JpaRepository<BulkSendError, Integer> {

}
