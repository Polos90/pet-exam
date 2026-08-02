package com.exam.controller;


import com.exam.constants.Constants;
import com.exam.model.request.PetRequest;
import com.exam.model.response.AddPetResponse;
import com.exam.model.response.PetResponse;
import com.exam.service.PetService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controlador REST — define los endpoints de la API
 * GET    /api/pet/{petId}  → obtiene pet por ID
 * POST   /api/pet           → agrega nuevo pet
 */
@RestController
@RequestMapping(Constants.API_PET_PATH)
public class PetController {

    private static final Logger log = LoggerFactory.getLogger(PetController.class);

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping("/{petId}")
    public ResponseEntity<PetResponse> getPet(@PathVariable Long petId) {
        log.info(Constants.LOG_CTRL_GET, petId);
        PetResponse response = petService.getPetById(petId);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<AddPetResponse> addPet(@RequestBody @Valid PetRequest request) {
        log.info(Constants.LOG_CTRL_POST, request);
        AddPetResponse response = petService.addPet(request);
        return ResponseEntity.ok(response);
    }
}