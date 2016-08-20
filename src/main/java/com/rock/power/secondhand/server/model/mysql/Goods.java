package com.rock.power.secondhand.server.model.mysql;

/**
 * Created by jingyang on 16-8-4.
 */
public class Goods {
    public Integer id;
    public Integer userId;
    public Integer goodsCategoryId;
    public String telphone;
    public Double money;
    public String tradeAddress;
    public String goodsDescribe;
    public String goodsPictureAddress;
    public String createTime;
    public String updateTime;
    public String title;
    public Integer goodsStatus;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsCategoryId=" + goodsCategoryId +
                ", telphone=" + telphone +
                ", money=" + money +
                ", title='" + title + '\'' +
                ", tradeAddress='" + tradeAddress + '\'' +
                ", goodsDescribe='" + goodsDescribe + '\'' +
                ", goodsPictureAddress='" + goodsPictureAddress + '\'' +
                ", createTime='" + createTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", goodsStatus=" + goodsStatus +
                '}';
    }
}
