package com.dont39.ice_crawler.Engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ContentFilter {
    public static Map<String, Object> filterContent(String response) {
        HashMap<String, Object> content = new HashMap<>();
        Document doc = Jsoup.parse(response);
        content.put("title", doc.title());
        content.put("from", doc.select(".news_about p").get(0).text());
        content.put("time", doc.select(".news_about p").get(1).text());
        content.put("content", doc.select(".news_txt").text());
        content.put("author", doc.select(".news_infor_extra .infor_item").text());
        return content;
    }

    public static List<String> filterUrl(String response) {
        Document doc = Jsoup.parse(response);
        return doc.select(".list_hot a").eachAttr("href").stream()
                .filter(s -> Pattern.matches("newsDetail_forward_.\\d+", s))
                .collect(Collectors.toList());
    }
}
