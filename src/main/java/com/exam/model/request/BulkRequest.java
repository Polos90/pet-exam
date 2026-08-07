package com.exam.model.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BulkRequest {
    private List<Long> petIds;
}