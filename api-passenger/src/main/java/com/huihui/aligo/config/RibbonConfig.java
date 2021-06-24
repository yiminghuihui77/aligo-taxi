package com.huihui.aligo.config;

import com.huihui.aligo.rule.GrayRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author minghui.y
 * @create 2021-06-24 8:22 下午
 **/
//@Configuration
public class RibbonConfig {

    @Bean
    public IRule ribbonRule() {
        return new GrayRule();
    }

}
