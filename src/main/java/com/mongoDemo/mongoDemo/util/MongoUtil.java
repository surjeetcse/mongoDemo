package com.mongoDemo.mongoDemo.util;

import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MongoUtil {
    private static MongoClient MCLIENT;
    private static MongoUtil m_instance;


    public MongoUtil() {
        System.out.println("mongoUtil");
        if (!"".equals(MongoDatabaseManager.getPmsUsername()) && !"".equals(MongoDatabaseManager.getPmsPassword())) {
            MongoCredential credential = MongoCredential.createCredential(MongoDatabaseManager.getPmsUsername(),
                    MongoDatabaseManager.getPmsDatabase(), MongoDatabaseManager.getPmsPassword().toCharArray());
            ServerAddress serverAddress = new ServerAddress(MongoDatabaseManager.getPmsHost(), MongoDatabaseManager.getPmsPort());
            MCLIENT = new MongoClient(serverAddress, Arrays.asList(credential), getMongoOptions());
            System.out.println("inside if :"+MCLIENT);
        } else {
            MCLIENT = new MongoClient(MongoDatabaseManager.getHost(), MongoDatabaseManager.getPort());
            System.out.println("inside else :"+MCLIENT);
        }
    }
    public boolean save(Document doc, String collectionName) {
        MongoCollection<Document> collection = null;
        try {
            collection = getDB().getCollection(collectionName);
            collection.insertOne(doc);
            return true;
        } catch (Exception e) {
        } finally {
            collection = null;
        }
        return false;
    }

    public boolean update(String newName,String oldName, String collectionName){
        MongoCollection<Document> collection = null;
        try {
            collection = getDB().getCollection(collectionName);

            BasicDBObject query = new BasicDBObject();
            query.put("name", newName);

            BasicDBObject newDocument = new BasicDBObject();
            newDocument.put("name", oldName);

            BasicDBObject updateObj = new BasicDBObject();
            updateObj.put("$set", newDocument);

            collection.updateOne(query, updateObj);
            return true;
        } catch (Exception e) {
        } finally {
            collection = null;
        }
        return false;
    }

    public Map<String, String> fetch(String refID, String collectionName){
        Document searchQuery = null;
        Document result = null;
        FindIterable<Document> cursor = null;
        MongoCursor<Document> iterator = null;
        Map<String, String> requiredData = new HashMap();
        MongoCollection<Document> collection = null;
        try {
            collection = getDB().getCollection(collectionName);
            searchQuery = new Document();
            searchQuery.put("_id", refID);
            cursor = collection.find(searchQuery);
            iterator = cursor.iterator();
            while (iterator.hasNext()) {
                result = iterator.next();
                requiredData.put("name", result.get("name").toString());
            }
        } catch (Exception e) {
        } finally {
            collection = null;
        }
        return requiredData;
    }

    public boolean delete(String name, String collectionName){
        MongoCollection<Document> collection = null;
        try {
            collection = getDB().getCollection(collectionName);
            BasicDBObject query = new BasicDBObject();
            query.put("_id", name);
            collection.deleteOne(query);
            return true;
        } catch (Exception e) {
        } finally {
            collection = null;
        }
        return false;
    }
    private MongoDatabase getDB() {
        return MCLIENT.getDatabase("exampleDB");
    }
    public static MongoUtil getInstance() {
        if (m_instance == null) {
            m_instance = new MongoUtil();
        }
        return m_instance;
    }
    public MongoClientOptions getMongoOptions() {
        MongoClientOptions options = MongoClientOptions.builder()
                .connectionsPerHost(200)
                .connectTimeout(40 * 1000)
                .maxConnectionLifeTime(2 * 60 * 1000)
                .socketTimeout(2 * 60 * 1000)
                .threadsAllowedToBlockForConnectionMultiplier(15)
                .maxConnectionIdleTime(60 * 1000)
                .build();
        return options;
    }
}
