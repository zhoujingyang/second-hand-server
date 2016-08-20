package com.rock.power.secondhand.server.guowangController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yanshi on 16/8/20.
 */

@RestController
@RequestMapping("/user")
@Api(value = "用户接口", protocols = "JSON")
public class User {

    private Logger logger = LoggerFactory.getLogger(User.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;



    @RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ApiOperation(value = "增加用户接口", httpMethod = "POST")
    public boolean addUser(@RequestParam String userName){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeMillis = System.currentTimeMillis();
        Map userMap = new HashMap();
        userMap.put("userName",userName);
        userMap.put("createTime",dateFormat.format(currentTimeMillis));
        int resultStatus = sqlSessionTemplate.insert("guowang.mapper.InsertUser",userMap);
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
        timeMap.put("startId",startTime);
        timeMap.put("endId",endTime);
        return sqlSessionTemplate.selectList("guowang.mapper.GetUserList",timeMap);
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    @ApiOperation(value = "获取所有注册用户", httpMethod = "GET")
    public int getUsers() {
        return sqlSessionTemplate.selectOne("guowang.mapper.GetUsers");
    }



    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "获取用户信息", httpMethod = "POST")
    public User getUserInfo(@RequestParam String telphone) {
        Map timeMap = new HashMap();
        timeMap.put("telphone",telphone);
        return sqlSessionTemplate.selectOne("guowang.mapper.GetUserInfo", timeMap);
    }


    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户", httpMethod = "POST")
    public User updateUser(@RequestParam String userName,
                           @RequestParam(required =  false) String telphone,
                           @RequestParam(required = false) String nickName,
                           @RequestParam( required =  false ) String university) {
        Map timeMap = new HashMap();
        timeMap.put("telphone",telphone);
        return sqlSessionTemplate.selectOne("guowang.mapper.updateUser", timeMap);
    }

}
