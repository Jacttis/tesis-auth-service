package com.example.Auth.config.amq.client;

import com.example.Auth.document.Client;
import com.example.Auth.dto.ClientDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientMessage {

    private String messageId;
    private String message;
    private Date messageDate;
    private ClientDTO client;

}
