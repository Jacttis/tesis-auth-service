package com.example.Auth.web;

import com.example.Auth.document.Client;
import com.example.Auth.dto.*;
import com.example.Auth.security.TokenGenerator;
import com.example.Auth.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
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

    @Autowired
    @Qualifier("jwtRefreshTokenAuthProviderClient")
    JwtAuthenticationProvider jwtAuthenticationProviderClient;

    @PostMapping("/client/register")
    public ResponseEntity clientRegister(@RequestBody SignupDTO signupDTO) {
        Client client = new Client(signupDTO.getEmail(), signupDTO.getPassword());
        clientManager.createUser(client);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(client, signupDTO.getPassword(), Collections.emptyList());

        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));

    }

    @PostMapping("/client/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity clientLogin(@RequestBody LoginDTO loginDTO) {

        Authentication authentication;
        try{
            authentication = daoAuthenticationProviderClient.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getEmail(),loginDTO.getPassword()));
        }
        catch (Exception e){
            e.printStackTrace();
            return null ;
        }
        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));
    }

    @PostMapping("/client/token")
    public ResponseEntity token( @RequestBody TokenDTO tokenDTO) {
        Authentication authentication = jwtAuthenticationProviderClient.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));

        return ResponseEntity.ok(tokenGenerator.createTokenClient(authentication));
    }
}
