<h2 align="center">GOPOS Cmm Cloud</h2>
<p align="center">고포스 Cmm Cloud 패키지입니다.</p>

## cmmcloud 사용시 주의점
- [cmmcloud](https://github.com/BazerHanMinSu/cmmcloud) 에서 git clone 받아서 사용
  - /src/main/java 디렉토리에서 git clone https://github.com/BazerHanMinSu/cmmcloud.git
  - 이후에 cmmcloud가 업데이트 될 때마다 해당 디렉토리로 이동해서 git pull


## Release History

---
> ## v1.0.0 (2022-11-17)
> 
> #### 추가 및 수정 내역:
> 
> - 초기 패키지 생성
> 
> #### 버그 픽스 내역:
> - 없음
> 
> ## v1.0.1 (2022-11-22)
>
> #### 추가 및 수정 내역:
>
> - 페이징 defaultRecordSize 20으로 설정
> - RestTemplate, Transaction Config 생성
> - ApiService 생성
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.2 (2022-12-06)
>
> #### 추가 및 수정 내역:
>
> - checkHeaderAndPutData 공통 메소드 생성
> - EtcError 예외 추가
>   - WRONG_STORE_UNQCODE
> - ApiService 생성
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.3 (2022-12-20)
>
> #### 추가 및 수정 내역:
>
> - checkHeaderAndPutData 메소드 권한, 메뉴코드도 가져오도록 수정
> - isDateTimeFormat 공통 메소드 생성
> - compareDateTime 공통 메소드 생성
> - EtcError 예외 추가
>   - IS_NOT_DATE_FORMAT
>   - TWO_VALUES_DIFFERENT_LENGTH
>   - START_DATE_GREATER_THAN_END_DATE
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.4 (2022-12-22)
>
> #### 추가 및 수정 내역:
>
> - getNowDe 공통 메소드 생성
> - getNowDt 공통 메소드 생성
> - AuthError 예외 추가
>   - WITHRAWAL_USER
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.5 (2023-01-05)
>
> #### 추가 및 수정 내역:
>
> - ReadableRequestWrapper 수정
>   - body를 obj로 한번 더 감싸던 방식 제거
> - 매장고유코드 key 변경
>   - storeUnqcode -> storeUnqCode
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.6 (2023-01-09)
>
> #### 추가 및 수정 내역:
>
> - 매장고유코드 key 변경
>   - storeUnqCode -> storeUnqcode
> - Header, Body, Param 입력받은 값 로그 추가
> #### 버그 픽스 내역:
> - 없음