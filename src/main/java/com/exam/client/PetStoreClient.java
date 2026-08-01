package com.exam.client;

import com.exam.constants.Constants;
import com.exam.model.request.PetRequest;
import com.exam.model.response.PetResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Cliente para consumir la API externa de PetStore:
 * https://petstore.swagger.io/v2
 *
 * Clases para conectarse a APIs de terceros
 */
@Component
public class PetStoreClient {

    private static final Logger log = LoggerFactory.getLogger(PetStoreClient.class);

    @Value("${petstore.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public PetStoreClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * GET /pet/{petId}
     * Obtiene un pet por ID desde PetStore externa
     */
    public PetResponse getPetById(Long petId) {
        String url = baseUrl + Constants.PET_ID_PATH + petId;
        log.info(Constants.LOG_CLIENT_GET, url);

        Map response = restTemplate.getForObject(url, Map.class);
        log.info(Constants.LOG_CLIENT_GET_RESP, response);

        PetResponse pet = new PetResponse();
        pet.setId(Long.valueOf(String.valueOf(response.get(Constants.FIELD_ID))));
        pet.setName(String.valueOf(response.get(Constants.FIELD_NAME)));
        pet.setStatus(String.valueOf(response.get(Constants.FIELD_STATUS)));

        return pet;
    }

    /**
     * POST /pet
     * Agrega un nuevo pet al store externo
     */
    public Map addPet(PetRequest request) {
        String url = baseUrl + Constants.PET_PATH;
        log.info(Constants.LOG_CLIENT_POST, url, request);

        Map response = restTemplate.postForObject(url, request, Map.class);
        log.info(Constants.LOG_CLIENT_POST_RESP, response);

        return response;
    }
}