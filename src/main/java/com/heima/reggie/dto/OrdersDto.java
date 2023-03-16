package com.heima.reggie.dto;

import com.heima.reggie.domain.OrderDetail;
import com.heima.reggie.domain.Orders;
import lombok.Data;


import java.util.List;

/**
 * @Description
 * @Author rdm
 * @data 2022/5/30 - 22:19
 */
@Data
public class OrdersDto extends Orders {
    private List<OrderDetail> orderDetails;
}
