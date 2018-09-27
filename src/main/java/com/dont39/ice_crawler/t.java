package com.dont39.ice_crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class t {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.ctolib.com/topics-80581.html");
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(20)
                .setConnectTimeout(20).build();
        httpGet.setConfig(requestConfig);
        httpGet.setHeader("User-Agent:", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");

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
