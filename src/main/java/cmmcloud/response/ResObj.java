package cmmcloud.response;

import cmmcloud.vo.PagingVO;
import org.json.simple.JSONObject;

public class ResObj {

  public Boolean hasError;
  public Boolean hasPaging;
  private String status;
  private Object resultObj;
  private PagingVO pageObj;
  private Object errorCode;

  /**
   * 기본 성공 상태
   */
  public ResObj() {
    this.hasError = false;
    this.hasPaging = false;
    this.status = "200";
    this.resultObj = "Success";
  }

  /**
   * 생성과 동시에 성공값 세팅
   * @param result
   */
  public ResObj(Object result) {
    this.hasError = false;
    this.hasPaging = false;
    this.status = "200";
    this.resultObj = result;
  }

  /**
   * 생성과 동시에 성공값 세팅 및 페이징 정보 추가
   * @param result
   */
  public ResObj(PagingVO pagingVO, Object result) {
    this.hasError = false;
    this.hasPaging = true;
    this.status = "200";
    this.pageObj = pagingVO;
    this.resultObj = result;
  }

  /**
   * 에러 정보 세팅
   * @param status
   * @param result
   */
  public void setError(String status, Object result) {
    this.hasError = true;
    this.hasPaging = false;
    this.status = status;
    this.resultObj = result;
  }

  /**
   * 에러코드값 추가
   * @param code
   */
  public void setErrorCode(String code) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("code", code);
    this.errorCode = jsonObject;
  }

  /**
   * return new ResObj().getObject
   * return new ResObj(데이터).getObject
   * return new ResObj(페이징VO, 데이터).getObject
   * @return
   */
  public JSONObject getObject() {
    JSONObject resJson = new JSONObject();
    resJson.put("status", status);
    resJson.put("results", resultObj);

    if (hasError){
      resJson.put("error", errorCode);
    }

    if (hasPaging){
      resJson.put("pageInfo", pageObj);
    }

    return resJson;
  }
}
