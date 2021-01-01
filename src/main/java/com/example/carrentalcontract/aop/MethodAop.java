package com.example.carrentalcontract.aop;

import com.example.carrentalcontract.annotation.NotNull;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

/**
 * @ClassName MethodAop
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 0:56
 * @Version 1.0
 **/
@Aspect
@Component
public class MethodAop {

    @Pointcut("execution(public * com.example.carrentalcontract.controller.*..*(..))")
    public void pointCut() {

    }
    @Before("pointCut()")
    public void beforeExecute(JoinPoint joinPoint){
        //参数类型
        System.out.println(joinPoint.getArgs());
        //方法名
        System.out.println(joinPoint.getSignature().getName());

        //拿到注解
        NotNull[] declaredAnnotationsByType = ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaredAnnotationsByType(NotNull.class);
        Object[] args = joinPoint.getArgs();
        if(CollectionUtils.isEmpty(Arrays.asList(declaredAnnotationsByType)) || CollectionUtils.isEmpty(Arrays.asList(args))){
            return ;
        }

        //如果不为空
        for (NotNull notNull : declaredAnnotationsByType) {
            String index = notNull.index();
            Integer integer = Integer.valueOf(index);
            if(integer < 0 ){
                // throw exception;
                System.out.println("参数不合法");
            }else{
                Object arg = args[integer];
                String field = notNull.Field();
                if(!StringUtils.hasText(field)){
                    // field is null
                    throw new RuntimeException("参数非法");
                }else{
                    //field is not null
                    // is not possible
                    boolean result = org.apache.commons.lang3.StringUtils.isBlank(Optional.ofNullable(arg).orElseThrow(() -> new RuntimeException("参数非法")).toString());
                    if(result){
                        throw  new RuntimeException("参数非法");
                    }
                }
            }
        }
        System.out.println(joinPoint.getSignature().getClass().getDeclaredAnnotations());
    }


}
