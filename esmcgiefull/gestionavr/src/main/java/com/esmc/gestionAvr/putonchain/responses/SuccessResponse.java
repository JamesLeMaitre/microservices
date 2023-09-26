package com.esmc.gestionAvr.putonchain.responses;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse extends HttpResponse implements Serializable {
    private Object data;
}
