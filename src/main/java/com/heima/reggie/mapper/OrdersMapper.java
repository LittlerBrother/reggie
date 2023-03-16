package com.heima.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.heima.reggie.domain.Orders;
import org.apache.ibatis.annotations.Mapper;


/**
 * @Description
 * @Author rdm
 * @data 2022/5/30 - 20:16
 */
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {
}
