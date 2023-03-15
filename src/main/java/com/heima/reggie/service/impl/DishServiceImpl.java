package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Dish;
import com.heima.reggie.dto.DishDto;
import com.heima.reggie.domain.DishFlavor;
import com.heima.reggie.mapper.DishMapper;
import com.heima.reggie.service.DishFlavorService;
import com.heima.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service.impl
 * @Author: Little Brother
 * @CreateTime: 2023-03-09  00:36
 * @Version: 1.0
 * @Description: TODO
 */
@Service

public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>implements DishService {
    @Autowired
    DishFlavorService dishFlavorService;

    @Autowired
    DishService dishService;
    @Override
    @Transactional
    public void saveDish(DishDto dishDto) {
        this.save(dishDto);
        Long dishId = dishDto.getId();//获取菜品id

        List<DishFlavor> flavors = dishDto.getFlavors();

        for (DishFlavor dishFlavor: flavors) {
            dishFlavor.setDishId(dishId);
        }
        dishFlavorService.saveBatch(flavors);

    }

    @Override
    public DishDto getFlavorsById(Long id) {
        Dish dish = this.getById(id);

        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        //查询当前菜品对应的口味信息
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        List<DishFlavor> flavors = dishFlavorService.list(dishFlavorLambdaQueryWrapper);

        dishDto.setFlavors(flavors);
        return dishDto;
    }

    @Override
    @Transactional
    public void updateWithFlavors(DishDto dishDto) {
        //更新dish表的基本信息
        this.updateById(dishDto);

        //删除dish表中的对应的口味数据
        LambdaQueryWrapper<DishFlavor> dishDtoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishDtoLambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(dishDtoLambdaQueryWrapper);


        //添加当前提交过来的数据
        List<DishFlavor> dishFlavors = dishDto.getFlavors();
        for (DishFlavor dishFlavor : dishFlavors) {
            dishFlavor.setDishId(dishDto.getId());

        }

        dishFlavorService.saveBatch(dishFlavors);

    }

    /**
     * 添加套餐时查看菜品，不查看已经下架的菜品
     * @param dish
     */
    @Override
    public R<List<Dish>> listDish(Dish dish) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, dish.getCategoryId());
        dishLambdaQueryWrapper.eq(Dish::getStatus, 1);

        dishLambdaQueryWrapper.orderByDesc(Dish::getSort).orderByAsc(Dish::getUpdateTime);

        List<Dish> list = dishService.list(dishLambdaQueryWrapper);
        return R.success(list);
    }
}

