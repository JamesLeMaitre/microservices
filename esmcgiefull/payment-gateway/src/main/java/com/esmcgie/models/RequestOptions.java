package com.esmcgie.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class RequestOptions implements Serializable {
    private String clientId;
    private String clientSecret;
}
