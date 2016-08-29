package com.rock.power.secondhand.server.guowangController;

import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanshi on 16/8/20.
 */


@RestController
@RequestMapping("/guowang/feedback")
@Api(value = "反馈信息接口", protocols = "JSON")
public class Feedback {

    private Logger logger = LoggerFactory.getLogger(Feedback.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ApiOperation(value = "增加反馈信息接口", httpMethod = "POST")
    public boolean addUser(@RequestParam String telphone, @RequestParam String content) {
        Map userMap = new HashMap();
        userMap.put("userName", telphone);
        userMap.put("content", content);
        try {
            int resultStatus = sqlSessionTemplate.insert("guowang.mapper.InsertFeedback", userMap);
            if (resultStatus == 1) {
                return true;
            }
        } catch (Exception e) {
                return false;
        }
        return false;
    }



    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "反馈信息列表", httpMethod = "GET")
    public Map<String, Object> list(@RequestParam Integer start, @RequestParam Integer end) {
        Map<String, Integer> params = Maps.newHashMap();
        params.put("start", start);
        params.put("end", end);
        Map<String, Object> values = Maps.newHashMap();
        values.put("data", sqlSessionTemplate.selectList("guowang.mapper.GetFeedbackList", params));
        values.put("count", sqlSessionTemplate.selectOne("guowang.mapper.GetFeedbackCount"));
        return values;
    }

}

