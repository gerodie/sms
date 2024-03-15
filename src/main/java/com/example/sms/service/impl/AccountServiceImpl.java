package com.example.sms.service.impl;

import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.sms.common.Encryptor;
import com.example.sms.entity.Account;
import com.example.sms.repository.AccountRepository;
import com.example.sms.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

  private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);

  @Autowired
  private AccountRepository accountRepository;

  @Override
  public boolean validateAccountInfo(String username, String password) {
    log.info("start validateAccountInfo");
    boolean isAccountExist = true;
    try {
      Optional<Account> account = accountRepository.findById(username);
      if (ObjectUtils.isEmpty(account)) {
        return isAccountExist = false;
      }
      String accountPwd = account.get().getPassword();
      if (!ObjectUtils.isEmpty(accountPwd)) {
        Encryptor encryptor = new Encryptor();
        String deCodeString = encryptor.getDecryptorPwd(password);
        if (!deCodeString.equals(accountPwd)) {
          return isAccountExist = false;
        }
      }
    } catch (Exception e) {
      log.error(e);
    }
    return isAccountExist;
  }

}
