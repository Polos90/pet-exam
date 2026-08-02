package com.exam.model.request;

import com.exam.constants.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Parametros de entrada para POST /api/pet
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PetRequest {

    @NotNull(message = Constants.MSG_ID_REQUIRED)
    @JsonProperty(Constants.FIELD_ID)
    private Long id;

    @NotBlank(message = Constants.MSG_NAME_BLANK)
    @JsonProperty(Constants.FIELD_NAME)
    private String name;

    @NotBlank(message = Constants.MSG_STATUS_BLANK)
    @JsonProperty(Constants.FIELD_STATUS)
    private String status;
}