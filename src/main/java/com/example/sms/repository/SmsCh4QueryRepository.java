package com.example.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.sms.entity.SmsCh4Query;

@Repository
public interface SmsCh4QueryRepository extends JpaRepository<SmsCh4Query, String> {

}
