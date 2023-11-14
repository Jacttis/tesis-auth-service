package com.example.Auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

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
    private Date date;
    private String profession;

}
