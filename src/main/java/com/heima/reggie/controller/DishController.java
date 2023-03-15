package com.heima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Category;
import com.heima.reggie.domain.Dish;
import com.heima.reggie.dto.DishDto;
import com.heima.reggie.service.CategoryService;
import com.heima.reggie.service.DishFlavorService;
import com.heima.reggie.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.controller
 * @Author: Little Brother
 * @CreateTime: 2023-03-09  17:58
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@RequestMapping("/dish")
public class DishController {
    @Autowired
    DishFlavorService dishFlavorService;
    @Autowired
    DishService dishService;
    @Autowired
    CategoryService categoryService;

    @PostMapping()
    public R<String> save(@RequestBody DishDto dishDto) {

        dishService.saveDish(dishDto);
        return R.success("新增菜品成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();
        LambdaQueryWrapper<Dish> dishQueryWrapper = new LambdaQueryWrapper<>();
        dishQueryWrapper.like(name != null, Dish::getName, name);
        dishQueryWrapper.orderByDesc(Dish::getUpdateTime);
        dishService.page(pageInfo, dishQueryWrapper);
        //拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();
        //给每一个菜品的分类名称赋值
        List<DishDto> list = records.stream().map(item -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();
            //获取分类的对象
            Category category = categoryService.getById(categoryId);
            //设置分类名
            if (category != null) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            //拷贝
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);
        return R.success(dishDtoPage);
    }



    /**
     * 根据id查询菜品信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDto> getById(@PathVariable("id") Long id){
        DishDto flavors = dishService.getFlavorsById(id);
        return R.success(flavors);
    }

    @PutMapping()
    public R<String> update(@RequestBody DishDto dishDto) {

        dishService.updateWithFlavors(dishDto);
        return R.success("新增菜品成功");
    }


    @GetMapping("/list")
    public R<List<Dish>> list(Dish dish){
        return dishService.listDish(dish);
    }



}

