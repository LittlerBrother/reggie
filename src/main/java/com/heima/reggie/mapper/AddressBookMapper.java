package com.heima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.reggie.domain.AddressBook;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
