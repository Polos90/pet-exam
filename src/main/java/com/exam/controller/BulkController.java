package com.exam.controller;

import com.exam.model.request.BulkRequest;
import com.exam.model.response.BulkResponse;
import com.exam.service.BulkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pets")
public class BulkController {

    private final BulkService service;

    public BulkController(BulkService service){
        this.service = service;
    }

    @PostMapping("/bulk")
    public BulkResponse bulk(@RequestBody BulkRequest request){
        return service.findPets(request.getPetIds());
    }
}