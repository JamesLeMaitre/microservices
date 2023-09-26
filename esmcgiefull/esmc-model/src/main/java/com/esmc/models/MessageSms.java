package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSms implements Serializable {
    private String SenderId;
    private String ApiKey;
    private String ClientId;
    private String Message;
    private String MobileNumbers;
}
