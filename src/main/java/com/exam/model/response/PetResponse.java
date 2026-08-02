package com.exam.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Parametros de salida para GET /api/pet/{petId}
 * Campos: id, name, status
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PetResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;
}
