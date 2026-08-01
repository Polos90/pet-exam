package com.exam.model.request;

import com.exam.constants.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parametros de salida para POST /api/pet
 * Campos: transactionId (UUIDv4), dateCreated, status, name
 */

public class AddResponse {
    @JsonProperty(Constants.FIELD_TRANSACTION_ID)
    private String transactionId;

    @JsonProperty(Constants.FIELD_DATE_CREATED)
    private String dateCreated;

    @JsonProperty(Constants.FIELD_STATUS)
    private Object status;

    @JsonProperty(Constants.FIELD_NAME)
    private String name;

    public void AddPetResponse() {}

    public void AddPetResponse(String transactionId, String dateCreated, Object status, String name) {
        this.transactionId = transactionId;
        this.dateCreated   = dateCreated;
        this.status        = status;
        this.name          = name;
    }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getDateCreated() { return dateCreated; }
    public void setDateCreated(String dateCreated) { this.dateCreated = dateCreated; }

    public Object getStatus() { return status; }
    public void setStatus(Object status) { this.status = status; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    @Override
    public String toString() {
        return "AddPetResponse{transactionId='" + transactionId +
                "', dateCreated='" + dateCreated +
                "', status=" + status +
                ", name='" + name + "'}";
    }
}
