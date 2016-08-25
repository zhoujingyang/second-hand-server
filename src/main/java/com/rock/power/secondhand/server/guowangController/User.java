package com.rock.power.secondhand.server.guowangController;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanshi on 16/8/20.
 */

@RestController
@RequestMapping("/guowang/user")
@Api(value = "用户接口", protocols = "JSON")
public class User {

    private Logger logger = LoggerFactory.getLogger(User.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "增加用户接口", httpMethod = "POST")
    public boolean addUser(@RequestParam String userName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeMillis = System.currentTimeMillis();
        Map userMap = new HashMap();
        userMap.put("userName", userName);
        userMap.put("createTime", dateFormat.format(currentTimeMillis));
        int resultStatus = sqlSessionTemplate.insert("guowang.mapper.InsertUser", userMap);
        if (resultStatus == 0) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "根据创建时间获取用户", httpMethod = "POST")
    public List<User> getUserList(@RequestParam String startTime, @RequestParam String endTime) {
        Map timeMap = new HashMap();
        timeMap.put("startId", startTime);
        timeMap.put("endId", endTime);
        return sqlSessionTemplate.selectList("guowang.mapper.GetUserList", timeMap);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有注册用户", httpMethod = "GET")
    public int getUsers() {
        return sqlSessionTemplate.selectOne("guowang.mapper.GetUsers");
    }

    @RequestMapping(value = "/getCurrentUsers", method = RequestMethod.GET)
    @ApiOperation(value = "取得当日注册用户数量", httpMethod = "GET")
    public int getCurrentUser() {
        Map<String, String> params = Maps.newHashMap();
        params.put("start", DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " 00:00:00");
        params.put("end", DateFormatUtils.format(new Date(), "yyyy-MM-dd") + " 23:59:59");
        return sqlSessionTemplate.selectOne("guowang.mapper.GetCurrentUsers", params);
    }


    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息", httpMethod = "POST")
    public com.rock.power.secondhand.server.model.mysql.User getUserInfo(@RequestParam String username) {
        Map timeMap = new HashMap();
        timeMap.put("username", username);
        return sqlSessionTemplate.selectOne("guowang.mapper.GetUserInfo",timeMap);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST")
    public com.rock.power.secondhand.server.model.mysql.User updateUser(@RequestParam String userName,
                           @RequestParam(required = false) String telphone,
                           @RequestParam(required = false) String nickName,
                           @RequestParam(required = false) String university) {
        Map timeMap = new HashMap();
        timeMap.put("userName", userName);
        timeMap.put("nickName", nickName);
        timeMap.put("telphone", telphone);
        timeMap.put("university", university);
        return sqlSessionTemplate.selectOne("guowang.mapper.updateUser",timeMap);
    }

}
