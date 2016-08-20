package com.rock.power.secondhand.server.guowangController;

import com.google.common.collect.Maps;
import com.rock.power.secondhand.server.model.mysql.News;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by yanshi on 16/8/20.
 */
@RestController
@RequestMapping("/guowang/news")
@Api(value = "新闻接口", protocols = "JSON")
public class NewsController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping(value = "news", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "返回新闻列表")
    public List<News> list(@RequestParam Long start, @RequestParam Long end) {
        Map<String, Long> params = Maps.newHashMap();
        params.put("start", start);
        params.put("end", end);
        return sqlSessionTemplate.selectList("guowang.mapper.GetNewsList", params);
    }

    @RequestMapping(value = "news/{id}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "返回新闻详情")
    public News get(@PathVariable Integer id) {
        return sqlSessionTemplate.selectOne("guowang.mapper.GetNews", id);
    }

    @RequestMapping(value = "news/count", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "返回新闻总数")
    public int count() {
        return sqlSessionTemplate.selectOne("guowang.mapper.GetNewsCount");
    }
}
