<h2 align="center">GOPOS Backend Skeleton</h2>
<p align="center">고포스 백엔드 스켈레톤 프로젝트입니다.</p>

## Preferences

---
| JAVA      | SpringBoot | Mysql |
|-----------|------------|-------|
| OpenJDK11 | 2.6.11     | 8     |

## 스켈레톤 프로젝트 사용시 주의점
- [gopos-backend-gopos Release](https://github.com/BazerHanMinSu/gopos-backend-skeleton/releases) 에서 다운받아서 사용
  - cmmcloud 패키지는 개발자가 수정 절대 금지 (cmm패키지에 프로젝트에 필요한 config같은 공통 클래스 만들어서 사용) 
  - 프로젝트명이 `skeleton`이기 때문에 반드시 사용할 프로젝트명으로 몇가지 수정 필요

    <details>
      <summary>메인 클래스명 변경</summary>
      <div markdown="1">
        사용할 프로젝트명으로 메인 클래스 변경<br>
        ex) GoPosApplication => GoPosRuleApplication

    ![메인 클래스명 변경](https://user-images.githubusercontent.com/109120978/191654531-bcedfb44-0dfc-4573-be87-b1d0b252fb36.png)
      </div>
    </details>

    <details>
      <summary>pom.xml 변경</summary>
      <div markdown="1">
        프로젝트 기본 초기 속성 변경<br>
        ex) skeleton ⇒ rule      

    ![pom변경](https://user-images.githubusercontent.com/109120978/191654533-02f53228-8a00-4ef6-b5a1-f8fab628961d.png)
      </div>
    </details>

    <details>
      <summary>appication.properties 환경변수 변경</summary>
      <div markdown="1">
        DB관련 옵션 및 추후 추가될 비공개 값들은 환경변수로 관리한다.<br>
        담당자에게 관련 값을 전달받은 후 IntelliJ의 Edit Configuration에서 설정 후 진행한다.
      </div>
    </details>

## Default Folder Tree

---
- etc
  - sql
  - uml
    - class
    - erd
    - interface
    - sequence
    - usecase
- src
  - main
    - java
      - cmm
        - [각 프로젝트에 필요한 공통단 작성]
      - cmmcloud
        - config
        - enums
        - exception
        - filter
        - response
        - utils 
      - gopos
        - [API명]
          - dao
          - service
          - vo
          - API 컨트롤러
        - GoPos[프로젝트]Application.java

## Release History

---
> ## v1.0 (2022-09-22)
> 
> #### 추가 및 수정 내역:
> 
> - 초기 프로젝트 생성
> 
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.1 (2022-10-05)
>
> #### 추가 및 수정 내역:
>
> - AuthError Enum 신규 옵션 추가 및 Status 코드 804로 변경
> - EtcError Enum 사용 안하는 옵션들 제거
> - 최상위 디렉토리에 etc폴더 생성
>   - sql : 테이블 SQL 파일 폴더
>   - uml
>     - class : 클래스 다이어그램 폴더
>     - erd : ERD 다이어그램 폴더
>     - interface : 인터페이스 다이어그램 폴더
>     - sequence : 시퀀스 다이어그램 폴더
>     - usecase : Usecase 다이어그램 폴더
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.2 (2022-10-27)
>
> #### 추가 및 수정 내역:
>
> - properties -> yml 파일로 변경
> - 샘플 테이블 수정으로 인한 Controller, Service, VO, Mapper 변경
>
> #### 버그 픽스 내역:
> - 없음
> ## v1.0.3 (2022-11-09)
>
> #### 추가 및 수정 내역:
>
> - Github Actions 및 Dockerfile 추가
>
> #### 버그 픽스 내역:
> - 없음
> 
> ## v1.0.4 (2022-11-17)
>
> #### 추가 및 수정 내역:
>
> - ExceptionError 항목 추가 및 수정
>   - MissingRequestHeaderException
>   - HttpMessageNotReadableException
>   - ClassCastException
> - 페이징 공통단 추가
>   - PagingVO
> - 페이징 공통단 추가에 따른 ResObj 오버로딩 추가
>   - ResObj(PagingVO, 데이터)
> - Mybatis Null인 컬럼 출력 안되도록 수정
>
> #### 버그 픽스 내역:
> - 없음
> 
> ## v1.0.5 (2023-01-10)
>
> #### 추가 및 수정 내역:
>
> - Properties API Base Url 추가
> - MybatisConfig alias 항목들 추가
> - cmmcloud 공통단 업데이트
> - pom.xml artifactId 항목 gopos-backend로 변경
>
> #### 버그 픽스 내역:
> - 없음