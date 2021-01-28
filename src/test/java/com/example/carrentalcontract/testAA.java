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
    public void testBitOperation(){
        System.out.println("44                二进制：" + Integer.toBinaryString(44));
        System.out.println("44 << 1:十进制：" + (44 << 1) + " 二进制：" + Integer.toBinaryString(44 << 1));
        System.out.println("44 << 2:十进制：" + (44 << 2) + " 二进制：" + Integer.toBinaryString(44 << 2));
        System.out.println("44 << 2:十进制：" + (44 << 3) + " 二进制：" + Integer.toBinaryString(44 << 3));
        System.out.println();
        System.out.println("44 >> 1:十进制：" + (44 >> 1) + " 二进制：" + Integer.toBinaryString(44 >> 1));
        System.out.println("44 >> 2:十进制：" + (44 >> 2) + " 二进制：" + Integer.toBinaryString(44 >> 2));
        System.out.println("44 >> 3:十进制：" + (44 >> 3) + " 二进制：" + Integer.toBinaryString(44 >> 3));
        System.out.println();

        System.out.println("-44     :二进制：" + Integer.toBinaryString(-44));
        System.out.println("-44 << 1:十进制：" + (-44 << 1) + " 二进制：" + Integer.toBinaryString(-44 << 1));
        System.out.println("-44 << 2:十进制：" + (-44 << 2) + " 二进制：" + Integer.toBinaryString(-44 << 2));
        System.out.println("-44 << 3:十进制：" + (-44 << 3) + " 二进制：" + Integer.toBinaryString(-44 << 3));
        System.out.println();
        System.out.println("-44 >> 1:十进制：" + (-44 >> 1) + " 二进制：" + Integer.toBinaryString(-44 >> 1));
        System.out.println("-44 >> 2:十进制：" + (-44 >> 2) + " 二进制：" + Integer.toBinaryString(-44 >> 2));
        System.out.println("-44 >> 3:十进制：" + (-44 >> 3) + " 二进制：" + Integer.toBinaryString(-44 >> 3));
        System.out.println();

        System.out.println("44 >>> 1:十进制：" + (44 >>> 1) + " 二进制：" + Integer.toBinaryString(44 >>> 1));
        System.out.println("44 >>> 2:十进制：" + (44 >>> 2) + " 二进制：" + Integer.toBinaryString(44 >>> 2));
        System.out.println("44 >>> 3:十进制：" + (44 >>> 3) + " 二进制：" + Integer.toBinaryString(44 >>> 3));
        System.out.println();
        System.out.println("-44 >>> 1:十进制：" + (-44 >>> 1) + " 二进制：0" + Integer.toBinaryString(-44 >>> 1));
        System.out.println("-44 >>> 2:十进制：" + (-44 >>> 2) + " 二进制：00" + Integer.toBinaryString(-44 >>> 2));
        System.out.println("-44 >>> 3:十进制：" + (-44 >>> 3) + " 二进制：000" + Integer.toBinaryString(-44 >>> 3));

    }

}
