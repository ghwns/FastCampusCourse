package com.example.filter.aop;

import com.example.filter.model.UserRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.Objects;

@Aspect
@Component
public class TimerAOP {

    @Pointcut(value = "within(com.example.filter.controller.UserApiController)") // Spring 관리범위 내 Bean만 적용 가능
    public void timerPointCut(){}

    @Before(value = "timerPointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("Before");
    }

    @After(value = "timerPointCut()")
    public void after(JoinPoint joinPoint){
        System.out.println("After");
    }

    @AfterReturning(value = "timerPointCut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        System.out.println("After Returning");
    }

    @AfterThrowing(value = "timerPointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e){
        System.out.println("After Throwing");
    }


    @Around(value = "timerPointCut()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("메소드 실행 이전");

        Arrays.stream(joinPoint.getArgs()).forEach(
                it -> {
                    System.out.println(it);
                    if(it instanceof UserRequest){
                        var tempUser = (UserRequest) it;
                        var phoneNumber = tempUser.getPhoneNumber().replace("-", "");
                        tempUser.setPhoneNumber(phoneNumber);
                    }
                }
        );

        System.out.println("시간 재기 이전2");
        // 암,복호화 or 로깅
        var newObj = Arrays.asList(
                new UserRequest()
        );

        var stopWatch = new StopWatch();
        stopWatch.start();
        joinPoint.proceed(newObj.toArray());

        stopWatch.stop();
        System.out.println("total = " + stopWatch.getTotalTimeMillis());

        System.out.println("메소드 실행 이후");
    }
}
