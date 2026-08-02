package com.exam.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Parametros de salida para POST /api/pet
 * Campos: transactionId (UUIDv4), dateCreated, status, name
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddPetResponse {

    @JsonProperty("transactionId")
    private String transactionId;

    @JsonProperty("dateCreated")
    private String dateCreated;

    @JsonProperty("status")
    private Object status;

    @JsonProperty("name")
    private String name;
}
