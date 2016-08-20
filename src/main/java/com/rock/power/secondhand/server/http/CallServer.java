package com.rock.power.secondhand.server.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by yanshi on 16-8-4.
 */
public enum CallServer {
    I;
    private CloseableHttpClient httpClient;

    CallServer() {
        httpClient = HttpClients.createDefault();

    }

    public CloseableHttpClient getHttpCliet() {
        return httpClient;
    }
}
