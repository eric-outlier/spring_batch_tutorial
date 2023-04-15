package gopos.sample.service;

import cmmcloud.enums.error.EtcError;
import cmmcloud.response.ErrObj;
import cmmcloud.response.ResObj;
import cmmcloud.utils.CommonUtil;
import cmmcloud.vo.PagingVO;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gopos.sample.dao.SampleDAO;
import gopos.sample.vo.SampleVO;

@Service
public class SampleService {

  @Autowired
  SampleDAO sampleDAO;

  // Get Sample
  public ResObj getTest() {

    return new ResObj(sampleDAO.getTest());
  }

  // VO Return Sample
  public ResObj getVOTest(String noticePid) {

    SampleVO sampleVO = sampleDAO.getVOTest(noticePid);

    if (CommonUtil.isNullOrEmpty(sampleVO)) {
      return ErrObj.error(EtcError.NONE_SELECT_DATA);
    }

    return new ResObj(sampleVO);
  }

  // Paging Sample
  public ResObj getPagingTest(HashMap<String, Object> map) {

    int cmCodeCnt = sampleDAO.getAllCmCodeCnt(map);
    List cmCodes = sampleDAO.getAllCmCode(map);

    PagingVO pagingVO = new PagingVO(cmCodeCnt, map);

    return new ResObj(pagingVO, cmCodes);
  }

}
