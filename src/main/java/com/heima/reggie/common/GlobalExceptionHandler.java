package com.heima.reggie.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.common
 * @Author: Little Brother
 * @CreateTime: 2023-03-05  17:42
 * @Version: 1.0
 * @Description: TODO
 */
@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")){

            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("添加失败");
    }


    @ExceptionHandler({CustomerException.class})
    public R<String> exceptionHandler(CustomerException ex){
        log.error(ex.getMessage());

        return R.error(ex.getMessage());
    }

}

