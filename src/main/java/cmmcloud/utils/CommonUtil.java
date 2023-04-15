package cmmcloud.utils;

import cmmcloud.enums.error.EtcError;
import cmmcloud.enums.error.RequestError;
import cmmcloud.response.ErrObj;
import cmmcloud.response.ResObj;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CommonUtil {

  private static final String[] dateFormats = {
      "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyyMMdd", "yyyy-MM", "yyyyMM"
  };

  /**
   * =================================================================
   *                 Object Null or Empty 여부 return
   * =================================================================
   *
   * 설명 : Object가 Null 혹은 Empty 인지 return
   *
   * 1. 예시코드
   *
   *    // Body에 storeCode가 들어있는지 검사
   *    if(CommonUtil.isNullOrEmpty(map.get("storeCode")){
   *      return ErrObj.error(RequestError.NOT_ENOUGH_BODY).getObject();
   *    }
   */
  public static boolean isNullOrEmpty(Object obj) {

    return obj == null || "".equals(obj);
  }


  /**
   * =================================================================
   *                           메뉴 권한 여부
   * =================================================================
   *
   * 설명 : 메뉴 권한을 가지지 못한 경우 true, 가진 경우 false return
   *
   * 1. 헤더에서 권한 가져오기
   *
   * 2. 메뉴코드가 포함되어 있는지 검사
   *
   * 3. 예시코드
   *
   *    // 1510004 메뉴권한 없음
   *    if(CommonUtil.notHasMenuRule(headers, 1510004)){
   *      return ErrObj.error(AuthError.UNAUTHORIZED_ACCESS).getObject();
   *    }
   */

  //메뉴 권한을 가지지 못한경우 true, 가진경우 false 반환
  public static boolean notHasMenuRule(HttpHeaders headers, int menuCode) throws ParseException {

    List<String> headerList = headers.get("GP-AUTH-RULE");

    String ruleStr = headerList.get(0);
    JSONParser parser = new JSONParser();
    JSONObject ruleJson = (JSONObject)parser.parse(ruleStr);

    ArrayList<Double> menuCodeList = (ArrayList<Double>)ruleJson.get("menuCode");
    return !menuCodeList.contains((double)menuCode);
  }

  /**
   * =================================================================
   *         헤더 체크 및 권한코드, 메뉴코드, 매장 고유코드, ID 삽입
   * =================================================================
   *
   * 설명 : 인증 권한, ID 헤더 체크 후 권한코드, 메뉴코드, 매장 고유코드, ID 삽입
   *
   * 1. 헤더에서 권한, ID 가져오기
   *
   * 2. 매장고유코드가 있는지 검사
   *
   * 3. 권한코드, 메뉴코드, 매장 고유코드 , ID를 Map에 넣고 리턴
   *
   * 3. 예시코드
   *
   *    ResObj checkResult = CommonUtil.checkHeaderAndPutData(headers, query);
   *    if(checkResult.hasError){
   *      return checkResult;
   *    }
   */
  public static ResObj checkHeaderAndPutData(HttpHeaders headers, HashMap map) throws ParseException {

    List<String> headerList = headers.get("GP-AUTH-RULE");
    List<String> userList = headers.get("GP-AUTH-ID");
    if (CommonUtil.isNullOrEmpty(headerList)
        || headerList.isEmpty()
        || CommonUtil.isNullOrEmpty(userList)
        || userList.isEmpty()){
      return ErrObj.error(RequestError.NOT_ENOUGH_HEADER);
    }

    String ruleStr = headerList.get(0);
    JSONParser parser = new JSONParser();
    JSONObject ruleJson = (JSONObject)parser.parse(ruleStr);

    log.info("GP-AUTH-RULE : " + ruleJson.toJSONString());
    log.info("GP-AUTH-ID : " + userList);
    log.info("Request Body & Param : " + map);

    String storeUnqcode = String.valueOf(ruleJson.get("storeUnqcode"));

    // TODO : instanceof 말고 다른 방법 찾기
    if (ruleJson.get("ruleCode") instanceof JSONArray){
      List ruleCodes = (JSONArray) ruleJson.get("ruleCode");
      map.put("ruleCode", ruleCodes);
    }

    if (ruleJson.get("menuCode") instanceof JSONArray) {
      List menuCodes = (JSONArray) ruleJson.get("menuCode");
      map.put("menuCode", menuCodes);
    }

    if (CommonUtil.isNullOrEmpty(storeUnqcode) || storeUnqcode.length() != 14 ) {
      return ErrObj.error(EtcError.WRONG_STORE_UNQCODE);
    }

    // body, param에 fchqCode, storeCode가 있는 경우
    // 중복되어 덮어써지므로 로그인한 유저의 정보를 넣지 않음
    boolean isExistsCode = map.containsKey("fchqCode") || map.containsKey("storeCode");
    if (!isExistsCode) {
      String fchqCode = storeUnqcode.substring(0, 7);
      String storeCode = storeUnqcode.substring(7, 14);
      map.put("storeUnqcode", storeUnqcode);
      map.put("fchqCode", fchqCode);
      map.put("storeCode", storeCode);
    }

    String userId = userList.get(0);
    map.put("creator", userId);
    map.put("updater", userId);

    return new ResObj(map);
  }

  /**
   * =================================================================
   *                     Date(DateTime) 포맷 검증
   * =================================================================
   *
   * 설명 : 날짜형식이 다음과 같은지 검사
   * -> yyyy-MM-dd HH:mm:ss
   * -> yyyy-MM-dd
   * -> yyyyMMdd
   *
   * 형식에 맞으면 true, 아니면 false
   *
   * 예시 코드)
   *    if (CommonUtil.isDateTimeFormat(body.get("startDe")) {
   *      return ErrObj.error(EtcError.IS_NOT_DATE_FORMAT).getObject();
   *    }
   */
  public static boolean isDateTimeFormat(Object obj) {

    String date = String.valueOf(obj);
    boolean isMatchFormat = false;

    for (String format : dateFormats) {
      try{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Year Month Format 검사
        if (date.length() <= 7) {
          YearMonth.parse(date, formatter);
        }
        // Date Format 검사
        else if (date.length() <= 10) {
          LocalDate.parse(date, formatter);
        }
        // DateTime Format 검사
        else {
          LocalDateTime.parse(date, formatter);
        }

        // Format 에러발생 안한 경우 break
        isMatchFormat = true;
        break;
      } catch (DateTimeParseException e) {
      }
    }

    return isMatchFormat;
  }

  /**
   * =================================================================
   *                   두 Date(DateTime) 데이터 비교
   * =================================================================
   *
   * 설명 : 첫번째 입력받은 값이 두번째 입력받은 값보다 작은지 비교
   *
   * 예시 코드)
   *    ResObj checkDateTime = CommonUtil.compareDateTime(body.get("startDe"), body.get("endDe"));
   *    if (checkDateTime.hasError) {
   *      return checkDataTime.getObject();
   *    }
   */
  public static ResObj compareDateTime(Object obj1, Object obj2) {

    if (!isDateTimeFormat(obj1) || !isDateTimeFormat(obj2)) {
      return ErrObj.error(EtcError.IS_NOT_DATE_FORMAT);
    }

    String date1 = String.valueOf(obj1);
    String date2 = String.valueOf(obj2);

    if (date1.length() != date2.length()) {
      log.error("입력받은 두 값이 길이가 다릅니다.");
      return ErrObj.error(EtcError.TWO_VALUES_DIFFERENT_LENGTH);
    }

    boolean isAfter = false;

    for (String format : dateFormats) {
      try{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        // Year Month 검사
        if (date1.length() <= 7) {
          YearMonth yearMonth1 = YearMonth.parse(date1, formatter);
          YearMonth yearMonth2 = YearMonth.parse(date1, formatter);
          isAfter = yearMonth1.isAfter(yearMonth2);
          break;
        }
        // Date Format 검사
        if (date1.length() <= 10) {
          LocalDate localDate1 = LocalDate.parse(date1, formatter);
          LocalDate localDate2 = LocalDate.parse(date2, formatter);
          isAfter = localDate1.isAfter(localDate2);
          break;
        }
        // DateTime Format 검사
        else {
          LocalDateTime localDateTime1 = LocalDateTime.parse(date1, formatter);
          LocalDateTime localDateTime2 = LocalDateTime.parse(date2, formatter);
          isAfter = localDateTime1.isAfter(localDateTime2);
          break;
        }
      } catch (DateTimeParseException e) {
      }
    }

    return isAfter ? ErrObj.error(EtcError.START_DATE_GREATER_THAN_END_DATE) : new ResObj();
  }

  /**
   * =================================================================
   *                    현재 일자 가져오기 (yyyyMMdd)
   * =================================================================
   *
   * 설명 : 현재 일자를 yyyyMMdd 포맷으로 int형으로 가져온다.
   *
   * 예시 코드)
   *    int nowDe = CommonUtil.getNowDe();
   */
  public static int getNowDe() {

    LocalDate now = LocalDate.now(ZoneId.of("Asia/Seoul"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

    return Integer.parseInt(now.format(formatter));
  }

  /**
   * =================================================================
   *               현재 시각 가져오기 (yyyy-MM-dd HH:mm:ss)
   * =================================================================
   *
   * 설명 : 현재 시각을 yyyy-MM-dd HH:mm:ss 포맷으로 String 형으로 가져온다.
   *
   * 예시 코드)
   *    String nowDt = CommonUtil.getNowDt();
   */
  public static String getNowDt() {

    LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    return now.format(formatter);
  }
}
