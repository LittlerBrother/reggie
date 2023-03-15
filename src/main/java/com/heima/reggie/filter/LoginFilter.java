package com.heima.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.heima.reggie.common.BaseContext;
import com.heima.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie.filter
 * @Author: Little Brother
 * @CreateTime: 2023-03-05  16:01
 * @Version: 1.0
 * @Description: TODO
 */
@Slf4j
//过滤器组件
@WebFilter(filterName = "LoginCheckOut" ,
        //配置拦截请求路径
        urlPatterns = {"/*"})
@Component
public class LoginFilter implements Filter {


    //路径匹配
    public static final AntPathMatcher PATH_MATCH = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        //向上转型
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("成功拦截请求{}" , request.getRequestURI());

        //定义不需要处理的路径
        String[] urls = {"/employee/login",
                        "/employee/logout",
                        "/backend/**",
                        "/front/**",
                        "/common/**",
                        "/user/login",
                "/user/sendMsg"
        };

        boolean check = checkPath(urls, request.getRequestURI());

        if (check){
            //放行
            filterChain.doFilter(request, response);
            return ;
        }

        //判断是否登陆session是否有登陆信息
        if(request.getSession().getAttribute("employee") != null){
            //设置单前线程用户的id
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request, response);
            return ;
        }


        //检查客户端用户是否登陆
        if(request.getSession().getAttribute("user") != null){
            //设置单前线程用户的id
            Long userId = (Long) request.getSession().getAttribute("user");
            BaseContext.setCurrentId(userId);
            filterChain.doFilter(request, response);
            return ;
        }


        //返回前端json,让前端进行重定向
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    private boolean checkPath(String[] urls ,String uri){

        for (String url:urls) {
            boolean match = PATH_MATCH.match(url, uri);
            if (match){
                return true;
            }
        }
        return false;
    }
}

