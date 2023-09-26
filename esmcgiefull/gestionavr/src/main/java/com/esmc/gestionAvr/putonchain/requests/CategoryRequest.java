package com.esmc.gestionAvr.putonchain.requests;

import com.esmc.gestionAvr.putonchain.entities.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest implements Serializable {
    @NotNull
    @NotBlank
    private String wording;
    @NotNull
    @NotBlank
    private UnitOfMeasure unitOfMeasure;

    @NotNull
    @NotBlank
    private String code;
}
