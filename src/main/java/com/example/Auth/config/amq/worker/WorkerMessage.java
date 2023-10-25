package com.example.Auth.config.amq.worker;

import com.example.Auth.dto.ClientDTO;
import com.example.Auth.dto.WorkerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkerMessage {

    private String messageId;
    private String message;
    private Date messageDate;
    private WorkerDTO workerDTO;

}
