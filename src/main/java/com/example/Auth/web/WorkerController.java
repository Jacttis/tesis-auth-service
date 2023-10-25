package com.example.Auth.web;

import com.example.Auth.document.Worker;
import com.example.Auth.dto.WorkerDTO;
import com.example.Auth.repository.WorkerRepository;
import com.example.Auth.service.WorkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    WorkerManager workerManager;

    @GetMapping("/{email}")
    @PreAuthorize("#worker.email == #email")
    public ResponseEntity client(@AuthenticationPrincipal Worker worker, @PathVariable String email) {
        return ResponseEntity.ok(WorkerDTO.from(workerRepository.findByEmail(email).orElseThrow()));
    }

    @PatchMapping("updateWorker/{email}")
    @PreAuthorize("#worker.email == #email")
    public ResponseEntity<?> updateWorker(@AuthenticationPrincipal Worker worker,
                                          @PathVariable String email,
                                          @RequestBody WorkerDTO workerDTO) {

        Worker existingWorker = workerRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Worker not found"));

        try {
            for (Field field : WorkerDTO.class.getDeclaredFields()) {
                if(field.getName().equals("email")){
                    continue;
                }
                field.setAccessible(true);
                Object value = null;

                value = field.get(workerDTO);

                if (value != null) {
                    Field targetField = Worker.class.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(existingWorker, value);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad body passed");
        }


        workerManager.updateUser(existingWorker);

        return ResponseEntity.ok().build();
    }
}
