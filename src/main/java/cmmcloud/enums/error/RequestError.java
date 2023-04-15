package cmmcloud.enums.error;

import cmmcloud.enums.ErrorEnum;

public enum RequestError implements ErrorEnum {

  NOT_ENOUGH_BODY("0001", "Body의 필수요소가 부족합니다."),
  NOT_ENOUGH_QUERY("0002", "Query의 필수요소가 부족합니다."),
  NOT_ENOUGH_HEADER("0003", "Header의 필수요소가 부족합니다."),
  WRONG_API("0004", "잘못된 API 접근입니다."),
  NOT_ENOUGH_PID("0005", "PID값이 부족합니다."),
  NOT_ENOUGH_REQUIRED_FIELD("0006", "필수 전달 필드값이 부족합니다."),
  DEFAULT_ERROR("9999", "알수없는 에러입니다. 관리자에게 문의해주세요");

  private final String requestStatus = "802";
  private final String requestMessage = "요청에러: ";
  private final String status;
  private final String message;

  RequestError(String status, String message) {
    this.status = requestStatus + status;
    this.message = requestMessage + message;
  }

  @Override
  public String getStatus() {
    return status;
  }

  @Override
  public String getMessage() {
    return message;
  }

  @Override
  public String getError() {
    return this.toString();
  }
}
