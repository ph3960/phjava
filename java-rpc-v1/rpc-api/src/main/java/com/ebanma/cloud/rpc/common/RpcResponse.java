package com.ebanma.cloud.rpc.common;

import lombok.Data;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:00
 */
@Data
public class RpcResponse {
    /**
     * 响应ID
     */
    private String requestId;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 返回的结果
     */
    private Object result;
}
