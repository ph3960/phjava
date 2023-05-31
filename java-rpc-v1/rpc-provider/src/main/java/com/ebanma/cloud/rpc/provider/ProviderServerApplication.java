package com.ebanma.cloud.rpc.provider;

import com.ebanma.cloud.rpc.provider.server.RpcServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:22
 */
public class ProviderServerApplication implements CommandLineRunner {
    @Autowired
    private RpcServer rpcServer;

    public static void main(String[] args) {
        SpringApplication.run(ProviderServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        new Thread(new Runnable() {
            @Override
            public void run() {
                rpcServer.start("127.0.0.1", 8888);
            }
        }).start();
    }
}
