package com.example.Auth.repository;


import com.example.Auth.document.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerRepository extends MongoRepository<Worker, String> {
    Optional<Worker> findByEmail(String email);
    boolean existsByUsername(String username);
}

