package com.mongoDemo.mongoDemo.service;

import com.mongoDemo.mongoDemo.util.MongoUtil;
import org.springframework.stereotype.Service;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import java.util.Map;

@Service
public class MongoDbService {

    public static final String EXAMPLE_COLLECTION = "exampleCollection";

    public boolean saveData(String id, String name){
        //Save
        Document document=new Document();
        document.put("_id",id);
        document.put("name",name);
        boolean saveDb= MongoUtil.getInstance().save(document, EXAMPLE_COLLECTION);
        return  saveDb;
    }

    public boolean updateData(String newName,String oldName){
        //update
        boolean updateDb= MongoUtil.getInstance().update(newName,oldName, EXAMPLE_COLLECTION);
        return  updateDb;
    }

    public String fetchData(String id){
        //fetch
        Map<String ,String> fetchDb= MongoUtil.getInstance().fetch(id, EXAMPLE_COLLECTION);
        MongoCursor<Document> iterator = null;
        System.out.println();
        return fetchDb.get("name");
    }

    public boolean deleteData(String id){
        //delete
        boolean deleteDb= MongoUtil.getInstance().delete(id, EXAMPLE_COLLECTION);
        return  deleteDb;
    }
}
