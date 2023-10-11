package repository;


import com.example.Auth.document.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface WorkerRepository extends MongoRepository<Worker, String> {
    Optional<Worker> findByUsername(String username);
    boolean existsByUsername(String username);
}

