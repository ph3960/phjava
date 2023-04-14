package com.faw.phusestarter;

import com.faw.ph.PhStarterApplication;
import com.faw.ph.service.EnableRegisterServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRegisterServer
public class PhUseStarterApplication {



    public static void main(String[] args) {
        SpringApplication.run(PhUseStarterApplication.class, args);
    }

}
