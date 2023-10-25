package com.example.Auth.web;

import com.example.Auth.document.Client;
import com.example.Auth.document.Worker;
import com.example.Auth.dto.*;
import com.example.Auth.security.TokenGenerator;
import com.example.Auth.service.ClientManager;
import com.example.Auth.service.WorkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    ClientManager clientManager;

    @Autowired
    WorkerManager workerManager;

    @Autowired
    TokenGenerator tokenGenerator;

    @Autowired
    @Qualifier("daoAuthenticationProviderClient")
    DaoAuthenticationProvider daoAuthenticationProviderClient;

    @Autowired
    @Qualifier("daoAuthenticationProviderWorker")
    DaoAuthenticationProvider daoAuthenticationProviderWorker;

    @Autowired
    @Qualifier("jwtRefreshTokenAuthProviderClient")
    JwtAuthenticationProvider jwtAuthenticationProviderClient;

    @Autowired
    @Qualifier("jwtRefreshTokenAuthProviderWorker")
    JwtAuthenticationProvider jwtAuthenticationProviderWorker;

    @PostMapping("/client/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity clientRegister(@RequestBody SignupDTO signupDTO) {
        Client client = new Client( signupDTO.getEmail(), signupDTO.getPassword(), signupDTO.getAddress(), signupDTO.getLatitude(), signupDTO.getLongitude(),signupDTO.getName());
        clientManager.createUser(client);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(client, signupDTO.getPassword(), Collections.emptyList());

        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));

    }

    @PostMapping("/client/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity clientLogin(@RequestBody LoginDTO loginDTO) {

        Authentication authentication;
        try {
            authentication = daoAuthenticationProviderClient.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al autenticar: " + e.getMessage());
        }
        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));
    }

    @PostMapping("/client/token")
    public ResponseEntity tokenClient(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = jwtAuthenticationProviderClient.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));

        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));
    }

    @PostMapping("/worker/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity workerRegister(@RequestBody SignupDTO signupDTO) {
        Worker worker = new Worker( signupDTO.getEmail(), signupDTO.getPassword(),signupDTO.getName(),signupDTO.getLatitude(),signupDTO.getLongitude());
        workerManager.createUser(worker);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(worker, signupDTO.getPassword(), Collections.emptyList());

        return ResponseEntity.ok(tokenGenerator.createTokenWorker(authentication));

    }

    @PostMapping("/client/authenticateUser")
    public ResponseEntity getAuthenticateClient(@AuthenticationPrincipal Client client) {
        return ResponseEntity.ok(new EmailDTO(client.getEmail()));
    }

    @PostMapping("/worker/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity workerLogin(@RequestBody LoginDTO loginDTO) {

        Authentication authentication;
        try {
            authentication = daoAuthenticationProviderWorker.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getEmail(), loginDTO.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error al autenticar: " + e.getMessage());
        }
        return ResponseEntity.ok(tokenGenerator.createTokenWorker(authentication));
    }

    @PostMapping("/worker/token")
    public ResponseEntity tokenWorker(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = jwtAuthenticationProviderWorker.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));

        return ResponseEntity.ok(tokenGenerator.createTokenWorker(authentication));
    }


    @PostMapping("/worker/authenticateUser")
    public ResponseEntity getAuthenticateWorker(@AuthenticationPrincipal Worker worker) {
        return ResponseEntity.ok(new EmailDTO(worker.getEmail()));
    }
}
