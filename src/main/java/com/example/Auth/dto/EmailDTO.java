package com.example.Auth.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {
    @NonNull
    private String email;
}
