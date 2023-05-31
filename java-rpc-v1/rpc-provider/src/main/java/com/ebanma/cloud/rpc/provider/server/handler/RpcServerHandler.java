package com.ebanma.cloud.rpc.provider.server.handler;

import com.alibaba.fastjson.JSON;
import com.ebanma.cloud.rpc.common.RpcRequest;
import com.ebanma.cloud.rpc.common.RpcResponse;
import com.ebanma.cloud.rpc.common.annotation.RpcService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.BeansException;
import org.springframework.cglib.reflect.FastClass;
import org.springframework.cglib.reflect.FastMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:16
 */

@Component
@ChannelHandler.Sharable
public class RpcServerHandler extends SimpleChannelInboundHandler<String> implements ApplicationContextAware {

    private static final Map SERVICE_INSTANCE = new ConcurrentHashMap();

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        // 接收客户端远程调用请求
        RpcRequest rpcRequest = JSON.parseObject(s, RpcRequest.class);
        RpcResponse rpcResponse = new RpcResponse();

        rpcResponse.setRequestId(rpcRequest.getRequestId());
        try {
            rpcResponse.setResult(handler(rpcRequest));
        } catch (Exception e) {
            e.printStackTrace();
            rpcResponse.setError(e.getMessage());
        }
        // 返回客户端远程调用响应
        channelHandlerContext.writeAndFlush(JSON.toJSONString(rpcResponse));

    }

    public Object handler(RpcRequest request) throws InvocationTargetException {
        // 根据远程调用传递过来的类名从缓存中找到对应的Bean
        Object serviceBean = SERVICE_INSTANCE.get(request.getClassName());
        if (serviceBean == null) {
            throw new RuntimeException("根据beanName找不到服务，beanName:" + request.getClassName());
        }

        Class<?> aClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        // 利用CGLIB库进行反射调用
        FastClass fastClass = FastClass.create(aClass);
        FastMethod method = fastClass.getMethod(methodName, parameterTypes);
        return method.invoke(serviceBean, parameters);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        // 将有RpcService注解的bean缓存起来
        Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
        if (beansWithAnnotation != null && beansWithAnnotation.size() >0 ) {
            for (Map.Entry<String, Object> entry : beansWithAnnotation.entrySet()) {
                Object serviceBean = entry.getValue();
                if (serviceBean.getClass().getInterfaces().length == 0) {
                    throw new RuntimeException("服务必须实现接口");
                }

                String name = serviceBean.getClass().getInterfaces()[0].getName();
                SERVICE_INSTANCE.put(name, serviceBean);
            }
        }
    }
}
