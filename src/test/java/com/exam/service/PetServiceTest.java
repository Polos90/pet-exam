package com.exam.service;

import com.exam.client.PetStoreClient;
import com.exam.constants.TestConstants;
import com.exam.model.request.PetRequest;
import com.exam.model.response.AddPetResponse;
import com.exam.model.response.PetResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName(TestConstants.DN_SVC_CLASS)
class PetServiceTest {

    @Mock
    private PetStoreClient petStoreClient;

    @InjectMocks
    private PetService petService;

    private PetRequest  petRequest;
    private PetResponse petResponse;

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
    }

    // ══════════════════════════════════════════════════════════
    // GET
    // ══════════════════════════════════════════════════════════

    @Test
    @DisplayName(TestConstants.DN_SVC_GET_OK)
    void getPetById_debeRetornarPet() {
        when(petStoreClient.getPetById(TestConstants.PET_ID)).thenReturn(petResponse);

        PetResponse result = petService.getPetById(TestConstants.PET_ID);

        assertNotNull(result);
        assertEquals(TestConstants.PET_ID,     result.getId());
        assertEquals(TestConstants.PET_NAME,   result.getName());
        assertEquals(TestConstants.PET_STATUS, result.getStatus());
        verify(petStoreClient, times(1)).getPetById(TestConstants.PET_ID);
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_GET_FIELDS)
    void getPetById_camposNoNulos() {
        when(petStoreClient.getPetById(anyLong())).thenReturn(petResponse);

        PetResponse result = petService.getPetById(TestConstants.PET_ID);

        assertAll(TestConstants.MSG_ALL_FIELDS_HAVE_VALUE,
                () -> assertNotNull(result.getId(),     TestConstants.MSG_ID_NOT_NULL),
                () -> assertNotNull(result.getName(),   TestConstants.MSG_NAME_NOT_NULL),
                () -> assertNotNull(result.getStatus(), TestConstants.MSG_STATUS_NOT_NULL)
        );
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_GET_CACHE)
    void getPetById_debeUsarCache() {
        when(petStoreClient.getPetById(TestConstants.PET_ID)).thenReturn(petResponse);

        // Llamar dos veces al mismo ID
        petService.getPetById(TestConstants.PET_ID);
        petService.getPetById(TestConstants.PET_ID);

        // El cliente externo solo debe haberse llamado UNA vez
        verify(petStoreClient, times(1)).getPetById(TestConstants.PET_ID);
    }

    // ══════════════════════════════════════════════════════════
    // POST
    // ══════════════════════════════════════════════════════════

    @Test
    @DisplayName(TestConstants.DN_SVC_POST_FIELDS)
    void addPet_debeRetornarCamposRequeridos() {
        Map<String, Object> externalResponse = new HashMap<>();
        externalResponse.put(TestConstants.MAP_KEY_NAME,   TestConstants.PET_NAME);
        externalResponse.put(TestConstants.MAP_KEY_STATUS, TestConstants.PET_STATUS);

        when(petStoreClient.addPet(any(PetRequest.class))).thenReturn(externalResponse);

        AddPetResponse result = petService.addPet(petRequest);

        assertNotNull(result);
        assertNotNull(result.getTransactionId(), TestConstants.MSG_TRANSACTION_NOT_NULL);
        assertNotNull(result.getDateCreated(),   TestConstants.MSG_DATE_CREATED_NOT_NULL);
        assertNotNull(result.getName(),          TestConstants.MSG_NAME_NOT_NULL);
        assertNotNull(result.getStatus(),        TestConstants.MSG_STATUS_NOT_NULL);
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_POST_UUID)
    void addPet_transactionIdDebeSerUUID() {
        Map<String, Object> externalResponse = new HashMap<>();
        externalResponse.put(TestConstants.MAP_KEY_NAME,   TestConstants.PET_NAME);
        externalResponse.put(TestConstants.MAP_KEY_STATUS, TestConstants.PET_STATUS);

        when(petStoreClient.addPet(any())).thenReturn(externalResponse);

        AddPetResponse result = petService.addPet(petRequest);

        // UUID formato: xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx
        assertTrue(
                result.getTransactionId().matches(TestConstants.UUID_REGEX),
                TestConstants.MSG_UUID_FORMAT
        );
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_POST_UNIQUE)
    void addPet_cadaLlamadaGeneraTransactionIdUnico() {
        Map<String, Object> externalResponse = new HashMap<>();
        externalResponse.put(TestConstants.MAP_KEY_NAME,   TestConstants.PET_NAME);
        externalResponse.put(TestConstants.MAP_KEY_STATUS, TestConstants.PET_STATUS);

        when(petStoreClient.addPet(any())).thenReturn(externalResponse);

        AddPetResponse result1 = petService.addPet(petRequest);
        AddPetResponse result2 = petService.addPet(petRequest);

        assertNotEquals(
                result1.getTransactionId(),
                result2.getTransactionId(),
                TestConstants.MSG_UUID_UNIQUE
        );
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_POST_ONCE)
    void addPet_debeLlamarAlClienteUnaVez() {
        Map<String, Object> externalResponse = new HashMap<>();
        externalResponse.put(TestConstants.MAP_KEY_NAME,   TestConstants.PET_NAME);
        externalResponse.put(TestConstants.MAP_KEY_STATUS, TestConstants.PET_STATUS);

        when(petStoreClient.addPet(any())).thenReturn(externalResponse);

        petService.addPet(petRequest);

        verify(petStoreClient, times(1)).addPet(any(PetRequest.class));
    }

    @Test
    @DisplayName(TestConstants.DN_SVC_POST_NAME)
    void addPet_nombreDebeCoincidir() {
        Map<String, Object> externalResponse = new HashMap<>();
        externalResponse.put(TestConstants.MAP_KEY_NAME,   TestConstants.PET_NAME);
        externalResponse.put(TestConstants.MAP_KEY_STATUS, TestConstants.PET_STATUS);

        when(petStoreClient.addPet(any())).thenReturn(externalResponse);

        AddPetResponse result = petService.addPet(petRequest);

        assertEquals(TestConstants.PET_NAME, result.getName());
    }
}
