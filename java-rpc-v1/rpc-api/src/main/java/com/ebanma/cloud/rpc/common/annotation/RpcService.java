package com.ebanma.cloud.rpc.common.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @Author : PeiHang
 * @create 2023/5/31 17:00
 */

@Target(ElementType.TYPE)// 用于接口和类上
@Retention(RetentionPolicy.RUNTIME)// 在运行时可以获取到
public @interface RpcService {
}
