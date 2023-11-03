package com.example.coursespring;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepositoryTest extends MongoRepository<testMongo, String> {
}
