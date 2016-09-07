package com.rock.power.secondhand.server.guowangController;

import com.google.common.collect.Maps;
import com.rock.power.secondhand.server.model.mysql.Version;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yanshi on 16/9/6.
 */
@RestController
@RequestMapping("/guowang/version")
@Api(value = "版本接口", protocols = "JSON")
public class VersionController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "version/{type}", method = RequestMethod.PUT)
    @ApiOperation(httpMethod = "PUT", value = "更新版本号 type 1 安卓 2 IOS")
    public void update(@PathVariable("type") Integer type, @RequestParam Integer version) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("type", type);
        params.put("version", version);
        sqlSessionTemplate.update("guowang.mapper.UpVersion", params);
    }

    @RequestMapping(value = "version/{type}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "获取版本号 参数 type 1 安卓 2 IOSß")
    public Version getVersion(@PathVariable("type") Integer type) {
        return sqlSessionTemplate.selectOne("guowang.mapper.GetVersion", type);
    }

    @RequestMapping(value = "version/all", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "获取全部版本信息")
    public List<Version> getAll() {
        return sqlSessionTemplate.selectList("guowang.mapper.GetAllVersion");
    }
}
