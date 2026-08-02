package com.exam.service;

import com.exam.client.PetStoreClient;
import com.exam.constants.Constants;
import com.exam.model.request.PetRequest;
import com.exam.model.response.AddPetResponse;
import com.exam.model.response.PetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Capa Service — lógica de negocio
 * - Imprime en consola antes de regresar el response
 * - Genera transactionId con formato UUIDv4
 * - Genera dateCreated con la fecha actual del sistema
 */
@Service
public class PetService {

    private static final Logger log = LoggerFactory.getLogger(PetService.class);

    private final PetStoreClient petStoreClient;

    private final Map<Long, PetResponse> cache = new HashMap<>();

    public PetService(PetStoreClient petStoreClient) {
        this.petStoreClient = petStoreClient;
    }

    public PetResponse getPetById(Long petId) {
        log.info(Constants.LOG_SEPARATOR);
        log.info(Constants.LOG_SVC_SEARCH, petId);

        if (cache.containsKey(petId)) {
            log.info(Constants.LOG_SVC_CACHE_HIT, petId);
            PetResponse cached = cache.get(petId);
            log.info(Constants.LOG_SEPARATOR);
            return cached;
        }
        PetResponse pet = petStoreClient.getPetById(petId);

        cache.put(petId, pet);

        log.info(Constants.LOG_SVC_FOUND);
        log.info(Constants.LOG_SVC_FIELD_ID, pet.getId());
        log.info(Constants.LOG_SVC_FIELD_NOMBRE, pet.getName());
        log.info(Constants.LOG_SVC_FIELD_STATUS, pet.getStatus());
        log.info(Constants.LOG_SEPARATOR);

        return pet;
    }

    public AddPetResponse addPet(PetRequest request) {
        log.info(Constants.LOG_SEPARATOR);
        log.info(Constants.LOG_SVC_ADDING, request);

        Map externalResponse = petStoreClient.addPet(request);

        String transactionId = UUID.randomUUID().toString();

        String dateCreated = LocalDateTime.now().toString();

        AddPetResponse response = new AddPetResponse(
                transactionId,
                dateCreated,
                externalResponse.get(Constants.FIELD_STATUS),
                String.valueOf(externalResponse.get(Constants.FIELD_NAME))
        );

        PetResponse petCache = new PetResponse(request.getId(), request.getName(), request.getStatus());
        cache.put(request.getId(), petCache);

        log.info(Constants.LOG_SVC_ADDED);
        log.info(Constants.LOG_SVC_TRANSACTION, response.getTransactionId());
        log.info(Constants.LOG_SVC_DATE_CREATED, response.getDateCreated());
        log.info(Constants.LOG_SVC_FIELD_STATUS, response.getStatus());
        log.info(Constants.LOG_SVC_FIELD_NAME, response.getName());
        log.info(Constants.LOG_SEPARATOR);

        return response;
    }

}