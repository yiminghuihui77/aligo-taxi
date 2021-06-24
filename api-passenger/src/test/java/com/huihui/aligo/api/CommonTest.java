package com.huihui.aligo.api;

import java.math.BigDecimal;

/**
 * @author minghui.y
 * @create 2021-06-05 10:50 上午
 **/
public class CommonTest {

    public static void main( String[] args ) {

        //二进制表示小数：通过二分法无限接近？
        //二进制转十进制  ok
        //十进制转二进制：整数转二进制，和小数转二进制
        BigDecimal a = new BigDecimal( 2.1 );
        BigDecimal b = new BigDecimal( 2.5 );

        //2.100000000000000088817841970012523233890533447265625
        System.out.println(a);
        //2.5
        System.out.println(b);

    }

}
