package com.heima.reggie.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.reggie.domain.Orders;
import com.heima.reggie.dto.OrdersDto;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * @Description
 * @Author rdm
 * @data 2022/5/30 - 20:17
 */
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
