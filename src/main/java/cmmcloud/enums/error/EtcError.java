package cmmcloud.enums.error;

import cmmcloud.enums.ErrorEnum;

public enum EtcError implements ErrorEnum {

  NONE_SELECT_DATA("0001", "조건에 맞는 데이터가 없습니다"),
  WRONG_STORE_UNQCODE("0002", "매장 고유코드가 잘못되었습니다"),
  IS_NOT_DATE_FORMAT("0003", "날짜 형식이 맞지 않습니다."),
  TWO_VALUES_DIFFERENT_LENGTH("0004", "입력받은 두 날짜의 길이가 다릅니다."),
  START_DATE_GREATER_THAN_END_DATE("0005", "시작 날짜가 끝 날짜보다 큽니다."),
  DEFAULT_ERROR("9999", "알수없는 에러입니다. 관리자에게 문의해주세요");

  private final String etcStatus = "899";
  private final String etcMessage = "Error Occurred: ";
  private final String status;
  private final String message;

  EtcError(String status, String message) {
    this.status = etcStatus + status;
    this.message = etcMessage + message;
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
