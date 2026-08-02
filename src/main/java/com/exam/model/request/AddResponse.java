package com.exam.model.request;

import com.exam.constants.Constants;
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
public class AddResponse {
    @JsonProperty(Constants.FIELD_TRANSACTION_ID)
    private String transactionId;

    @JsonProperty(Constants.FIELD_DATE_CREATED)
    private String dateCreated;

    @JsonProperty(Constants.FIELD_STATUS)
    private Object status;

    @JsonProperty(Constants.FIELD_NAME)
    private String name;
}
