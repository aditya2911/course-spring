package com.example.coursespring;


import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("testMongo")
@AllArgsConstructor
public class testMongo {

    @Id
    private String id;

    private String name;

}
