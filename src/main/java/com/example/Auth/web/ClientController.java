package com.example.Auth.web;

import com.example.Auth.document.Client;
import com.example.Auth.dto.ClientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import repository.ClientRepository;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/{email}")
    @PreAuthorize("#client.email == #email")
    public ResponseEntity client(@AuthenticationPrincipal Client client, @PathVariable String email) {
        return ResponseEntity.ok(ClientDTO.from(clientRepository.findByEmail(email).orElseThrow()));
    }
}
