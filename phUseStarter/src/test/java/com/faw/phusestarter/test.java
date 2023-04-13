package com.faw.phusestarter;

import com.faw.ph.starter.StarterBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author : PeiHang
 * @create 2023/4/13 20:42
 */
@SpringBootTest
public class test {


    @Autowired
    private StarterBean starterBean;

    @Test
    void contextLoads() {

        System.out.println(starterBean);

    }
}
