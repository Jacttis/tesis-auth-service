package com.example.Auth.dto;

import com.example.Auth.document.Client;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDTO {
    private String email;

    public static ClientDTO from(Client client) {
        return builder().email(client.getEmail()).build();
    }
}
