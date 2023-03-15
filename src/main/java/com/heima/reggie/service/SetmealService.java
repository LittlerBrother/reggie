package com.heima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Setmeal;
import com.heima.reggie.dto.SetmealDto;

import java.util.List;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service
 * @Author: Little Brother
 * @CreateTime: 2023-03-09  00:38
 * @Version: 1.0
 * @Description: TODO
 */
public interface SetmealService extends IService<Setmeal> {

    public R<String> saveWithDish(SetmealDto setmealDto);

    /**
     * 删除所选套餐
     * @param ids
     */
    R<String> removeWithids(List<Long> ids);


}
