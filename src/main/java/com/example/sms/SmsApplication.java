package com.example.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories({"com.example.sms.*"})
@ComponentScan({"com.example.sms.*"})
@EntityScan({"com.example.sms.entity"})
@EnableJpaAuditing
public class SmsApplication {

  public static void main(String[] args) {
    SpringApplication.run(SmsApplication.class, args);
  }

}
