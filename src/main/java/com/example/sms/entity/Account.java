package com.example.sms.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sms_account")
public class Account implements Serializable {

  private static final long serialVersionUID = 7454134977284927134L;

  @Id
  @Column(name = "username", nullable = false, length = 100)
  private String username;

  @Column(name = "password", nullable = false, length = 300)
  private String password;

  @Column(name = "company", nullable = false, length = 100)
  private String company;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCompany() {
    return company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return "Account [username=" + username + ", password=" + password + ", company=" + company
        + "]";
  }

}
