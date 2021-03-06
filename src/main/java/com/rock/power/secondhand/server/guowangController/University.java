package com.rock.power.secondhand.server.guowangController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by yanshi on 16/8/20.
 */


@RestController
@RequestMapping("/guowang/feedback")
@Api(value = "反馈信息接口", protocols = "JSON")
public class University {


    private Logger logger = LoggerFactory.getLogger(University.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "/getUniversityList", method = RequestMethod.GET)
    @ApiOperation(value = "获取大学列表", httpMethod = "GET")
    public List<String> getUniversityList(){
        return sqlSessionTemplate.selectList("guowang.mapper.GetUniversityList");
    }
}
