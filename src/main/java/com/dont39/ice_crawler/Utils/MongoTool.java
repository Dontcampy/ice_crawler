package com.dont39.ice_crawler.Utils;

import com.mongodb.*;

import java.util.*;

public class MongoTool {
    private static ResourceBundle bundle = ResourceBundle.getBundle("setting");
    private static String uri = bundle.getString("mongo_uri");
    private static String host = bundle.getString("mongo_host");
    private static Integer port = Integer.valueOf(bundle.getString("mongo_port"));
    private static String username = bundle.getString("mongo_username");
    private static String password = bundle.getString("mongo_pwd");
    private static String authDatabase = bundle.getString("mongo_authDatabase");
    private static Integer connectionsPerHost = Integer.valueOf(bundle.getString("mongo_connectionsPerHost"));
    private static Integer maxWaitTime = Integer.valueOf(bundle.getString("mongo_maxWaitTime"));

    private static ServerAddress serverAddress = new ServerAddress(host, port);
    private static MongoCredential credential =
            MongoCredential.createScramSha1Credential(username, authDatabase, password.toCharArray());
    private static MongoClientOptions mongoClientOptions =
            new MongoClientOptions.Builder().connectionsPerHost(connectionsPerHost).maxWaitTime(maxWaitTime).build();
//    private static List<ServerAddress> addrs = Collections.singletonList(serverAddress);
//    private static List<MongoCredential> credentials = Collections.singletonList(credential);
    private static MongoClient mongoClient = null;


    // 在类加载时初始化MongoClient
    static {
        initMongoClient();
    }

    private MongoTool() {}

    /**
     * 初始化MongoClient, 优先读取uri
     */
    private static void initMongoClient() {
        if (!uri.isEmpty()) {
            mongoClient = new MongoClient(new MongoClientURI(uri));
        } else {
            mongoClient = new MongoClient(serverAddress, credential, mongoClientOptions);
        }
    }

    public static MongoClient getMongoClient() {
        if (mongoClient == null) {
            initMongoClient();
        }
        return mongoClient;
    }

    public static void CloseClient() {
        if (mongoClient != null) {
            mongoClient.close();
            mongoClient = null;
        }
    }
}
