package com.example.restfulwebservicemyself.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    // GET
    // /hello-world (endpoint)
    // @RequestMapping(methodRequestMethod.GET, path="/") 기존 방식 최근은 GetMapping()
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    //alt + enter
    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        //java bean형태로 반환하면, json형태로 변환해서 반환.
        //responsebody에 저장하지않더라도 @RestController가 json으로 반환해줌
        return new HelloWorldBean("Hello World");
    }

    // 가변데이터 url -> pathvariable 이용해서 가변데이터 활용
    //{}에 사용되는 값은 @PathVarible을 통해 패스베이어블에 사용되는 값임을 지정.(가변데이터로 사용 될 것 입니다.)
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World, %s", name));
    }

}
