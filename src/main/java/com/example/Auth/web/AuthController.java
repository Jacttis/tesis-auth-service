package com.example.Auth.web;

import com.example.Auth.document.Client;
import com.example.Auth.dto.*;
import com.example.Auth.security.TokenGenerator;
import com.example.Auth.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    ClientManager clientManager;
    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    @Qualifier("daoAuthenticationProviderClient")
    DaoAuthenticationProvider daoAuthenticationProviderClient;

    @PostMapping("/client/register")
    public ResponseEntity clientRegister(@RequestBody SignupDTO signupDTO) {
        Client client = new Client(signupDTO.getEmail(), signupDTO.getPassword());
        clientManager.createUser(client);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(client, signupDTO.getPassword(), Collections.emptyList());

        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));

    }

    @PostMapping("/client/login")
    public ResponseEntity clientLogin(@RequestBody LoginDTO loginDTO) {

        Authentication authentication = daoAuthenticationProviderClient.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getEmail(),loginDTO.getPassword()));

        System.out.println("EEEEEEEEEEEE");
        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));
    }
}
