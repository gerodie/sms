package com.example.sms.common;

import org.jasypt.util.text.BasicTextEncryptor;

public class Encryptor {

  public String getEncryptorPwd(String password) {
    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    textEncryptor.setPassword(GlobalVariables.PASSWORD);
    return textEncryptor.encrypt(password);
  }

  public String getDecryptorPwd(String password) {
    BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
    textEncryptor.setPassword(GlobalVariables.PASSWORD);
    return textEncryptor.decrypt(password);
  }

}
