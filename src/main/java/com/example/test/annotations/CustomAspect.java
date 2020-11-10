package com.example.test.annotations;

import com.example.test.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class CustomAspect {
    private final UserRepository userRepository;

    public CustomAspect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Around("@annotation(LogMethod)")
    public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext()
                        .getAuthentication();

        Long id = userRepository.findByName(authentication.getName()).get().getId();

        log.info("Method:" + joinPoint.getSignature().getName()
                + ", user id: " + id + " - start");

        Object proceed = joinPoint.proceed();

        log.info("Method:" + joinPoint.getSignature().getName()
                + ", user id: " + id + " - end");
        return proceed;
    }
}
