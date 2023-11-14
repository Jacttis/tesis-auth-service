package com.example.Auth.dto;

import com.example.Auth.document.Client;
import com.mongodb.lang.Nullable;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Builder
@Data
public class ClientDTO {

    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String name;
    private String phoneNumber;
    private String picture;
    private Date birthDate;

    public static ClientDTO from(Client client) {
        return builder()
                .email(client.getEmail())
                .address(client.getAddress())
                .latitude(client.getLatitude())
                .longitude(client.getLongitude())
                .name(client.getName())
                .phoneNumber(client.getPhoneNumber())
                .birthDate(client.getBirthDate())
                .picture(client.getPicture())
                .build();
    }
}
