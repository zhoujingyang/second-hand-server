package com.rock.power.secondhand.server.controller;

import com.rock.power.secondhand.server.model.ResponseEntity;
import com.rock.power.secondhand.server.model.mysql.Goods;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by jingyang on 16-8-1.
 */
@RestController
@RequestMapping("/secondhand/goods")
@Api(value = "商品接口", protocols = "JSON")
public class GoodsController {

    private Logger logger = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @Value("${image.path}")
    private String imagePath;


    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "获取商品详情", httpMethod = "GET")
    public Goods getGoodsList(@PathVariable Integer id) {
        return sqlSessionTemplate.selectOne("mapper.Mapper.GetGoodsList", id);
    }


    @RequestMapping(value = "/list/user", method = RequestMethod.GET)
    @ApiOperation(value = "获取商品列表接口", httpMethod = "GET")
    public List<Goods> getGoodsListForUser(@RequestParam Integer id) {
        Map params = new HashMap();
        params.put("id", id);
        List<Goods> goodsList = sqlSessionTemplate.selectList("mapper.Mapper.GetGoodsListForUser", params);
        return goodsList;
    }

    @RequestMapping(value = "/list/order", method = RequestMethod.GET)
    @ApiOperation(value = "获取商品列表接口,order=0 正序，order=1倒序 ", httpMethod = "GET")
    public List<Goods> getGoodsList(@RequestParam Integer order, @RequestParam String orderByString, @RequestParam int limitCount,
                                    @RequestParam(required = false) Integer goodsStatus, @RequestParam(required = false) Integer userId,
                                    @RequestParam(required = false) Integer goodsCategoryId) {

        Map params = new HashMap();
        params.put("order", order == 0 ? "desc" : "asc");
        params.put("orderByString", orderByString);
        params.put("limitCount", limitCount);
        params.put("goodsStatus", goodsStatus);
        params.put("userId", userId);
        params.put("goodsCategoryId", goodsCategoryId);
        List<Goods> goodsList = sqlSessionTemplate.selectList("mapper.Mapper.GetGoodsOrderBy", params);
        return goodsList;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json",produces = "application/json")
    @ApiOperation(value = "增加商品接口", httpMethod = "POST")
    public boolean addGoods(@RequestParam String telphone, @RequestParam Double money, @RequestParam String tradeAddress,
                            @RequestParam String goodsDescribe, @RequestParam String title,
                            @RequestParam Integer userId,
                            @RequestParam String goodsPictureAddress,
                            @RequestParam Integer goodsCategoryId) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeMillis = System.currentTimeMillis();
        int resultStatus = 0;
        Goods goods = new Goods();
        goods.telphone = telphone;
        goods.money = money;
        goods.tradeAddress = tradeAddress;
        goods.goodsDescribe = goodsDescribe;
        goods.title = title;
        goods.goodsStatus = 0;
        goods.userId = userId;
        goods.goodsPictureAddress = goodsPictureAddress;
        goods.goodsCategoryId = goodsCategoryId;
        goods.createTime = dateFormat.format(currentTimeMillis);
        goods.updateTime = dateFormat.format(currentTimeMillis);

        resultStatus = sqlSessionTemplate.insert("mapper.Mapper.addGoods", goods);
        if (resultStatus == 0) {
            return false;
        } else {
            return true;
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改商品接口", httpMethod = "POST")
    public boolean updateGoods(@RequestParam Integer id,
                               @RequestParam(required = false) String telphone, @RequestParam(required = false) Double money, @RequestParam(required = false) String tradeAddress,
                               @RequestParam(required = false) String goodsDescribe, @RequestParam(required = false) String title,
                               @RequestParam(required = false) Integer userId,
                               @RequestParam(required = false) String goodsPictureAddress,
                               @RequestParam(required = false) Integer goodsCategoryId,
                               @RequestParam(required = false) Integer goodsStatus) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long currentTimeMillis = System.currentTimeMillis();
        int resultStatus;
        Goods goods = new Goods();
        goods.id = id;
        goods.telphone = telphone;
        goods.money = money;
        goods.tradeAddress = tradeAddress;
        goods.goodsDescribe = goodsDescribe;
        goods.title = title;
        goods.goodsStatus = 0;
        goods.userId = userId;
        goods.goodsPictureAddress = goodsPictureAddress;
        goods.goodsCategoryId = goodsCategoryId;
        goods.updateTime = dateFormat.format(currentTimeMillis);
        goods.goodsStatus = goodsStatus;

        resultStatus = sqlSessionTemplate.update("mapper.Mapper.updateGoods", goods);
        if (resultStatus == 0) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ApiOperation(value = "逻辑删除商品接口", httpMethod = "POST")
    public boolean deleteGoods(@RequestParam Integer goodsId) {
        int resultStatus = 0;
        resultStatus = sqlSessionTemplate.update("mapper.Mapper.deleteGoods", goodsId);
        if (resultStatus == 0) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam MultipartFile file) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        ResponseEntity<String> responseEntity = new ResponseEntity<>();
        try (FileOutputStream outputStream = new FileOutputStream(new File(imagePath, fileName))) {
            IOUtils.write(file.getBytes(), outputStream);
            responseEntity.setSuccess(true);
            responseEntity.setValue(fileName);
        } catch (IOException e) {
            logger.error("upload image error {}/{}", imagePath, fileName);
            throw new RuntimeException(e);
        }
        return responseEntity;
    }
}
