package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.common.CustomerException;
import com.heima.reggie.domain.Category;
import com.heima.reggie.domain.Dish;
import com.heima.reggie.domain.Setmeal;
import com.heima.reggie.mapper.CategoryMapper;
import com.heima.reggie.service.CategoryService;
import com.heima.reggie.service.DishService;
import com.heima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service.impl
 * @Author: Little Brother
 * @CreateTime: 2023-03-08  23:46
 * @Version: 1.0
 * @Description: TODO
 */
@Slf4j
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>implements CategoryService {

    @Autowired
    DishService dishService;

    @Autowired
    SetmealService setmealService;


    public void deleteById(Long id) {
        LambdaQueryWrapper<Dish> dishWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealWrapper = new LambdaQueryWrapper<>();
        dishWrapper.eq(Dish::getCategoryId, id);
        int dishCount = dishService.count(dishWrapper);
        log.info("dishCount:{}"+ dishCount);
        if (dishCount > 0){
            //抛出异常
            throw new CustomerException("删除的菜品已经关联套餐");
        }

        setmealWrapper.eq(Setmeal::getCategoryId,id);
        int count = setmealService.count(setmealWrapper);
        if (count>0){
            throw new CustomerException("删除的套餐已经关联菜品");
        }

        super.removeById(id);
    }

}

