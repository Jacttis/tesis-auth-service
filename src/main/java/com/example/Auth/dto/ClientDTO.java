package com.example.Auth.dto;

import com.example.Auth.document.Client;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Builder
@Data
public class ClientDTO {
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String picture;
    private String name;
    private String phoneNumber;
    private Date birthDate;

    public static ClientDTO from(Client client) {
        return builder().email(client.getEmail()).address(client.getAddress()).latitude(client.getLatitude()).longitude(client.getLongitude()).name(client.getName()).phoneNumber(client.getPhoneNumber()).birthDate(client.getBirthDate()).picture(client.getPicture()).build();
    }
}
