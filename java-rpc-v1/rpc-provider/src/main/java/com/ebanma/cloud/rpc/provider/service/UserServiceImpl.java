package com.ebanma.cloud.rpc.provider.service;


import com.ebanma.cloud.rpc.common.com.ebanma.cloud.rpc.api.UserService;
import com.ebanma.cloud.rpc.common.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author : PeiHang
 * @create 2023/5/31 17:05
 */
@Service
public class UserServiceImpl implements UserService {

    Map<Object, UserDTO> userMap = new HashMap();
    @Override
    public UserDTO getById(int id) {
        if (userMap.size() == 0) {
        UserDTO user1 = new UserDTO();
        user1.setId(1);
        user1.setName("张三");
        UserDTO user2 = new UserDTO();
        user2.setId(2);
        user2.setName("李四");
        userMap.put(user1.getId(), user1);
        userMap.put(user2.getId(), user2);
    }
        return userMap.get(id);
    }
}
