package com.heima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.Employee;
import com.heima.reggie.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.controller
 * @Author: Little Brother
 * @CreateTime: 2023-03-05  14:45
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@Slf4j
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired //或者可以使用 @Resource
    EmployeeService employeeService;


    /**
     *
     * @param employee
     * @param request
     * @return
     */
    @PostMapping("/login" )
    public R<Employee> Login(@RequestBody Employee employee , HttpServletRequest request){


        //获取用户的密码，并且通过md5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));



        //通过用户名查数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //判断emp是否存在，不存在返回登陆失败

        if (emp == null){
            return R.error("登陆失败，用户名不存在");
        }
        //判断密码是否正确
        if (!emp.getPassword().equals(password)){
            return R.error("登陆失败，密码错误");
        }
        //判断状态
        if (emp.getStatus() == 0){
            return R.error("登陆失败，状态不允许");
        }
        //登陆成功，将信存入session中
        request.getSession().setAttribute("employee", emp.getId());
        return R.success(emp);
    }



    @PostMapping("/logout")
    public R<String > logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }


    @PostMapping()
    public R<String> save(@RequestBody Employee employee,HttpServletRequest request){
        log.info("新增的员工信息是{}",employee);

        //设置初始密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());

        //获取当前用户
//        Long empId = (Long) request.getSession().getAttribute("employee");

//        employee.setCreateUser(empId);
//        employee.setUpdateUser(empId);

        employeeService.save(employee);
        return R.success("新增与共成功");
    }
    @GetMapping("/page")
    public R<Page>  page(int page, int pageSize, String name){
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造条件构造器
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件条件
        queryWrapper.like(!StringUtils.isEmpty(name), Employee::getName,name);
        //添加排序
        queryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询语句
        employeeService.page(pageInfo, queryWrapper);

        return R.success(pageInfo);
    }


    @PutMapping()
    public R<String> update(@RequestBody Employee employee,HttpServletRequest request){

//        Long empId =(Long) request.getSession().getAttribute("employee");
//
//        employee.setUpdateUser(empId);
//        employee.setUpdateTime(LocalDateTime.now());

        employeeService.updateById(employee);
        return R.success("禁用成功");
    }
    @GetMapping("/{id}")
    public R<Employee> getEmployeeById(@PathVariable("id") Long id){
        Employee emp = employeeService.getById(id);
        if (emp != null)
            return R.success(emp);
        return R.error("查找失败");
    }

}

