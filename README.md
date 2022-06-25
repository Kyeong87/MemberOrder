# 회원 , 주문


## 사용 기술
- Java, Spring boot, JPA, Spring Security, jwt, MySQL, Redis, Swagger

## 시나리오
### 1. Spring boot 구동
- 1-1. ApplicationRunner
    - 초기 데이터 추가

- 1-2. schema.sql
    - Create Table

### 2. 회원 (MemberController)
- Spring Security + jwt 이용하여 로그인 기능 구현
- password -> PasswordEncoder 이용하여 암호화 처리

#### 2.1 login
- `로그인 API` 호출 -> response `토큰` 발급
#### example
```
//URL : POST /login
{
    "id":"pyk",
    "password":"test1234"
}
//response
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJweWsiLCJpYXQiOjE2NDAyMzcwNDAsImV4cCI6MTY0MDI0MDY0MH0.rnxMQv3Z3t5WtYGPjqRH5AYMluwXVz292DD9IWWLZus"
}
```

#### 2.2 join
- `토큰` 발급 제외

#### example
```
//URL : POST /join
{
    "id":"pyk1",
    "password":"test1234",
    "name":"박연경1",
    "nickName":"pyk1",
    "phone":"01098598733",
    "email":"test@test.com",
    "gender":"WOMAN"
}
//response
{
    "result": "pyk1"
}
```

#### 2.3 logout
- 토큰 header -> `X-AUTH-TOKEN` 추가 후 로그아웃 API 호출

#### example
```
//URL : POST /user-logout
{
    
}
//response
{
    "msg": "로그아웃 되었습니다."
}
```

#### 2.4 memberDetail(회원상세)
- 토큰 header -> `X-AUTH-TOKEN` 추가 후 회원상세 API 호출

#### example
```
//URL : GET /member/{memberId}
{
}
//response
[
    {
        "id": "pyk",
        "password": "{bcrypt}$2a$10$2tMWKc4AEnEYZ/BzmBha6uQoICcUY.aRTK4nM9IhM39ma6ZQYTrzy",
        "name": "박연경",
        "nickName": null,
        "phone": "01098598733",
        "email": "pykpyk8733@gmail.com",
        "gender": "WOMAN",
        "registerDate": null,
        "authorities": [
            {
                "authority": "ROLE_USER"
            }
        ],
        "enabled": true,
        "username": "박연경",
        "accountNonExpired": true,
        "accountNonLocked": true,
        "credentialsNonExpired": true
    }
]
```

#### 2.5 userGroup(전체회원조회)
- 토큰 header -> `X-AUTH-TOKEN` 추가 후 전체회원조회 API 호출
- 페이지네이션으로 일정 단위로 조회합니다.
- 이름, 이메일을 이용하여 검색 기능이 필요합니다.
- 각 회원의 마지막 주문 정보

#### example
```
//URL : GET /userGroup
{
    "name":"박연경",
    "nowPage":1,
    "pageSize":1
}
//response
{
    "data": {
        "userList": [
            {
                "id": "pyk",
                "memberName": "박연경",
                "nickName": "sponge",
                "phone": "01098598733",
                "email": "pykpyk8733@gmail.com",
                "gender": "WOMAN",
                "registerDate": null,
                "orderNumber": "20220623-N5WW",
                "orderName": "상품2",
                "orderRegisterDate": [
                    2022,
                    6,
                    24,
                    20,
                    35,
                    22
                ]
            }
        ],
        "pagination": {
            "pageSize": 1,
            "nowPage": 1,
            "totalCnt": 4
        }
    }
}
```

### 3. 주문 (OrderController)
- `로그인 API` 호출 -> `주문 API` 호출
- 로그인 API 에서 발급 받은 토큰 값을 header -> `X-AUTH-TOKEN` 추가 후 주문 API 호출

#### 3.1 회원 주문 조회

#### example
```
//URL : GET /order/{memberId}
{
}
//response
[
    {
        "id": 1,
        "orderNumber": "20220622-N5WW",
        "name": "상품1",
        "memberId": "pyk",
        "registerDate": [
            2022,
            6,
            23,
            20,
            49,
            9
        ]
    },
    {
        "id": 2,
        "orderNumber": "20220623-N5WW",
        "name": "상품2",
        "memberId": "pyk",
        "registerDate": [
            2022,
            6,
            24,
            20,
            49,
            9
        ]
    }
]
```

#### 3.2 주문 등록

#### example
```
//URL : POST /order/{memberId}
{
    "name":"상품4"
}
//response
{
    "message": "주문이 완료 되었습니다."
}
```