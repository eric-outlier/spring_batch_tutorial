package cmmcloud.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class ApiService<T> {

  @Autowired
  private RestTemplate restTemplate;

  @Value("${api.base.url}")
  private String baseUrl;

  public ResponseEntity<T> callApiEndPoint(String url, HttpMethod httpMethod,
      HttpHeaders httpHeaders, Object body, Class<T> clazz) {

    if (httpHeaders == null) {
      HttpHeaders headers = new HttpHeaders();
      headers.set("Content-Type", "application/json");
      headers.set("Accept", "application/json");

      httpHeaders = headers;
    }

    ResponseEntity<T> result = restTemplate.exchange(
        baseUrl + url, httpMethod, new HttpEntity<>(body, httpHeaders), clazz);
    log.info("Call Url => " + (baseUrl + url));
    log.info("StatusCode => " + result.getStatusCode());
    log.info("StatusCodeValue => " + result.getStatusCodeValue());
    log.info("ResultBody => " + result.getBody().toString());

    return result;
  }

  /**
   * ================ GET Api call ================
   */

  public ResponseEntity<T> get(String url) {
    return callApiEndPoint(url, HttpMethod.GET, null, null, (Class<T>) Object.class);
  }

  public ResponseEntity<T> get(String url, HttpHeaders httpHeaders) {
    return callApiEndPoint(url, HttpMethod.GET, httpHeaders, null, (Class<T>) Object.class);
  }

  public ResponseEntity<T> get(String url, HttpHeaders httpHeaders, Class<T> clazz) {
    return callApiEndPoint(url, HttpMethod.GET, httpHeaders, null, clazz);
  }

  /**
   * ================ POST Api call ================
   */

  public ResponseEntity<T> post(String url, Object body) {
    return callApiEndPoint(url, HttpMethod.POST, null, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> post(String url, HttpHeaders headers, Object body) {
    return callApiEndPoint(url, HttpMethod.POST, headers, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> post(String url, HttpHeaders headers, Object body, Class<T> clazz) {
    return callApiEndPoint(url, HttpMethod.POST, headers, body, clazz);
  }

  /**
   * ================ PUT Api call ================
   */

  public ResponseEntity<T> put(String url, Object body) {
    return callApiEndPoint(url, HttpMethod.PUT, null, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> put(String url, HttpHeaders headers, Object body) {
    return callApiEndPoint(url, HttpMethod.PUT, headers, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> put(String url, HttpHeaders headers, Object body, Class<T> clazz) {
    return callApiEndPoint(url, HttpMethod.PUT, headers, body, clazz);
  }

  /**
   * ================ PATCH Api call ================
   */

  public ResponseEntity<T> patch(String url, Object body) {
    return callApiEndPoint(url, HttpMethod.PATCH, null, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> patch(String url, HttpHeaders headers, Object body) {
    return callApiEndPoint(url, HttpMethod.PATCH, headers, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> patch(String url, HttpHeaders headers, Object body, Class<T> clazz) {
    return callApiEndPoint(url, HttpMethod.PATCH, headers, body, clazz);
  }

  /**
   * ================ DELETE Api call ================
   */

  public ResponseEntity<T> delete(String url, Object body) {
    return callApiEndPoint(url, HttpMethod.DELETE, null, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> delete(String url, HttpHeaders headers, Object body) {
    return callApiEndPoint(url, HttpMethod.DELETE, headers, body, (Class<T>) Object.class);
  }

  public ResponseEntity<T> delete(String url, HttpHeaders headers, Object body, Class<T> clazz) {
    return callApiEndPoint(url, HttpMethod.DELETE, headers, body, clazz);
  }
}
