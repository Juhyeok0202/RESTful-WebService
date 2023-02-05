package com.example.restfulwebservicemyself.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// HTTP Status code
// 2xx -> OK
// 4xx -> Client잘못 쓰었을 댸
// 5xx -> Server측 문제

/**
 * 사용자(Clinet)가 요청했던 데이터 값이 리소스가 존재하지 않았을 경우에는
 * 5xx 에러가 아니라, HttpStatus에 NotFound라는 값을 지정하도록 처리를 하였음.
 */
@ResponseStatus(HttpStatus.NOT_FOUND) //5xx 에러가 아니라 4xx대인 클라이언트 측 오류로
public class UserNotFoundException extends RuntimeException {
    //RuntimeException으로 해서 실행시 발생하는 오류가 실행이 됨.
    public UserNotFoundException(String message) {
        super(message); //super-class : RuntimeException
    }
}
