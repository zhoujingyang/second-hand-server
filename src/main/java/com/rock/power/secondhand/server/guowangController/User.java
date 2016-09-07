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

//, consumes = "application/json", produces = "application/json"
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "增加用户接口", httpMethod = "POST")
    public boolean addUser(@RequestParam String userName) {
        Map userMap = new HashMap();
        userMap.put("userName", userName);
        int resultStatus;

        try {
            resultStatus = sqlSessionTemplate.insert("guowang.mapper.InsertUser", userMap);
            return true;
        }catch (Exception e){
            return false;
        }

    }


    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户是否存在接口", httpMethod = "POST")
    public boolean getUser(@RequestParam String userName) {
        Map userMap = new HashMap();
        userMap.put("username", userName);
        int resultStatus;
        try {
            resultStatus = sqlSessionTemplate.selectOne("guowang.mapper.GetUser", userMap);
        }catch (Exception e){
            return false;
        }
            return true;

    }


    @RequestMapping(value = "/getUserOnlineTime", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户上网时长", httpMethod = "POST")
    public Integer getUserOnlineTime(@RequestParam String userName) {
        Map userMap = new HashMap();
        userMap.put("username", userName);
        int result = sqlSessionTemplate.selectOne("guowang.mapper.GetUserOnlineTime", userMap);
        return result;
    }

    @RequestMapping(value = "/updateUserOnlineTime", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户上网时长", httpMethod = "POST")
    public boolean updateUserOnlineTime(@RequestParam String userName,@RequestParam Integer addOnlineTime) {
        Map userMap = new HashMap();
        userMap.put("username", userName);
        int oldOnlineTime = sqlSessionTemplate.selectOne("guowang.mapper.GetUserOnlineTime", userMap);
        int onlineTime = oldOnlineTime + addOnlineTime;
        userMap.put("onlineTime",onlineTime);
        int result = sqlSessionTemplate.update("guowang.mapper.UpdateUserOnlineTime", userMap);
        if(result == 1){
            return  true;
        }else {
            return  false;
        }

    }




    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ApiOperation(value = "根据创建时间获取用户", httpMethod = "POST")
    public List<User> getUserList(@RequestParam String startTime, @RequestParam String endTime) {
        Map timeMap = new HashMap();
        timeMap.put("startTime", startTime);
        timeMap.put("endTime", endTime);
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
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String address,
                           @RequestParam(required = false) String university) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeMillis = System.currentTimeMillis();

        Map timeMap = new HashMap();
        timeMap.put("userName", userName);
        timeMap.put("name", name);
        timeMap.put("address",address);
        timeMap.put("telphone", telphone);
        timeMap.put("university", university);
        timeMap.put("updateTime", dateFormat.format(currentTimeMillis));

        return sqlSessionTemplate.selectOne("guowang.mapper.updateUser",timeMap);
    }

}
