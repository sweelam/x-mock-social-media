package com.sweelam.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoCursor;
import com.sweelam.database.DatabaseDao;

@Service
public class ProfileService {
    private static final String PROFILE_COLLECTION = "profiles";

    private final DatabaseDao databaseDao;

    public ProfileService(DatabaseDao databaseDao) {
        this.databaseDao = databaseDao;
    }

    public List<Document> getAllProfiles() {
        MongoCursor<Document> mongoCursor = databaseDao.find(PROFILE_COLLECTION);
        List<Document> results = new ArrayList<Document>();
        while (mongoCursor.hasNext())
            results.add(mongoCursor.next());
        return results;
    }

    public Document getById(String profileId) {
        return databaseDao.findOneById("profiles", profileId);
    }

    public void save(List<Map<String, Object>> entity) {
        List<Document> docs = entity.stream()
                .map(t -> new Document(t))
                .toList();

        databaseDao.insertMany(PROFILE_COLLECTION, docs);
    }
}
