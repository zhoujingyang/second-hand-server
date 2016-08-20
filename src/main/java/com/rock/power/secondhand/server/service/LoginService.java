package com.rock.power.secondhand.server.service;


import com.rock.power.secondhand.server.http.CallServer;
import com.rock.power.secondhand.server.model.mysql.User;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by yanshi on 16-8-4.
 */
@Service
public class LoginService {
    private Logger logger = LoggerFactory.getLogger(LoginService.class);

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;
    @Value("${login.server.url}")
    private String loginUrl = "http://58.20.127.106/";

    public Optional<User> login(String userName, String password) {
        User user = new User();
        HttpPost httpPost = new HttpPost(loginUrl);
        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("DDDDD", userName));
        nvps.add(new BasicNameValuePair("upass", password));
        nvps.add(new BasicNameValuePair("R6", "1"));
        nvps.add(new BasicNameValuePair("0MKKey", "1"));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nvps));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try (CloseableHttpClient httpClient = CallServer.I.getHttpCliet()) {
            HttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                logger.info(EntityUtils.toString(httpResponse.getEntity(), Charset.forName("GB2312")));
                //  判断登录成功
                user.userName = userName;
//                sqlSessionTemplate.insert("mapper.Mapper.InsertUser", user);
            }
        } catch (IOException e) {
            logger.warn("call url {} is  unreachable error message {} ", loginUrl, e.getMessage());
            throw  new RuntimeException(e);
        }
        return Optional.ofNullable(user);

    }
}
