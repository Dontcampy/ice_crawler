package com.dont39.ice_crawler.Utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.util.*;

public class MongoTool {
    private static ResourceBundle bundle = ResourceBundle.getBundle("setting");
    private static String uri = bundle.getString("mongo_uri");
    private static String host = bundle.getString("mongo_host");
    private static Integer port = Integer.parseInt(bundle.getString("mongo_host"));
    private static String username = bundle.getString("mongo_username");
    private static String password = bundle.getString("mongo_password");
    private static String authDatabase = bundle.getString("mongo_authDatabase");

    private static ServerAddress serverAddress = new ServerAddress(host, port);
    private static MongoCredential credential =
            MongoCredential.createScramSha1Credential(username, authDatabase, password.toCharArray());

    private static List<ServerAddress> addrs = Collections.singletonList(serverAddress);
    private static List<MongoCredential> credentials = Collections.singletonList(credential);
    private static MongoClient mongoClient = null;

    // TODO: 优先读取mongodb uri
    static {
        if (!uri.isEmpty()) {
            mongoClient = new MongoClient(new MongoClientURI(uri));
        } else {

        }
    }
//
//    public static MongoClient getMongoClient() {
//        if ()
//    }

}
