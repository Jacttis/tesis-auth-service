package com.example.Auth.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private String profession;

}
