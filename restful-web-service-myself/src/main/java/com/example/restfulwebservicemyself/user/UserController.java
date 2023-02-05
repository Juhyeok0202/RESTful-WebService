package com.example.restfulwebservicemyself.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    //new로 생성하는 것이 아니라, 의존성 주입으로 생성해야함.
    private UserDaoService service;

    public UserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // GET /users/1 or /users/10 ->1,10 이라고 전달해도 서버측(controller)에서는 String으로 전달 됨.
    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        //Exception Handling: 서버에 존재하지 않는 데이터에 접근 시 예외 처리
        if(user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return user;
    }

    /**
     * uri를 반환하면 왜 status code는 201(created)을 상태코드로 넘겨줄까?
     * 201 -> 성공과 동시에 새로운 resource가 생성되었다는 의미 이므로,
     */
    //status code를 created인 201로 받기 위해 uri를 반환해준다.(GET mathod와 다른 상태코드 사용하자)
    // post mathod의 실행 결과값으로써 생성된 id를 전달받게 되면
    // -> network트래픽 감소 + 조금 더 효율적인 app 완성 (응답코드값을 핸들링 해야함)(201)
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = service.save(user);

        // 사용자한테 요청 값을 변환하기 위해 클래스 사용
        // 지금 받아온 request사용 -> 반환시켜주고자 할 때 path지정 -> 가변변수에 지정 -> uri형태로 변경
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        // 지금 만들어진 uri값을 반환
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User user){
        User updateUser = service.updateByIdAndUser(id,user);

        if(updateUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        return updateUser;
    }
}
//    private Integer id;
//    private String name;
//    private Date joinDate;
