package cmmcloud.enums.error;

import cmmcloud.enums.ErrorEnum;

public enum DaoError implements ErrorEnum {

  DATA_NOT_CREATED("0001", "데이터 생성에 실패 했습니다."),
  DATA_NOT_UPDATED("0002", "데이터 수정에 실패 했습니다."),
  DATA_NOT_DELETED("0003", "데이터 삭제에 실패 했습니다."),
  DEFAULT_ERROR("9999", "알수없는 에러입니다. 관리자에게 문의해주세요");

  private final String daoStatus = "803";
  private final String daoMessage = "DAO 에러: ";
  private final String status;
  private final String message;

  DaoError(String status, String message) {
    this.status = daoStatus + status;
    this.message = daoMessage + message;
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
