package com.esmc.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Settings {

    private Long id;

    private String code;

    private String label;

    private String value;

    private String type;

    private Date dateCreate;

    private Date dateUpdate;
}
