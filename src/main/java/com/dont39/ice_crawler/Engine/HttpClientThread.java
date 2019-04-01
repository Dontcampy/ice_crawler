package com.dont39.ice_crawler.Engine;

import com.dont39.ice_crawler.Common.HeaderConstants;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientThread implements Runnable{
    private String url;

    public HttpClientThread(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(20)
                .setConnectTimeout(20).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent:", HeaderConstants.USERAGENT);

        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
