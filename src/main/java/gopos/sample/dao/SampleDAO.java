package gopos.sample.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import gopos.sample.vo.SampleVO;

@Mapper
public interface SampleDAO {

  List<HashMap<String, String>> getTest();

  SampleVO getVOTest(String noticePid);

  int getAllCmCodeCnt(HashMap map);

  List<HashMap<String, String>> getAllCmCode(HashMap map);
}
