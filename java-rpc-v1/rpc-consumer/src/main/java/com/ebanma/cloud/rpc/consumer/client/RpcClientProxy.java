package com.ebanma.cloud.rpc.consumer.client;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

import com.alibaba.fastjson.JSON;

import com.ebanma.cloud.rpc.common.RpcRequest;
import com.ebanma.cloud.rpc.common.RpcResponse;
/**
 * @Author : PeiHang
 * @create 2023/5/31 17:33
 */

public class RpcClientProxy {

    public static Object create(Class rpcService) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{rpcService},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RpcRequest rpcRequest = new RpcRequest();
                        rpcRequest.setRequestId(UUID.randomUUID().toString());
                        rpcRequest.setClassName(method.getDeclaringClass().getName());
                        rpcRequest.setMethodName(method.getName());
                        rpcRequest.setParameterTypes(method.getParameterTypes());
                        rpcRequest.setParameters(args);

                        RpcClient rpcClient = new RpcClient("127.0.0.1", 8888);

                        try {
                            Object response = rpcClient.send(JSON.toJSONString(rpcRequest));
                            RpcResponse rpcResponse = JSON.parseObject(response.toString(), RpcResponse.class);
                            if (rpcResponse.getError() != null) {
                                throw new RuntimeException(rpcResponse.getError());
                            }

                            Object result = rpcResponse.getResult();
                            return JSON.parseObject(result.toString(), method.getReturnType());
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw e;
                        } finally {
                            rpcClient.close();
                        }
                    }
                });
    }
}