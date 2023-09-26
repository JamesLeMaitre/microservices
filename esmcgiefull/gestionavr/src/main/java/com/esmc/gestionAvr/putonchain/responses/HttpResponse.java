package com.esmc.gestionAvr.putonchain.responses;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class HttpResponse implements Serializable {
    private Boolean status;
    private String message;
}

