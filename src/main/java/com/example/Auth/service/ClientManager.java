package com.example.Auth.service;

import com.example.Auth.document.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.text.MessageFormat;


@Service
public class ClientManager implements UserDetailsManager {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void createUser(UserDetails client) {
        ((Client) client).setPassword(passwordEncoder.encode(client.getPassword()));
        clientRepository.save((Client) client);
    }

    @Override
    public void updateUser(UserDetails user) {

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
        System.out.println(clientRepository.findByEmail(email).get().getEmail());

        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        MessageFormat.format("email {0} not found", email)
                ));
    }
}
