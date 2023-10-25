package com.example.Auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDTO {
    private String email;
    private String password;
    private Double latitude;
    private Double longitude;
    private String name;
    private String address;
    private String phoneNumber;

}
