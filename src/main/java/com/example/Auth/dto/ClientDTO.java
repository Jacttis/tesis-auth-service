package com.example.Auth.dto;

import com.example.Auth.document.Client;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
public class ClientDTO {
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String name;

    public static ClientDTO from(Client client) {
        return builder().email(client.getEmail()).build();
    }
}
