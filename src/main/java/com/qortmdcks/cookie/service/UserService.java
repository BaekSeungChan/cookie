package com.qortmdcks.cookie.service;


import com.qortmdcks.cookie.payload.LoginRequest;
import com.qortmdcks.cookie.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    // login logic
    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();


            if(userDto.getPassword().equals(pw)){
                // cookie 해당 정보를 저장
                var cookie = new Cookie("authorization-cookie",userDto.getId());
                cookie.setDomain("localhost");  // naver.com , daum.net  dev.xxx.com, << production.xxx.com
                cookie.setPath("/");
                cookie.setHttpOnly(true); // 쿠키는 취약점을 가지고 있다. setHttpOnly(true)를 하면 자바스크립트에 해당 값을 읽을 수 없게 해준다.
                //cookie.setSecure(true); // << https 에서만 사용되도록 설정
                // 실제 업무를 할 때는 setHttpOnly와 setSecure을 설정해 준다.
                cookie.setMaxAge(-1);   // session

                httpServletResponse.addCookie(cookie);

            }
        }else{
            throw new RuntimeException("User Not Found");
        }

    }
}