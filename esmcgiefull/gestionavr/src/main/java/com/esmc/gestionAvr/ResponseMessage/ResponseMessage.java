package com.esmc.gestionAvr.ResponseMessage;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data @AllArgsConstructor
public class ResponseMessage {
    private boolean status;
    private List Data ;
}
