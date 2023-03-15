package com.heima.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @BelongsProject: reggie
 * @BelongsPackage: com.heima.reggie
 * @Author: Little Brother
 * @CreateTime: 2023-03-05  00:33
 * @Version: 1.0
 * @Description: TODO
 */
@ServletComponentScan
@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class ReggieApplication {
    public static void main(String[] args) {
            SpringApplication.run(ReggieApplication.class, args);
        }
}

