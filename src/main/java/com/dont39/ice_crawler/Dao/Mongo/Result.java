package com.dont39.ice_crawler.Dao.Mongo;

import com.dont39.ice_crawler.Utils.MongoTool;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class Result {
    private MongoDatabase db = MongoTool.getMongoClient().getDatabase("crawler");
    private MongoCollection<Document> coll = db.getCollection("reesult");

    public void insert(Document document) {
        coll.insertOne(document);
    }
}
