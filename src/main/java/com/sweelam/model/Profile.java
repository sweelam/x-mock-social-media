package com.sweelam.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "profiles")
@NoArgsConstructor
@Getter @Setter
public class Profile {
    
    private String username;
    private List<Map<String, Object>> posts;
    
}
