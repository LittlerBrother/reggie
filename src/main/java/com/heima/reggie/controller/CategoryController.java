package com.heima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Category;
import com.heima.reggie.domain.DishFlavor;
import com.heima.reggie.service.CategoryService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;


/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.controller
 * @Author: Little Brother
 * @CreateTime: 2023-03-08  23:50
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 菜品分类添加功能
     *
     * @param category
     * @return
     */
    @PostMapping()
    public R<String> save(@RequestBody Category category) {
        categoryService.save(category);
        return R.success("添加成功");
    }

    /**
     * 分页功能
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除菜品分类
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long id) {
        System.out.println("想要删除的菜品id是" + id);
        categoryService.deleteById(id);
        return R.success("删除成功");
    }

    /**
     * 修改菜品的分类
     *
     * @param category
     * @return
     */
    @PutMapping()
    public R<String> updateCategory(@RequestBody Category category) {
        categoryService.updateById(category);
        return R.success("更新成功");
    }

    @GetMapping("/list")
    public R<List<Category>> list(Category category) {
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        queryWrapper.orderByDesc(Category::getSort).orderByDesc(Category::getUpdateTime);
        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }

}

