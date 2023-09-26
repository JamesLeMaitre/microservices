package com.esmc.gestionAvr.putonchain.requests;

import com.esmc.gestionAvr.putonchain.entities.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest implements Serializable {
    @NotNull
    @NotBlank
    private String wording;
    @NotNull
    @NotBlank
    private int quantity;
    @NotNull
    @NotBlank
    private double unitPrice;
    @NotNull
    @NotBlank
    private String description;
    @NotNull
    @NotBlank
    private Long idDetailAgr;
    @NotNull
    @NotBlank
    private double unitTotal;
    @NotNull
    @NotBlank
    private Category category;

    private Long ksu;

}
