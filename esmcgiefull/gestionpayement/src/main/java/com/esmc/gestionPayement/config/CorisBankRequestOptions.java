package com.esmc.gestionPayement.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@NoArgsConstructor
@AllArgsConstructor
public class CorisBankRequestOptions {
    private String clientId;
    private String clientSecret;
}
