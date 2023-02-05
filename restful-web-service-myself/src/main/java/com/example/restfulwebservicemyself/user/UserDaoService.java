package com.example.restfulwebservicemyself.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 사용자 서비스 클래스
 */
@Service //Bean의 용도: service역할 (@Component 로 해도 되긴 함)
public class UserDaoService {
    private static List<User> users = new ArrayList<>();


    private static int userCount =3;
    //DB에 3명의 사용자가 이미 들어가 있다고 가정하자.(아래의 상황이다.)
    static {
        users.add(new User(1, "Kenneth", new Date()));
        users.add(new User(2, "Alice", new Date()));
        users.add(new User(3, "Elena", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User save(User user) {
        if(user.getId() == null) {
            user.setId(++userCount);
        }

        users.add(user);
        return user;
    }

    public User findOne(int id) {
        for(User user : users){
            if(user.getId()==id){
                return user;
            }
        }
        return null; //not found
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while(iterator.hasNext()) {
            User user = iterator.next();

            if(user.getId()==id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }

    public User updateByIdAndUser(int id, User user) {
        User updateUser = findOne(id);

        if(updateUser!=null){
            updateUser.setName(user.getName());
            updateUser.setJoinDate(new Date());
            return updateUser;
        }
        return null;
    }
}
