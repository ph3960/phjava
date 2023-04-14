package com.faw.ph.config;

import com.faw.ph.starter.StarterBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author : PeiHang
 * @create 2023/4/13 19:26
 */
@Configuration
@ConditionalOnBean(ConfigMarker.class)
public class MyConfig {

    static {
        System.out.println("MyAutoConfiguration init....");
    }

    @Bean
    public StarterBean starterBean(){
        return new StarterBean();
    }

}
