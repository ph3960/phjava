package com.faw.usertestall.service;

import com.faw.usertestall.domain.common.PageQuery;
import com.faw.usertestall.domain.common.PageResult;
import com.faw.usertestall.domain.dto.UserDTO;
import com.faw.usertestall.domain.dto.UserQueryDTO;

import java.util.List;


public interface UserService {
    int save(UserDTO userDTO);

    int update(Long id, UserDTO userDTO);

    int delete(Long id);

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO> pageQuery);
}
