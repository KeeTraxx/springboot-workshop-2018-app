package ch.puzzle.springboot.workshop.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SampleAspect {
    @Pointcut("execution(* ch.puzzle.springboot.workshop.example.controller.*.*(..))")
    public void pcut() {

    }

    @Around("pcut()")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("advice before " +joinPoint);

        Object obj = joinPoint.proceed();

        System.out.println("advice after " +joinPoint);
        return obj;
    }

    @Before("pcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("before " +joinPoint);
    }

    @After("pcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after " +joinPoint);
    }
}
