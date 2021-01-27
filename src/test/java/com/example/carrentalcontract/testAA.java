package com.example.carrentalcontract;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @description:
 * @author: 黄天亮
 * @create: 2021-01-27 11:39
 **/
@Slf4j
@SpringBootTest
public class testAA {

    @Test
    public void testOne(){
        int a = 44;
        System.out.println(a << 1);
        System.out.println(a >> 2);
    }

}
