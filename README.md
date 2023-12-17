

## 개요
JDK17 + SpringBoot 3.2.x + H2 with R2DBC 를 사용했습니다.

 
## 구동
### JDK17 이상 설치 되 있어야 합니다.

### H2 Schema
resource/db/h2/schema.sql 로 생성 합니다
resource/db/h2/data.sql 로 샘플 데이터 추가 합니다.
 

### Swagger
http://localhost:8080/swagger-ui.html
 

## Architecture
* 클린아키텍쳐를 지향합니다.
* 헥사고날 아키텍처를 지행하여 도메인, 비즈니스로직, 어댑터, 인프라스트럭쳐를 분리합니다.
* 도메인에는 최대한 기술적 요소를 배제 합니다.
* 비즈니스로직이 중심 도메인에서 벗어나, 퍼시스턴스나, 어댑터, 인프라스트럭쳐에 존재 하지 않게 합니다.
* 순환참조를 배제하고 외부로직이 내부로 행하는 구조를 만듭니다.
* 내부에서는 의존성 역전으로 포트를 통한 인터페이싱을 합니다.
* REST API 명세를 지향합니다.

## 패키지 구조

## Test
* TDD 를 지향 합니다.
* Mock 객체와 UseCase 를 테스트하는 통합 테스트를 수행합니다. (시간 상 누락 했습니다)
* BDD 를 사용한 인수테스트와 비즈니스 로직 테스트를 수행합니다
 
## 에러처리
브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
이부분에서 시간관께상 여러 제약과 정교한 에러 핸들링을 누락 했습니다.
우선은 예외시 에러메시지로 나가는 선에서 마감을 했습니다.

## 후기
직접 쿼리로 해결하느냐, 간접적인 데이터를 쿼리후 가공하느냐 고민이 되는 과제 였습니다.
아마도 실 서비스에서는 Redis나 MongoDB 등에 매트러얼라이즈 한 상태로 처리 할것 같습니다.

좋은 결과가 있었으면 좋겠습니다.
감사합니다.
