package com.sweelam.service.impl;

import com.mongodb.client.MongoCursor;
import com.sweelam.database.DatabaseDao;
import com.sweelam.model.Profile;
import com.sweelam.repository.ProfileRepository;
import com.sweelam.service.ProfileService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {
    private static final String PROFILE_COLLECTION = "profiles";
    private final DatabaseDao databaseDao;
    private final ProfileRepository profileRepository;


    public List<Document> getAllProfiles() {
        // Manual way , NOT RECOMMENDED!!!
        MongoCursor<Document> mongoCursor = databaseDao.find(PROFILE_COLLECTION);
        List<Document> results = new ArrayList<Document>();
        while (mongoCursor.hasNext())
            results.add(mongoCursor.next());
        return results;
    }

    public Flux<Profile> getAllProfilesReactively() {
        log.info("Service thread {}", Thread.currentThread().getId());
        return profileRepository.findAll();
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
