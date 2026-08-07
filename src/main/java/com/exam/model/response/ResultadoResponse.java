package com.exam.model.response;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResultadoResponse {

    private Long petId;

    private boolean success;

    private String name;

    private String status;

    private String error;
}
