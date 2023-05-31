package com.ebanma.cloud.rpc.common.com.ebanma.cloud.rpc.api;

import com.ebanma.cloud.rpc.common.dto.UserDTO;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:02
 */

public interface UserService {

    UserDTO getById(int id);

}
