

## 개요
JDK17 + SpringBoot 3.2.x + Kotlin, 
H2 with R2DBC 를 사용했습니다.

## 저장소
https://github.com/aposto/shop-api-service

## 구현범위
전체 API는 구현을 했습니다.

다만, 브랜드 및 상품을 추가 / 업데이트 / 삭제하는 API
이부분에서 시간관계상 여러 제약과 정교한 에러 핸들링을 누락 했습니다.
우선은 예외시 에러메시지로 나가는 선에서 마감을 했습니다.

## 빌드
```
./gradlew build
```

## 테스트
모두 kotest로 작성 되어 있습니다.
일반 기능은 TDD
유스케이스는 BDD 사용했습니다.

```
./gradlew test
```

## 구동
### JDK17 이상 설치 되 있어야 합니다.
```
./gradlew bootRun
```
### Swagger
http://localhost:8080/swagger-ui.html


### H2 Schema
resource/db/h2/schema.sql 로 생성 합니다
resource/db/h2/data.sql 로 샘플 데이터 추가 합니다.
 



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
 
 
