package com.dont39.ice_crawler.Engine;

import com.dont39.ice_crawler.Common.HeaderConstants;
import com.dont39.ice_crawler.Dao.Mongo.Result;
import com.dont39.ice_crawler.Dao.Redis.Filter.Filter;
import com.dont39.ice_crawler.Dao.Redis.Filter.FilterFactory;
import com.dont39.ice_crawler.Dao.Redis.UrlQueue;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HttpClientThread implements Runnable{
    private String url;
    private Result mongoResult = new Result();
    private UrlQueue urlQueue = new UrlQueue();
    private Filter queueFilter = FilterFactory.getFactory().getFilter("DefaultFilter");

    public HttpClientThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent:", HeaderConstants.USERAGENT);

        try(CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String resp = EntityUtils.toString(response.getEntity(), "utf-8");
            mongoResult.insertMap(ContentFilter.filterContent(resp)); // 将数据写入mongo
            ContentFilter.filterUrl(resp).forEach(s -> {
                if (queueFilter.checkAdd(s)) {
                    urlQueue.push(s); // 将url写入redis
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
