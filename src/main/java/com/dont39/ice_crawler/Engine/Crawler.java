package com.dont39.ice_crawler.Engine;

import com.dont39.ice_crawler.Dao.Redis.UrlQueue;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


import java.io.IOException;

public class Crawler {
    public static void main(String[] args) {
        UrlQueue queue = new UrlQueue();
        while (true) {
            String url = queue.pop();
            if (url != null) {
                Thread t = new Thread(new HttpClientThread(url));
                t.start();
            }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
