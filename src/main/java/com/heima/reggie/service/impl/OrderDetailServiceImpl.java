package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.domain.OrderDetail;
import com.heima.reggie.mapper.OrderDetailMapper;
import com.heima.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;


/**
 * @Description
 * @Author rdm
 * @data 2022/5/30 - 20:19
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
