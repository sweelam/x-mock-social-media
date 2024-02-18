package com.sweelam.database;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoDbClient {
    private static MongoClient mongoClient = null;
    
    @Value("${mongodb.url}")
    private String DB_URL;
    
    @Bean
    public MongoClient mongozClient() {
        if (mongoClient == null)
            mongoClient = MongoClients.create(DB_URL);

        return mongoClient;
    }
}
