package com.exam.model.request;

import com.exam.constants.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Parametros de entrada para POST /api/pet
 */
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

    public PetRequest() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "PetRequest{id=" + id + ", name='" + name + "', status='" + status + "'}";
    }
}