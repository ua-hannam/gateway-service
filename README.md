# **Gateway Service Project**

## **개요**

이 프로젝트는 단순화된 게이트웨이 서비스를 구현하는 것을 목표로 합니다.<br>
현재는 기본 구조만 구현된 상태이며, 추후 이 게이트웨이는 팀의 중심적인 서비스 라우팅 역할을 담당할 예정입니다.

## **프로젝트 구조**

```css
.
├── build
│   ├── classes
│   │   └── java
│   │       └── main
│   │           └── uahannam
│   │               └── gatewayservice
│   ├── resources
│   └── tmp
├── src
│   ├── main
│   │   ├── java
│   │   │   └── uahannam
│   │   │       └── gatewayservice
│   │   └── resources
│   └── test
│       └── java
│           └── uahannam
│               └── gatewayservice
├── build.gradle
├── gradlew
├── gradlew.bat
└── settings.gradle
```

- **src**: 소스 코드 디렉토리. 여기에 모든 Java 코드와 리소스 파일이 포함됩니다.
    - **main/java**: 실제 어플리케이션 코드가 위치합니다.
    - **main/resources**: 설정 파일 (예: **`application.yml`**)이 위치합니다.
    - **test/java**: 테스트 코드가 위치합니다.
- **build**: 빌드된 클래스와 리소스 파일이 위치하는 디렉토리.
- **gradlew**, **gradlew.bat**: Gradle 래퍼 스크립트로, 시스템에 Gradle이 설치되어 있지 않아도 프로젝트를 빌드할 수 있게 해줍니다.

## **기능**

현재 프로젝트는 다음 기능을 포함합니다:

- 기본적인 게이트웨이 라우팅 설정.
- 요청 및 응답 로깅을 위한 **`DefaultFilter`**.

## **빌드 및 실행**

Gradle을 사용하여 프로젝트를 빌드하고 실행할 수 있습니다:

```bash
./gradlew build
./gradlew bootRun
```