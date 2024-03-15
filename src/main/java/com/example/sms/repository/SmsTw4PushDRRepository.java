package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.SmsTw4PushDR;

@Repository
public interface SmsTw4PushDRRepository extends JpaRepository<SmsTw4PushDR, String> {

}
