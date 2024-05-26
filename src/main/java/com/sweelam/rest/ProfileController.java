package com.sweelam.rest;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sweelam.service.ProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("api/profiles")
@CrossOrigin
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<Document>> getProfiles() {
        return ResponseEntity.ok(profileService.getAllProfiles());
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<?> getProfile(@PathVariable String profileId) {
        return ResponseEntity.ok(profileService.getById(profileId));
    }

    @PostMapping
    public ResponseEntity<?> addNewProfile(@RequestBody List<Map<String, Object>> entity) {
        profileService.save(entity);
        return ResponseEntity.ok("Done");
    }

}
