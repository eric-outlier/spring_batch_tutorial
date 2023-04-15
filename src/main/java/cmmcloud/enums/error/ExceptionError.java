package cmmcloud.enums.error;

import cmmcloud.enums.ErrorEnum;
import cmmcloud.utils.CommonUtil;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum ExceptionError implements ErrorEnum {

  BadSqlGrammarException("0001", "SQL 문법 오류입니다."),
  DataAccessResourceFailureException("0002", "Database 연결에 실패 했습니다."),
  DataIntegrityViolationException("0003", "데이터 제약 조건을 위배 했습니다."),
  DuplicatedKeyException("0004", "중복된 입력값입니다."),
  NullPointerException("0005", "Null값이 발생 했습니다."),
  NumberFormatException("0006", "숫자로 타입변환이 불가능합니다."),
  MissingServletRequestParameterException("0007", "필수 Parameter 값이 없습니다."),
  BadRequest("0008", "잘못된 요청입니다."),
  Unauthorized("0009", "권한이 없습니다."),
  InternalServerError("0010", "서버 Error가 발생 했습니다."),
  ReactiveException("0011", "ReactiveException가 발생 했습니다."),
  HttpMediaTypeNotSupportedException("0012", "지원하지 않는 Method 요청입니다."),
  NoSuchMessageException("0013", "메세지를 확인 할 수 없습니다."),
  MyBatisSystemException("0014", "Mybatis 에러 발생 했습니다."),
  IllegalArgumentException("0015", "부적절한 인자가 입력 되었습니다."),
  AmazonSimpleEmailServiceException("0016", "Amazon SES 에러입니다."),
  UnsupportedOperationException("0017", "Byte변환중 에러가 발생했습니다."),
  MissingRequestHeaderException("0018", "API요청에 필요한 Header를 찾을 수 없습니다."),
  HttpMessageNotReadableException("0019", "API요청에 필요한 Body를 읽을 수 없습니다."),
  ClassCastException("0020", "클래스 캐스팅 에러가 발생 했습니다."),

  DEFAULT_ERROR("9999", "알수없는 에러입니다. 관리자에게 문의해주세요");

  private final String exceptionStatus = "801";
  private final String exceptionMessage = "Exception: ";
  private final String status;
  private final String message;

  ExceptionError(String status, String message) {
    this.status = exceptionStatus + status;
    this.message = exceptionMessage + message;
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

  private static Map<String, ExceptionError> ByName = Stream.of(values())
      .collect(Collectors.toMap(ExceptionError::getError, e -> e));

  public static ExceptionError valueOfName(String err) {
    return CommonUtil.isNullOrEmpty(ByName.get(err)) ? DEFAULT_ERROR : ByName.get(err);
  }

}
