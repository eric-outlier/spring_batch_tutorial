package cmmcloud.enums.error;

import cmmcloud.enums.ErrorEnum;

public enum AuthError implements ErrorEnum {

  INCORRECT_AUTH_CODE("0001", "인증코드가 불일치 합니다."),
  FORBIDDEN_WORD("0002", "사용금지 단어가 포함되어 있습니다."),
  DUPLICATED_ID("0003", "중복된 아이디 입니다."),
  DUPLICATED_EMAIL("0004", "중복된 이메일 입니다."),
  NOT_VERIFIED_EMAIL("0005", "인증되지 않은 이메일 입니다."),
  NONE_USER("0006", "존재하지 않는 사용자 입니다."),
  NONE_EMAIL("0007", "존재하지 않는 이메일 입니다."),
  INCORRECT_PASSWORD("0008", "비밀번호가 일치하지 않습니다."),
  INVALID_TOKEN("0009", "토큰이 유효하지 않습니다."),
  EXPIRED_TOKEN("0010", "만료된 토큰입니다."),
  SEND_EMAIL_RETRY_1MIN("0011", "1분 이내에는 재인증이 불가능 합니다."),
  FAILED_WITHDRAWAL("0012", "회원탈퇴를 실패 했습니다."),
  INVALID_USERTYPE("0013", "유효하지 않은 회원타입 입니다."),
  NONE_EMAIL_CODE("0014", "인증 이메일코드가 없습니다."),
  UNAUTHORIZED_ACCESS("0015", "허가되지 않은 접근입니다."),
  LOGIN_AUTHENTICATION_REQUIRED("0016", "로그인(인증)이 필요합니다."),
  NOT_ALLOW_ACCESS("0016", "허가되지 않은 접근입니다."),
  SAME_PREV_PASSWORD("0017", "이전 비밀번호와 같습니다."),
  WITHRAWAL_USER("0018", "탈퇴한 유저입니다."),
  DEFAULT_ERROR("9999", "알수없는 에러입니다. 관리자에게 문의해주세요");

  private final String authStatus = "804";
  private final String authMessage = "Auth 에러: ";
  private final String status;
  private final String message;

  AuthError(String status, String message) {
    this.status = authStatus + status;
    this.message = authMessage + message;
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
