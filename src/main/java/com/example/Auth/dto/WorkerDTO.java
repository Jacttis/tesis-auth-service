package com.example.Auth.dto;

import com.example.Auth.document.Client;
import com.example.Auth.document.Worker;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class WorkerDTO {
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String name;
    private String phoneNumber;

    public static WorkerDTO from(Worker worker) {
        return builder().email(worker.getEmail()).address(worker.getAddress()).latitude(worker.getLatitude()).longitude(worker.getLongitude()).phoneNumber(worker.getPhoneNumber()).name(worker.getName()).build();
    }

}
