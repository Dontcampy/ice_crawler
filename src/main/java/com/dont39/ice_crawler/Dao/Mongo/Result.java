package com.dont39.ice_crawler.Dao.Mongo;

import com.dont39.ice_crawler.Utils.MongoTool;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Map;

public class Result {
    private MongoDatabase db = MongoTool.getMongoClient().getDatabase("crawler");
    private MongoCollection<Document> coll = db.getCollection("result");

    public void insertMap(Map<String, Object> map) {
        Document doc = new Document(map);
        coll.insertOne(doc);
    }
}
