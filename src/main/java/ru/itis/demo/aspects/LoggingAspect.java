package ru.itis.demo.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* ru.itis.demo.service..*.*(..)))")
    public void logService(JoinPoint joinPoint) {

        LocalDateTime date = LocalDateTime.now();

        StringBuilder builder = new StringBuilder();

        builder.append("(");
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            builder.append(joinPoint.getArgs()[i].toString());

            if (i < joinPoint.getArgs().length - 1) {
                builder.append(",");
            }
        }
        builder.append(")");

        log.info(date.toString()
                + ": "
                + joinPoint.getTarget().getClass().getName()
                + " called method "
                + joinPoint.getSignature().getName()
                + " with arguments: "
                + builder.toString());
    }
}
