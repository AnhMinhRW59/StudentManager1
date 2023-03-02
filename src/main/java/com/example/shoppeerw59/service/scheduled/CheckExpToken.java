package com.example.shoppeerw59.service.scheduled;

import com.example.shoppeerw59.modal.entity.Token;
import com.example.shoppeerw59.repository.Specification.TokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;


@Component
@Slf4j
public class CheckExpToken {
    private static final long EXPIRATION_TIME = 864000000; //10 days

    @Autowired
    private TokenRepository tokenRepository;

    @Scheduled(cron = "* * 1 * * *")
    public void checkExpTokenJob(){
        log.info("Time to run log: ", new Date());
        List<Token> tokensExp = tokenRepository.findAllByExpirationIsAfter(new Date(System.currentTimeMillis() + EXPIRATION_TIME));
        tokenRepository.deleteAll();
        log.info("Number token was delete: {}", tokensExp.size());
    }
}
