package com.qortmdcks.cookie.repository;

import com.qortmdcks.cookie.payload.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepository {

    private final List<UserDto> userList = new ArrayList<>();

    public Optional<UserDto> findById(String id){
        return userList
                .stream()
                .filter(it ->{
                    return it.getId().equals(id);
                })
                .findFirst();
    }

    public Optional<UserDto> findByName(String name){
        return userList
                .stream()
                .filter(it ->{
                    return it.getName().equals(name);
                })
                .findFirst();
    }



    @PostConstruct  // Bean 초기화 되고 호출이 된다.
    public void start(){
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), // 랜덤한 아이디 생성 많이 사용하는 방식
                        "홍길동",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), // 랜덤한 아이디 생성 많이 사용하는 방식
                        "유관순",
                        "1234"
                )
        );
        userList.add(
                new UserDto(
                        UUID.randomUUID().toString(), // 랜덤한 아이디 생성 많이 사용하는 방식
                        "철수",
                        "1234"
                )
        );
    }
}
