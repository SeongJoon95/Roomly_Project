## 프로젝트 소개

**숙박 및 예약 관련 여행 사이트 입니다.**

**본 프로젝트는 IT코리아에서 배운 웹 개발 기술을 활용하여 응용하기 위해 만들어진 프로젝트입니다. 다양한 기능들에 대하여 프론트 및 벡엔드 기술을 실제로 적용하면서 전반적인 웹 개발 과정을 실습하며 배운 기술들을 응용하는것과 실무에서 사용할수 있는 기술들에 초첨을 두었습니다.**

**주제 선정 이유**

- 배운 기술을 응용하고 새로운 API 모듈을 활용하며, 팀원들과 역할이 겹치지 않는 협업을 통해 다양한 경험을 쌓을 수 있는 프로젝트를 고민하던 중, 복합적인 요구사항을 충족해야 하는 여행 및 숙박 예약 사이트가 기술적 성장과 사용자 중심의 서비스 설계를 동시에 경험할 수 있는 최적의 선택이라고 판단하여 선정하였습니다.

## 개발 환경

### IDE & Tools

![vsCode.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/167bc9ae-814b-4b31-8c6c-6a7795556417/vsCode.png)
(image.png)
VS Code

![postman.jpg](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/7f624c55-4f36-4ea1-ad58-af3794d387e5/postman.jpg)

Post Man

![workbench.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/a3bfa91b-a5b8-4b34-9213-64f76d594b73/workbench.png)

WorkBench

### Languages & Framework

---

![TS.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/eb66b882-19c2-4c8d-a898-ee58439d637b/TS.png)

TypeScript

![react.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/568639c5-bb5a-4774-9685-1b17480dd935/react.png)

React

![java.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/f968b659-e9c5-4bbe-b0f3-d0e17f3645ed/84a7f099-6c1d-415c-8b70-8d5ade1e9cd4.png)

Java

![css.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/608510e3-3165-4fe5-9146-3fdc18edc2ef/css.png)

CSS

![springBoot.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/4a56961a-fd3b-44ac-ba6f-031363331a31/springBoot.png)

Spring Boot

### Database Management System

---

![mysql.webp](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/820c24cc-118c-4c7f-8c8f-f214916ae5a1/mysql.webp)

MySql

### Version Control & Repository

---

![gitHub.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/c6242075-fb93-450c-8c7d-14aa7f7ed015/gitHub.png)

GitHub

### 외부 API

---

**결제 포트원**(https://portone.io/korea/ko),
**공공 데이터 포털API**(https://www.data.go.kr/),
**OAuth2 API**(KAKAO, GOOGLE),
**Google 클라우드 지도 api**

## 📌 팀

---

**안성준(Back-End)**

- 요구사항, API 명세서 작성
- ERD 및 DB 계정 생성 관리
- 게스트 및 관리자 관련 API 구현
- OAuth2 API 연동
- 결제 (포트윈) API 연동 및 구현

**박현우(Back-End)**

- 요구사항 작성
- 호스트및 관리자 관련 API 구현
- ERD 설계
- 사업자 진위 확인 여부 API 연동 및 구현

**이소진(Front-End)**

- 숙소 검색 리스트 / 상세 리스트 페이지 등
- 와이어프레임 작성
- 리액트 라이브러리 활용, 향상된 UI/UX 및 상태관리

**옥진서(Front-End)**

- 회원가입,마이페이지 관련 component 제작
- 마이페이지 및 회원가입 제작
- 프론트 백앤드 연동

**김나연(Front-End)**

- 회원가입 페이지, 아이디/비밀번호 찾기 페이지 구현
- 결제 페이지 구현
- 전체 페이지 CSS

## 📌 시연 영상 및 주요기능 소개

### ✅ ERD

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/c2cbaae7-732b-4118-8489-ca519cc3606e/image.png)

- 총 12개의 테이블로 구성되어 있으며 중복해서 사용하는 테이블은 연두색으로 표시 하였으며, 게스트는 분홍색 호스트는 파란색으로 표시하였습니다.

### ✅ 로그인 및 회원가입

- 카카오 연동하여 회원가입 및 로그인 하였습니다.

[회원가입및 로그인(kakao,게스트).mp4](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/e4a10662-c9a6-4be4-9a1e-06cb4ec00566/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85%EB%B0%8F_%EB%A1%9C%EA%B7%B8%EC%9D%B8(kakao%EA%B2%8C%EC%8A%A4%ED%8A%B8).mp4)

### ✅ 마이페이지

**비밀번호 변경 및 전화번호 인증 및 변경**

- 현재 비밀번호를 입력하고 변경할 비밀번호를 입력하여 변경 클릭시 Id에 대한 비밀번호가 변경되며 다시 로그인 할수 있도록 구성하였습니다.
- 전화번호는 기존의 전화번호를 불러와 보여주며 변경을 클릭하고 새로운 전화번호를 입력하고 번호에 대한 인증번호를 입력하고 변경을 클릭하면 로그아웃후 다시 로그인 할수 있도록 하였습니다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/c5e19b47-8e9c-4a96-b6ab-41098e763b7b/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/c486d425-2b88-4a46-a4a2-c15aa794e8eb/image.png)

**즐겨찾기**

- 즐겨찾기 되어 있지 않은 경우 내역이 없다는 문구와 메인페이지로 이동이 가능합니다.
- 즐겨찾기가 되어 있을경우 해당 숙소의 대표이미지와 숙소이름, 지역을 나타내며 버튼을 클릭하여 해당 숙소의 상세페이지로 이동이 가능합니다.

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/4242575e-d0eb-4303-9f66-14fd672855aa/image.png)

![image.png](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/ff5c5e5f-3909-465c-a0ba-da568450f1d3/image.png)

### ✅ 숙소 및 객실 등록하기 (Host)

[숙소 및 객실등록.mp4](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/dca3f131-d073-4eaf-b693-ae086c0cdaf6/%EC%88%99%EC%86%8C_%EB%B0%8F_%EA%B0%9D%EC%8B%A4%EB%93%B1%EB%A1%9D.mp4)

- 관리자의 승인을 받은 guest에 한해서 숙소 등록할수 있도록 하였습니다.
- 숙소가 등록이 된 상태에서 관리자의 승인이 있어야 게스트가 보는 숙소 리스트에서 등록된 숙소를 확인할 수 있습니다.

### ✅ 결제 및 예약내역확인

[결제및 예약내역.mp4](https://prod-files-secure.s3.us-west-2.amazonaws.com/1f5d4b6d-f15b-41fc-9626-aafe35d7fa23/430e5dad-7759-4ab6-a0b3-732c932b4124/%EA%B2%B0%EC%A0%9C%EB%B0%8F_%EC%98%88%EC%95%BD%EB%82%B4%EC%97%AD.mp4)

- 메인화면에서 지역과 체크인 체크아웃 날짜, 인원을 검색하면 해당 조건에 부합하는 숙소list를 확인할수 있습니다.
- 원하는 숙소를 클릭하여 숙소상세보기에서 원하는 객실을 선택하여 결제 페이지로 이동합니다.
- 게스트의 이름과 전화번호를 불러오며 다양한 결제 방식을 선택하여 결제 가능합니다.
- 결제후 마이페이지의 예약내역을 확인하면 해당 숙소명과 객실명 입퇴실시간, 가격등을 확인할 수 있으며 리뷰 작성도 가능합니다.

## 📌 상세 내용

### 기능

- **회원가입 및 로그인**
    - **OAuth2**(kakao, naver) **API 연동 및 구현**
    - **파일 업로드 → 폴더에 파일 저장가능(사업자 등록증 이미지 저장시)**
    - **사업자 진위여부 API**
- **회원정보 수정**
    - **게스트**
        - 개인정보 보기 및 수정
        - 리뷰 작성(예약후 가능)
        - 즐겨찾기
        - 내가 작성한 리뷰 list 보기, 예약 현황 보기
    - **호스트**
        - 개인정보 보기 및 수정
        - 숙소등록(승인후) 및 수정
        - 예약 현황보기
    - **관리자**
        - 게스트 정보보기
        - 호스트 정보보기 및 회원가입
        - 숙소등록 승인
- **결제(포트원) API 연동 및 구현**
- **검색기능**
- **필터기능**
    - 지역별 숙소 리스트 출력 가능
    - 카테고리별 숙소 리스트 출력 가능
- 숙소 상세 리스트 및 숙소 안 객실 정보 및 리뷰 구현 가능
- 박수(n박n일)별 객실 이용가격 출력 가능
- 호스트 승인 요청 및 승인된 호스트에 한정 숙소등록 가능

## 프로젝트 결과 및 후기

---

### **안성준**

> **느낀점**
: 이번 프로젝트를 통해 **소통의 중요성**을 다시 한번 깊이 깨닫게 되었습니다. 프로젝트 초반에는 충분히 대화했다고 생각했지만, 서로의 이해가 달라 **의도와 다른 결과물**이 나오는 경우가 종종 있었습니다. 이로인해 **불필요한 시간 낭비**가 발생하기도 했으며, 결과적으로 목표한 기간 내에 프로젝트를 마무리하지 못하는 상황을 겪어야 했습니다. 이러한 경험은 저에게 아쉬움과 동시에 많은 배움을 주었습니다.
앞으로는 프로젝트를 시작하기 전에 **명확한 기준을 설정**하고, 진행 사항과 과정을 팀원 간 말로만 공유하는 것이 아니라 **체계적으로 문서화**해야 한다는 필요성을 확실히 느꼈습니다. 이를 통해 팀 내 **소통 오해를 최소**화하고, 프로젝트의 효율성과 완성도를 높일 수 있을 것이라 믿습니다.

**이슈사항
<문제>:** 프론트와 벡 연동하여 결제 api를 사용하는데 있어서 프론트에서 결제 자체가 되지 않는 문제와 결제가 완료 되어도 벡에 값이 넘어가지 않는 어려움이 있었습니다.

**<해결> :** 해당 라이브러리를 제공해주는 사이트의 공식 문서를 참조하거나 구글 검색 및 chatGPT를 이용하여 문제를 해결 하였습니다. 정말 모르겠던 부분은 강사님께 질문을 통해 알게 되었습니다. 프론트와 벡의 연동되어 있는 부분이 있다보니 개발자 환경도구를 통해 값이 잘 넘어갔는지 error가 발생했는지 파악하고 수정하는 방식으로 문제를 해결하였습니다.

**프로젝트 이후 ( ~ing)
:** 완벽하게 마무리 하지 못하였기에 수료가 끝난 이후 저는 ****제가 맡았던 부분 이외에 다른 부분을 보며 왜 이렇게 코드를 짰는지 이해하게 됫으며, 중복된 코드가 있는 경우 하나의 api로 통일 하며 유지보수과 코드 간결성을 위해 노력하고 있습니다.
>