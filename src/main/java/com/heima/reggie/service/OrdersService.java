package com.heima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.reggie.domain.Orders;
import com.heima.reggie.dto.OrdersDto;
import org.springframework.web.bind.annotation.RequestBody;



public interface OrdersService extends IService<Orders> {
    /**
     * 用户下单
     * @param orders
     */
    public void submit(@RequestBody Orders orders);

    /**
     * 用户查看订单
     * @return
     */
    public Page<OrdersDto> userPage(int page , int pageSize);
}
