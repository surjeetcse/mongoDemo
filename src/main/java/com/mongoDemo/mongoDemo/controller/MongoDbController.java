package com.mongoDemo.mongoDemo.controller;

import com.mongoDemo.mongoDemo.service.MongoDbService;
import com.mongoDemo.mongoDemo.util.MongoUtil;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class MongoDbController {

    @RequestMapping("/run")
    @ResponseBody
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
    @Autowired
    MongoDbService mongoDbService;
    @PostMapping(value = "/mongoData")
    public boolean saveData(@RequestParam  String id, @RequestParam String name){
        //Save
       return  mongoDbService.saveData(id,name);
    }
    @PutMapping(value = "/mongoData")
    public boolean updateData(@RequestParam String newName,@RequestParam String oldName){
      return mongoDbService.updateData(newName,oldName);
    }
    @GetMapping(value = "/mongoData")
    public String fetchData(@RequestParam String id){
        //fetch
        return mongoDbService.fetchData(id);
    }
    @DeleteMapping(value = "/mongoData")
    public boolean deleteData(@RequestParam String id){
        //deleted
        return  mongoDbService.deleteData(id);
    }
}
