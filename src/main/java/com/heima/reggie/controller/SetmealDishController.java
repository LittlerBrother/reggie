package com.heima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Category;
import com.heima.reggie.domain.Setmeal;
import com.heima.reggie.dto.SetmealDto;
import com.heima.reggie.service.CategoryService;
import com.heima.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.controller
 * @Author: Little Brother
 * @CreateTime: 2023-03-13  21:55
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealDishController {
    @Autowired
    SetmealService setmealService;
    @Autowired
    CategoryService categoryService;

    /**
     * 添加套餐
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){

        return setmealService.saveWithDish(setmealDto);
    }

    /**
     * 套餐分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){

        Page<Setmeal> pageInfo = new Page<>(page,pageSize);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.like(name != null,Setmeal::getName, name);
        setmealLambdaQueryWrapper.orderByAsc(Setmeal::getCreateTime);

        setmealService.page(pageInfo,setmealLambdaQueryWrapper);


        //对象拷贝
        BeanUtils.copyProperties(pageInfo, setmealDtoPage,"records");
        List<Setmeal> records = pageInfo.getRecords();
        List<SetmealDto> list = records.stream().map(item -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item, setmealDto);

            //获取分类的id，通过id查询套餐的名称
            Long categoryId = item.getCategoryId();

            Category category = categoryService.getById(categoryId);

            if (category != null) {
                String categoryName = category.getName();

                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        setmealDtoPage.setRecords(list);
        return R.success(setmealDtoPage);
    }

    @DeleteMapping()
    public R<String> delete(@RequestParam("ids") List<Long> ids){
        return setmealService.removeWithids(ids);
    }

}

