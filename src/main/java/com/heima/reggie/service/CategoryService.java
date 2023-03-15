package com.heima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.reggie.domain.Category;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service
 * @Author: Little Brother
 * @CreateTime: 2023-03-08  23:45
 * @Version: 1.0
 * @Description: TODO
 */
public interface CategoryService extends IService<Category> {
    public void deleteById(Long id);
}
