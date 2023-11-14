package com.example.Auth.dto;

import com.example.Auth.document.Worker;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class WorkerDTO {
    private String email;
    private String address;
    private Double latitude;
    private Double longitude;
    private String name;
    private String phoneNumber;
    private String picture;
    private String description;
    private Date birthDate;
    private String certificate;
    private ProfessionDTO profession;

    public static WorkerDTO from(Worker worker) {
        return builder()
                .email(worker.getEmail())
                .address(worker.getAddress())
                .latitude(worker.getLatitude())
                .longitude(worker.getLongitude())
                .phoneNumber(worker.getPhoneNumber())
                .name(worker.getName())
                .profession(new ProfessionDTO(worker.getProfession()))
                .certificate(worker.getCertificate())
                .birthDate(worker.getBirthDate())
                .picture(worker.getPicture())
                .description(worker.getDescription())
                .build();
    }

}
