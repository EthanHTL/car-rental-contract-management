package com.example.carrentalcontract.aop;

import com.example.carrentalcontract.annotation.NotNull;
import com.example.carrentalcontract.exception.NotNullException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;


/**
 * @ClassName MethodAop
 * @Description //TODO
 * @Author admin
 * @date 2021/1/2 0:56
 * @Version 1.0
 **/
@Aspect
@Component
@Slf4j
public class NotNullAop {

    @Pointcut("@annotation(com.example.carrentalcontract.annotation.NotNull)")
    public void pointCut() {
    }
    @Before("pointCut()")
    public void beforeExecute(JoinPoint joinPoint){
        //参数类型
        //System.out.println(joinPoint.getArgs());
        //方法名
        //System.out.println(joinPoint.getSignature().getName());

        //拿到注解
        NotNull[] declaredAnnotationsByType = ((MethodSignature) joinPoint.getSignature()).getMethod().getDeclaredAnnotationsByType(NotNull.class);
        Object[] args = joinPoint.getArgs();
        if(CollectionUtils.isEmpty(Arrays.asList(declaredAnnotationsByType)) || CollectionUtils.isEmpty(Arrays.asList(args))){
            return ;
        }

        //如果不为空
        for (NotNull notNull : declaredAnnotationsByType) {
            int index = notNull.argIndex();
            if(index < 0 ){
                // throw exception;
                System.out.println("参数不合法");
            }else{
                Object arg = args[index];
                String field = notNull.field();
                if(!StringUtils.hasText(field)){
                    // field is null
                    throw new NotNullException(notNull.statusCode(),"field is not null");
                }else{

                    Field declaredField = null;
                    try {
                        declaredField = arg.getClass().getDeclaredField(notNull.field());
                        declaredField.setAccessible(true);
                        Object o = declaredField.get(arg);

                        boolean result = org.apache.commons.lang3.StringUtils.isBlank(o.toString());
                        if(result){
                            throw  new NotNullException(notNull.statusCode(),notNull.name()+"不能为空");
                        }
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    // field is not null
                    // is not possible




                }
            }
        }
        // System.out.println(joinPoint.getSignature().getClass().getDeclaredAnnotations());
    }


}
