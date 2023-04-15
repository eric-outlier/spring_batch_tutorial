package cmmcloud.response;

import cmmcloud.enums.ErrorEnum;

public class ErrObj {

  /**
   * ====================================================================
   *                          에러 관련 시나리오
   * ====================================================================
   *
   * 1. cmm/enums/error 패키지에 Exception 및 API 응답 에러가 정의되어있다.
   *
   * 2. controller, service단에서 에러를 return해야 할 경우에 아래의 코드를 참고한다.
   *      // ex) RequestError의 쿼리 파라미터가 부족한 경우
   *      return ErrObj.error(RequestError.NOT_ENOUGH_QUERY).getErrorObject();
   *
   * 3. 모든 Error 관련 Enum은 ErrorEnum 인터페이스를 상속받고 있기 때문에, ErrorEnum형으로 파라미터를 전달받는다.
   *
   * 4. 각 Enum에 status, message가 정의되어 있기 때문에 인터페이스의 getStatus, getMessage로 값을 가져온다.
   *
   * 5. ResObj 객체에 status, message를 할당하고 return
   */

  // 에러타입 분류
  public static ResObj error(ErrorEnum errorEnum) {

    ResObj resObj = new ResObj();
    resObj.setError(errorEnum.getStatus(), errorEnum.getMessage());
    resObj.setErrorCode(errorEnum.getError());
    return resObj;
  }
}
