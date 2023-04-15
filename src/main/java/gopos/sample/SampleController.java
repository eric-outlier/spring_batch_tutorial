package gopos.sample;

import cmmcloud.enums.error.RequestError;
import cmmcloud.response.ErrObj;
import cmmcloud.response.ResObj;
import cmmcloud.utils.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import gopos.sample.service.SampleService;

import java.util.HashMap;

@Slf4j
@Api(tags = "Test", description = "Test API")
@RestController
@RequestMapping("/test")
public class SampleController {

  @Autowired
  SampleService sampleService;

  @ApiOperation(value = "Mybatis HashMap 테스트", notes = "Mybatis HashMep select 테스트 입니다.")
  @ApiImplicitParams({
      @ApiImplicitParam(
          name = "test",
          value = "테스트 값",
          required = true,
          dataType = "String",
          paramType = "query",
          example = "test",
          dataTypeClass = String.class)
  })
  @GetMapping("/hashmap")
  public JSONObject getTest(String test) {

    if (CommonUtil.isNullOrEmpty(test)) {
      return ErrObj.error(RequestError.NOT_ENOUGH_QUERY).getObject();
    }

    return sampleService.getTest().getObject();
  }

  @ApiOperation(value = "Mybatis VO 테스트", notes = "Mybatis VO select 테스트 입니다.")
  @ApiImplicitParams({
      @ApiImplicitParam(
          name = "code",
          value = "코드 그룹",
          required = true,
          dataType = "String",
          paramType = "query",
          example = "1",
          dataTypeClass = String.class)
  })
  @GetMapping("/vo")
  public JSONObject getVOTest(String code) {

    if (CommonUtil.isNullOrEmpty(code)) {
      return ErrObj.error(RequestError.NOT_ENOUGH_QUERY).getObject();
    }

    return sampleService.getVOTest(code).getObject();
  }

  @GetMapping("/paging")
  public JSONObject getPagingTest(@RequestParam HashMap<String, Object> query) {

    return sampleService.getPagingTest(query).getObject();
  }
}
