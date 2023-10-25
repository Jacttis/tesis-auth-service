package com.example.Auth.service;

import com.example.Auth.config.amq.client.ClientMessagePublisher;
import com.example.Auth.config.amq.worker.WorkerMessage;
import com.example.Auth.config.amq.worker.WorkerMessagePublisher;
import com.example.Auth.document.Worker;
import com.example.Auth.dto.WorkerDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import com.example.Auth.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@Slf4j
public class WorkerManager implements UserDetailsManager {

    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    WorkerMessagePublisher workerMessagePublisher;

    @Override
    public void createUser(UserDetails worker) {
        ((Worker) worker).setPassword(passwordEncoder.encode(worker.getPassword()));
        workerRepository.save((Worker) worker);

        WorkerMessage workerMessage = new WorkerMessage();
        workerMessage.setWorkerDTO(WorkerDTO.from((Worker) worker));

       // workerMessagePublisher.publishMessage(workerMessage);


    }

    @Override
    public void updateUser(UserDetails worker) {
        workerRepository.save((Worker) worker);

        WorkerMessage workerMessage = new WorkerMessage();
        workerMessage.setWorkerDTO(WorkerDTO.from((Worker) worker));

        workerMessagePublisher.publishMessage(workerMessage);

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String email) {
        return workerRepository.existsById(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return workerRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("email {0} not found", email)
                ));
    }
}
