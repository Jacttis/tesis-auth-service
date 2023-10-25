package com.example.Auth.service;

import com.example.Auth.config.amq.client.ClientMessage;
import com.example.Auth.config.amq.client.ClientMessagePublisher;
import com.example.Auth.document.Client;
import com.example.Auth.dto.ClientDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import com.example.Auth.repository.ClientRepository;

import java.text.MessageFormat;


@Service
@Slf4j
public class ClientManager implements UserDetailsManager {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ClientMessagePublisher clientMessagePublisher;


    @Override
    public void createUser(UserDetails client) {
        ((Client) client).setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save((Client) client);

        ClientMessage clientMessage = new ClientMessage();
        clientMessage.setClient(ClientDTO.from((Client) client));

        clientMessagePublisher.publishMessage(clientMessage);

    }

    @Override
    public void updateUser(UserDetails client) {
        clientRepository.save((Client) client);
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String email) {
        return clientRepository.existsById(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("email {0} not found", email)
                ));
    }
}
