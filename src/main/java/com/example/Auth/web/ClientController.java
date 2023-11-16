package com.example.Auth.web;

import com.example.Auth.document.Client;
import com.example.Auth.document.Worker;
import com.example.Auth.dto.ClientDTO;
import com.example.Auth.dto.WorkerDTO;
import com.example.Auth.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.example.Auth.repository.ClientRepository;

import java.lang.reflect.Field;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientManager clientManager;

    @GetMapping("/getClient")
    public ResponseEntity<?> client(@AuthenticationPrincipal Client client, @RequestParam String email) {
        return ResponseEntity.ok(ClientDTO.from(clientRepository.findByEmail(email).orElseThrow()));
    }

    @PatchMapping("/updateClient")
    public ResponseEntity<?> updateClient(@AuthenticationPrincipal Client client,
                                          @RequestParam String email,
                                          @RequestBody ClientDTO clientDTO) {

        Client existingClient = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found"));

        try {
            for (Field field : ClientDTO.class.getDeclaredFields()) {
                if(field.getName().equals("email")){
                    continue;
                }
                field.setAccessible(true);
                Object value = null;

                value = field.get(clientDTO);

                if (value != null) {
                    Field targetField = Client.class.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(existingClient, value);
                }
            }
        } catch (IllegalAccessException | NoSuchFieldException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad body passed");
        }


        clientManager.updateUser(existingClient);

        return ResponseEntity.ok().build();
    }
}
