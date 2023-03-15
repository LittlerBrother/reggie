package com.heima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Dish;
import com.heima.reggie.dto.DishDto;

import java.util.List;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service
 * @Author: Little Brother
 * @CreateTime: 2023-03-09  00:35
 * @Version: 1.0
 * @Description: TODO
 */
public interface DishService extends IService<Dish> {

    /**
     * 新增菜品
     * @param dishDto
     */
    public void saveDish(DishDto dishDto);


    /**
     * 查询菜品的详细信息
     * @param id
     * @return
     */
    public DishDto getFlavorsById(Long id);

    public void updateWithFlavors(DishDto dishDto);


    /**
     * 添加套餐时查看菜品，不查看已经下架的菜品
     * @param dish
     */
    public R<List<Dish>> listDish(Dish dish);
}
