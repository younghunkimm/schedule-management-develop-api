# 일정 관리 API - Develop

Spring Boot 일정 관리 API 입니다.  
사용자는 인증/인가를 통해 일정 및 댓글을 생성, 조회, 수정, 삭제할 수 있습니다.  
QueryDSL을 활용한 일정의 동적 검색과 페이징 기능이 구현되어 있습니다.

## 📅 개발 기간

2025.08.05 ~ 2025.08.12

## 🛠 기술 스택

- [x] Java 17
- [x] Spring Boot 3.5.4
- [x] Spring Data JPA
- [x] QueryDSL 5.0.0
- [x] MySQL
- [x] Swagger (springdoc-openapi)
- [x] Lombok
- [x] Jakarta Validation

## 📑 주요 기능

### 1. 회원 관리
- 회원 가입
- 로그인 (세션 기반 인증, `Filter` 활용)
- 로그아웃
- 회원 조회, 수정, 삭제(Soft Delete)

### 2. 일정 관리
- 일정 작성, 수정, 삭제(Soft Delete)
- 일정 페이징 조회(댓글 수 포함)
- 일정 단건 조회(댓글 리스트 포함)

### 3. 댓글 관리
- 댓글 작성, 수정, 삭제(Soft Delete)

### 4. 검색 & 페이징
- `QueryDSL` 기반 동적 검색 조건
- `Pageable` 기반 페이징 처리
- 수정일 기준 내림차순 정렬

### 5. 예외 처리
- 도메인별 ErrorCode Enum 정의
- 공통 예외 처리 Handler
- Validation 예외 메세지 커스터마이징

### 6. API 문서화
- `Swagger UI` 제공 (`/swagger-ui/index.html`)
- OpenAPI 3 스펙 기반 자동 문서화

## 🗂 패키지 구조

```bash
schedulemanagementdevelopapi
├── comment                     # === 댓글 Domain ===
│   ├── controller              # 댓글 API 엔드포인트
│   ├── dto                     # 댓글 요청/응답 DTO
│   │   ├── request
│   │   └── response
│   ├── entity                  # 댓글 엔티티
│   ├── exception               # 댓글 관련 예외 코드
│   ├── policy                  # 댓글 정책 정의
│   ├── repository              # 댓글 JPA/QueryDSL 저장소
│   └── service                 # 댓글 서비스 인터페이스/구현체
│
├── global                      # === 전역 설정 ===
│   ├── config                  # 전역 설정 (Swagger, QueryDSL, 필터 등)
│   ├── dto                     # 공통 응답 DTO
│   │   └── response
│   ├── entity                  # 공통 엔티티 (BaseEntity, SoftDelete 등)
│   ├── exception               # 전역 예외 및 에러 코드
│   └── filter                  # 전역 필터 (LoginFilter 등)
│
├── member                      # === 회원 Domain ===
│   ├── controller              # 회원 API 엔드포인트
│   ├── dto                     # 회원 요청/응답 DTO
│   │   ├── request
│   │   └── response
│   ├── entity                  # 회원 엔티티
│   ├── exception               # 회원 관련 예외 코드
│   ├── policy                  # 회원 정책 정의
│   ├── repository              # 회원 JPA/QueryDSL 저장소
│   └── service                 # 회원 서비스 인터페이스/구현체
│
└── schedule                    # === 일정 Domain ===
    ├── controller              # 일정 API 엔드포인트
    ├── dto                     # 일정 요청/응답 DTO
    │   ├── request
    │   └── response
    ├── entity                  # 일정 엔티티
    ├── exception               # 일정 관련 예외 코드
    ├── policy                  # 일정 정책 정의
    ├── repository              # 일정 JPA/QueryDSL 저장소
    └── service                 # 일정 서비스 인터페이스/구현체
```

## 🔑 ERD 다이어그램

<img width="862" height="435" alt="sparta-schedule-management-develop2" src="https://github.com/user-attachments/assets/d9964903-64b5-4afb-a3ba-b39ffc16c33a" />

## 📄 API 명세서 요약

### 일정

| Method | Path | Summary | Parameters |
|--------|------|---------|------------|
| GET | `/schedules` | 일정 목록(댓글 수 포함) 조회 | `page`, `size`, `title` |
| POST | `/schedules` | 일정 생성 | - |
| GET | `/schedules/{scheduleId}` | 일정 단건(댓글 리스트 포함) 조회 | - |
| PATCH | `/schedules/{scheduleId}` | 일정 수정 | - |
| DELETE | `/schedules/{scheduleId}` | 일정 삭제 | - |

### 댓글

| Method | Path | Summary | Parameters |
|--------|------|---------|------------|
| POST | `/schedules/{scheduleId}/comments` | 댓글 생성 | - |
| PATCH | `/comments/{commentId}` | 댓글 수정 | - |
| DELETE | `/comments/{commentId}` | 댓글 삭제 | - |

### 회원

| Method | Path | Summary | Parameters |
|--------|------|---------|------------|
| POST | `/members/signup` | 회원가입 | - |
| POST | `/members/login` | 로그인 | - |
| POST | `/members/logout` | 로그아웃 | - |
| GET | `/members` | 유저 전체 조회 | `name`, `email` |
| GET | `/members/{memberId}` | 유저 단건 조회 | - |
| PATCH | `/members/{memberId}` | 유저 정보 수정 | - |
| DELETE | `/members/{memberId}` | 유저 삭제 | - |


