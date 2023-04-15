package cmmcloud.vo;

import cmmcloud.utils.CommonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashMap;
import lombok.Data;

@Data
public class PagingVO {

    /**
     * ========================================================================
     *                               페이징 처리
     * ========================================================================
     *
     * 설명 : 서버에서 연산을 최소화 하기 위해 총 데이터 개수, 총 페이지 개수만 처리한다.
     *
     * 1. PagingVO를 호출하기 전 총 데이터 개수를 구한다.
     *    - 총 데이터 개수가 0이하면 기본값 return
     *
     * 2. recordSize를 통해 총 페이지 개수 계산
     *    - recordSize 입력받지 않았을 경우 페이지 1개로 처리
     *
     * 3. 서비스단에서 컨트롤러로 ResObj를 리턴할때 다음과 같이 사용한다.
     *
     *    예시코드)
     *
     *    PagingVO pagingVO = new PagingVO(총 데이터 개수, 화면에서 입력받은 map);
     *    return new ResObj(pagingVO, 데이터);
     */

    private int totalDataCnt = 0;       // 총 데이터 개수
    private int totalPageCnt = 1;       // 총 페이지 개수
    @JsonIgnore
    private int defaultRecordSize = 20; // 레코드 개수 지정되지 않았을 때 기본값

    public PagingVO(int totalDataCnt, HashMap map) {

        if (totalDataCnt <= 0) {
            return;
        }

        if (CommonUtil.isNullOrEmpty(map.get("recordSize"))){
            this.totalDataCnt = totalDataCnt;
            this.totalPageCnt = ((totalDataCnt - 1) / defaultRecordSize) + 1;
            return;
        }

        int recordSize = Integer.valueOf((String) map.get("recordSize"));

        this.totalDataCnt = totalDataCnt;
        this.totalPageCnt = ((totalDataCnt - 1) / recordSize) + 1;
    }
}
