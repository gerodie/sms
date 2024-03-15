package com.example.sms.common;

public enum ApiResponseState {

  RECEIVE_SUCCESS("0000", "接收成功"), ACCOUNT_ERROR("0001", "帳號或密碼錯誤"), SERVICE_UNAVAILABLE("0002",
      "暫時停止服務"), COUNT_ERROR("0003", "請確認packageCnt或smsBody筆數"), HANDLE_EXCEPTION("0004",
          "處理異常"), MSGID_DUPLICATE("0005", "MsgId資料重複"), DATA_NULL("0006",
              "請確認mobile或smsText是否有資料"), DATA_LENGTH("0007",
                  "請確認mobile或smsText資料長度"), UID_DUPLICATE("0008", "uId資料重複");

  String state;
  String description;

  ApiResponseState(String state, String description) {
    this.state = state;
    this.description = description;
  }

  public String getState() {
    return this.state;
  }

  public String getDescription() {
    return this.description;
  }

}
