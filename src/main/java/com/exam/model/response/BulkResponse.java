package com.exam.model.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BulkResponse {

    private String transactionId;
    private int totalRequested;
    private int totalSuccess;
    private int totalFailed;
    private List<ResultadoResponse> results;
}
