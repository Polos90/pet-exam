package com.exam.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parametros de salida para GET /api/pet/{petId}
 * Campos: id, name, status
 */
public class PetResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    public PetResponse() {}

    public PetResponse(Long id, String name, String status) {
        this.id     = id;
        this.name   = name;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "PetResponse{id=" + id + ", name='" + name + "', status='" + status + "'}";
    }
}
