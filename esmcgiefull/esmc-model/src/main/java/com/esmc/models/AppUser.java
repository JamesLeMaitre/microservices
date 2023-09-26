package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements Serializable {

    private Long id;

    private String username;

    private String password;

    private Collection<AppRole> roles = new ArrayList<>();


    private Boolean actif = false;
}
