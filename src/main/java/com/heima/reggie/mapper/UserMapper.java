package com.heima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.reggie.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.mapper
 * @Author: Little Brother
 * @CreateTime: 2023-03-14  10:16
 * @Version: 1.0
 * @Description: TODO
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

