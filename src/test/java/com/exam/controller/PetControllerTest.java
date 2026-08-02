package com.exam.controller;

import com.exam.constants.TestConstants;
import com.exam.model.request.PetRequest;
import com.exam.model.response.AddPetResponse;
import com.exam.model.response.PetResponse;
import com.exam.service.PetService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * addFilters = false evita que ApiKeyFilter (@Component Filter) bloquee
 * los requests con 401 durante los tests del controlador.
 */
@WebMvcTest(PetController.class)
@AutoConfigureMockMvc(addFilters = false)
@DisplayName(TestConstants.DN_CTRL_CLASS)
class PetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PetService petService;

    @Autowired
    private ObjectMapper objectMapper;

    private PetRequest     petRequest;
    private PetResponse    petResponse;
    private AddPetResponse addPetResponse;

    @BeforeEach
    void setUp() {
        petRequest = new PetRequest();
        petRequest.setId(TestConstants.PET_ID);
        petRequest.setName(TestConstants.PET_NAME);
        petRequest.setStatus(TestConstants.PET_STATUS);

        petResponse = new PetResponse();
        petResponse.setId(TestConstants.PET_ID);
        petResponse.setName(TestConstants.PET_NAME);
        petResponse.setStatus(TestConstants.PET_STATUS);

        addPetResponse = new AddPetResponse(
                TestConstants.TRANSACTION_ID,
                TestConstants.DATE_CREATED,
                TestConstants.PET_STATUS,
                TestConstants.PET_NAME
        );
    }

    // ══════════════════════════════════════════════════════════
    // GET /api/pet/{petId}
    // ══════════════════════════════════════════════════════════

    @Test
    @DisplayName(TestConstants.DN_CTRL_GET_200)
    void getPet_debeRetornar200() throws Exception {
        when(petService.getPetById(TestConstants.PET_ID)).thenReturn(petResponse);

        mockMvc.perform(get(TestConstants.URL_PET_BY_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(TestConstants.JSON_ID).value(TestConstants.PET_ID))
                .andExpect(jsonPath(TestConstants.JSON_NAME).value(TestConstants.PET_NAME))
                .andExpect(jsonPath(TestConstants.JSON_STATUS).value(TestConstants.PET_STATUS));
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_GET_FIELDS)
    void getPet_debeTenerCamposRequeridos() throws Exception {
        when(petService.getPetById(anyLong())).thenReturn(petResponse);

        mockMvc.perform(get(TestConstants.URL_PET_BY_ID))
                .andExpect(status().isOk())
                .andExpect(jsonPath(TestConstants.JSON_ID).exists())
                .andExpect(jsonPath(TestConstants.JSON_NAME).exists())
                .andExpect(jsonPath(TestConstants.JSON_STATUS).exists());
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_GET_400)
    void getPet_debeRetornar400ConIdInvalido() throws Exception {
        mockMvc.perform(get(TestConstants.URL_PET_INVALID_ID))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    // ══════════════════════════════════════════════════════════
    // POST /api/pet
    // ══════════════════════════════════════════════════════════

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_200)
    void addPet_debeRetornar200() throws Exception {
        when(petService.addPet(any(PetRequest.class))).thenReturn(addPetResponse);

        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequest)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath(TestConstants.JSON_TRANSACTION_ID).exists())
                .andExpect(jsonPath(TestConstants.JSON_DATE_CREATED).exists())
                .andExpect(jsonPath(TestConstants.JSON_STATUS).exists())
                .andExpect(jsonPath(TestConstants.JSON_NAME).value(TestConstants.PET_NAME));
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_400_NOMBRE)
    void addPet_debeRetornar400SinNombre() throws Exception {
        petRequest.setName("");

        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_400_STATUS)
    void addPet_debeRetornar400SinStatus() throws Exception {
        petRequest.setStatus("");

        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_400_ID)
    void addPet_debeRetornar400SinId() throws Exception {
        petRequest.setId(null);

        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequest)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_400_BODY)
    void addPet_debeRetornar400ConBodyVacio() throws Exception {
        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestConstants.EMPTY_JSON_BODY))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName(TestConstants.DN_CTRL_POST_UUID)
    void addPet_transactionIdDebeSerUUID() throws Exception {
        when(petService.addPet(any())).thenReturn(addPetResponse);

        mockMvc.perform(post(TestConstants.URL_PET)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(petRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath(TestConstants.JSON_TRANSACTION_ID)
                        .value(TestConstants.TRANSACTION_ID));
    }
}
