package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Setmeal;
import com.heima.reggie.domain.SetmealDish;
import com.heima.reggie.dto.SetmealDto;
import com.heima.reggie.mapper.SetmealMapper;
import com.heima.reggie.service.SetmealDishService;
import com.heima.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service.impl
 * @Author: Little Brother
 * @CreateTime: 2023-03-09  00:38
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class SetmealServiceImpl  extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    SetmealDishService setmealDishService;
    @Autowired
    SetmealService setmealService;

    @Override
    public R<String> saveWithDish(SetmealDto setmealDto) {

        this.save(setmealDto);
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            setmealDish.setSetmealId(setmealDto.getId());
        }
        setmealDishService.saveBatch(setmealDishes);

        return R.success("添加成功");

    }

    @Override
    @Transactional
    public R<String> removeWithids(List<Long> ids) {

        LambdaQueryWrapper<Setmeal> setmealQueryWrapper = new LambdaQueryWrapper<>();
        setmealQueryWrapper.in(Setmeal::getId,ids);
        setmealQueryWrapper.eq(Setmeal::getStatus ,1);
        int count = setmealService.count(setmealQueryWrapper);
        if (count > 0){
            throw new RuntimeException("套餐正在使用不可删除");
        }
        this.removeByIds(ids);

        //删除关系表中的数据
        LambdaQueryWrapper<SetmealDish> setmealDishQueryWrapper = new LambdaQueryWrapper<>();
        setmealDishQueryWrapper.in(SetmealDish::getSetmealId,ids);
        setmealDishService.removeById(setmealDishQueryWrapper);
        return R.success("删除套餐成功");

    }
}

