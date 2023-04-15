package cmmcloud.exception;

import cmmcloud.enums.error.ExceptionError;
import cmmcloud.response.ResObj;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler()
  public JSONObject handleException(Exception ex) {

    ex.printStackTrace();

    String errClass = ex.getClass().getSimpleName();

    ExceptionError exceptionError = ExceptionError.valueOfName(errClass);

    String status = exceptionError.getStatus();
    String errMsg = exceptionError.getMessage();

    ResObj resObj = new ResObj();
    resObj.setError(status, errMsg);
    resObj.setErrorCode(errClass);

    return resObj.getObject();
  }
}
