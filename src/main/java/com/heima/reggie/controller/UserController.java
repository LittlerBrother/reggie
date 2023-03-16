package com.heima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.heima.reggie.common.R;
import com.heima.reggie.domain.User;
import com.heima.reggie.service.UserService;
import com.heima.reggie.utils.SMSUtils1;
import com.heima.reggie.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.controller
 * @Author: Little Brother
 * @CreateTime: 2023-03-14  10:19
 * @Version: 1.0
 * @Description: TODO
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session){
        log.info("user{}" ,user);

        if (!StringUtils.isEmpty(user)){
            //获取手机号
            String phone = user.getPhone();
            //生成验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();
            SMSUtils1.sendMessage("阿里云短信测试", "SMS_154950909", "13290733669", code);
            session.setAttribute(phone,code);
        }
        return R.success("获取短信成功");
    }

    @PostMapping("/login")
    public R<User> login(@RequestBody Map map,HttpSession session){
//        log.info("map{}",map.toString());

        String phone = (String) map.get("phone").toString();
        String code =(String)  map.get("code").toString();

        Object sessionCode = session.getAttribute(phone);
        if (sessionCode.equals(code) && sessionCode != null){
            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getPhone,phone);
            User user = userService.getOne(userQueryWrapper);
            if (user == null){
                user = new User();
                user.setPhone(phone);
                user.setStatus(1);
                userService.save(user);
            }
            session.setAttribute("user",user.getId());
            return R.success(user);
        }
        return R.error("登陆失败");
    }
}

