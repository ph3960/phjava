package com.faw.usertestall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.faw.usertestall.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
