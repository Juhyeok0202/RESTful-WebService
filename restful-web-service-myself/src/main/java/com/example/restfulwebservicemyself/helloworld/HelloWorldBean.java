package com.example.restfulwebservicemyself.helloworld;
// lombok

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // setter,getter,constructor, tostring(모든 프로퍼티에 대해서)
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldBean {
    private String message; //json의 키값
}

//domain? domain지식 인간 활동 영역이라던가 , 자율적인
// 컴퓨터 활동과 같은 어떤 특정한 전문 분야에서 활용하는 업무 지식