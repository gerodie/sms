package com.example.sms.common;

public class GlobalVariables {

  public final static String COMMON_MSGID = "msgId";
  public final static String COMMON_PACKAGECNT = "packageCnt";
  public final static String COMMON_UID = "uId";
  public final static String COMMON_STATUSMSG = "statusMsg";

  public final static String REQUEST_CLIENTID = "clientId";
  public final static String REQUEST_SYSTIME = "sysTime";
  public final static String REQUEST_TIMEUP = "timeUp";
  public final static String REQUEST_EXPIRYMINUTES = "expiryMinutes";
  public final static String REQUEST_MOBILE = "mobile";
  public final static String REQUEST_SMSTEXT = "smsText";

  public final static String RESPONSE_TTCSTATUS = "ttcStatus";
  public final static String RESPONSE_ERRORUIDS = "errorUids";
  public final static String RESPONSE_UIDSTATUS = "uidStatus";

  public final static String ROUTING_STARTTIME = "02:00";
  public final static String ROUTING_ENDTIME = "06:20";

  public final static String ENTITY_OPID = "operatorId";

  public final static String DB_RT_RETURNMSG = "XXX";

  public final static String PASSWORD = "8K02C";

  public final static int PAYLOAD_MAX_COUNT = 500;

  public final static String RECEIVE_TRADEVAN_SUCCESS = "RD";

  public final static String TTC_SEND_WAIT = "W";
  public final static String TTC_SEND_PROCESS = "P";
  public final static String TTC_SEND_SUCCESS = "S";
  public final static String TTC_SEND_FAIL = "F";
  public final static String TTC_SEND_ERROR = "E";

  public final static String CALL_TWM_API = "http://localhost:8080/api/sendsms-twm/send.cgi";
  public final static String CALL_TTC_API =
      "http://localhost:8080/api/sendsms-twm/phasetwo-response";
  public final static String CALL_APT_API =
      "https://xsms.aptg.com.tw/XSMSAP/api/APIRTFastHttpRequest?MDN=&UID=&UPASS=";

  public final static String REQUEST_USERNAME = "username";
  public final static String REQUEST_PASSWORD = "password";
  public final static String REQUEST_RATEPLAN = "rateplan";
  public final static String REQUEST_SRCADDR = "srcaddr";
  public final static String REQUEST_DSTADDR = "dstaddr";
  public final static String REQUEST_ENCODING = "encoding";
  public final static String REQUEST_SMBODY = "smbody";
  public final static String REQUEST_DLVTIME = "dlvtime";
  public final static String REQUEST_VLDTIME = "vldtime";
  public final static String REQUEST_RESPONSE = "response";

  public final static String RESPONSE_MAGICID = "magicid";
  public final static String RESPONSE_MSGID = "msgid";
  public final static String RESPONSE_PHASE_II_DONE = "tccrd_sms_gateway_rpack";

  public final static String TELECOM_OPERATOR_TWM = "TW4";
  public final static String TELECOM_OPERATOR_APT = "AP4";
  public final static String TELECOM_OPERATOR_CHT = "CH4";

  public final static String SOCKET_IP = "211.79.42.33";// 另一組IP:203.66.172.133
  public final static int SOCKET_PORT = 8001;

  public final static String SERVER_ACCOUNT = "";
  public final static String SERVER_PASSWORD = "";
}
