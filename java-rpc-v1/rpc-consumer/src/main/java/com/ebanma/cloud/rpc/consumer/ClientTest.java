package com.ebanma.cloud.rpc.consumer;

import com.ebanma.cloud.rpc.common.com.ebanma.cloud.rpc.api.UserService;
import com.ebanma.cloud.rpc.common.dto.UserDTO;
import com.ebanma.cloud.rpc.consumer.client.RpcClientProxy;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:34
 */
public class ClientTest {

    public static void main(String[] args) {
        UserService userService = (UserService) RpcClientProxy.create(UserService.class);
        UserDTO userDTO = userService.getById(1);
        System.out.println("测试RPC调用：" + userDTO);
    }
    
}
