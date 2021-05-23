package com.huihui.aligo;

import com.huihui.aligo.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author minghui.y
 * @create 2021-05-23 8:44 下午
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServiceVerificationCodeApplication.class})
public class RedisTest {

    @Autowired
    private RedisUtil redisUtil;


    @Test
    public void testSetKey() {
        redisUtil.set( "verify-code", "298872" );
        String value = (String) redisUtil.get( "verify-code" );

        System.out.println(value);
    }

}
