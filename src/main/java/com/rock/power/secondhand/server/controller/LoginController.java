package com.rock.power.secondhand.server.controller;

import com.rock.power.secondhand.server.model.mysql.User;
import com.rock.power.secondhand.server.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by yanshi on 16/7/31.
 */
@RestController
@Api(value = "登录接口")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginService.class);
    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/secondhand/hello")
    @ApiOperation(value = "测试接口", httpMethod = "GET")
    public String hello(String word) {
        return word;
    }


    @RequestMapping(value = "/secondhand/login", consumes = {"text/plain", "application/*"}, produces = {"text/plain", "application/*"})
    @ResponseBody
    @ApiOperation(value = "登录接口", httpMethod = "POST",consumes = "text/plain", produces = "text/plain")
    public boolean login(String userName, String passwd) {
        Optional<User> userOptional = loginService.login(userName, passwd);
        boolean isLoginSuccess = userOptional.isPresent();
        if (isLoginSuccess) {
            logger.info("username {} login success ", userName);
        }
        return isLoginSuccess;
    }
}
