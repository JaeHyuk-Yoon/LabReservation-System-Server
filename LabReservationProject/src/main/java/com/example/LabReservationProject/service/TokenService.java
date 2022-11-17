package com.example.LabReservationProject.service;

import com.example.LabReservationProject.entity.Token;
import com.example.LabReservationProject.repository.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j //로깅 어노테이션
public class TokenService {
    @Autowired
    TokenRepository tokenRepository;

    //토큰 id는 무조건 1 ( findById(id) 하기위해)
    long id = 1;

    //토큰 조회
    public Token showToken() {

        return tokenRepository.findById(id).orElse(null);
    }

    //토큰 생성 or 변경 동시에 가능
    public Token createToken() {
        Token token = new Token(id, newValue());

        return tokenRepository.save(token);
    }

    //랜덤값 생성 함수
    private String newValue() {
        String value = "";
        String[] singleValue = new String[6];
        Random random = new Random();

        for(int i=0; i < singleValue.length; i++) {

            if(random.nextBoolean()) {
                singleValue[i] = Integer.toString(random.nextInt(10));
            }
            else {
                if(random.nextBoolean()) {
                    singleValue[i] = Character.toString((char) (random.nextInt(26)+65));
                }
                else {
                    singleValue[i] = Character.toString((char) (random.nextInt(26)+97));
                }
            }
            value = value.concat(singleValue[i]);
        }

        return value;
    }

    //id 1번 Token value를 null로 변경
    public void tokenToNULL() {
        Token token = tokenRepository.findById(id).orElse(null);
        token.setValue(null);
        tokenRepository.save(token);
    }
}
