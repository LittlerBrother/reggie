package com.heima.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.reggie.domain.Employee;
import com.heima.reggie.mapper.EmployeeMapper;
import com.heima.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.service.impl
 * @Author: Little Brother
 * @CreateTime: 2023-03-05  14:42
 * @Version: 1.0
 * @Description: TODO
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

