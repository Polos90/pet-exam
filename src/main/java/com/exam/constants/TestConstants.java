package com.exam.constants;

/**
 * Constantes de prueba — centraliza todos los strings usados en los tests.
 * Cubre: datos de prueba, URLs, jsonPaths, mensajes de aserción, regex y @DisplayName.
 */
public final class TestConstants {

    private TestConstants() {}

    // ── Datos de prueba (test data) ─────────────────────────────────────────────
    public static final Long   PET_ID     = 10000023L;
    public static final String PET_NAME   = "testingPet1";
    public static final String PET_STATUS = "available";

    // ── Valores fijos en respuestas ─────────────────────────────────────────────
    public static final String TRANSACTION_ID = "60cc5c22-3250-4e07-a519-a6dab99c6713";
    public static final String DATE_CREATED   = "2024-06-25T19:22:42.181753";

    // ── URL paths ───────────────────────────────────────────────────────────────
    public static final String URL_PET               = "/api/pet";
    public static final String URL_PET_BY_ID         = "/api/pet/10000023";
    public static final String URL_PET_INVALID_ID    = "/api/pet/abc";

    // ── Map keys (cuerpo de respuesta externa) ──────────────────────────────────
    public static final String MAP_KEY_NAME   = "name";
    public static final String MAP_KEY_STATUS = "status";

    // ── JsonPath expressions ────────────────────────────────────────────────────
    public static final String JSON_ID             = "$.id";
    public static final String JSON_NAME           = "$.name";
    public static final String JSON_STATUS         = "$.status";
    public static final String JSON_TRANSACTION_ID = "$.transactionId";
    public static final String JSON_DATE_CREATED   = "$.dateCreated";

    // ── Body vacío ──────────────────────────────────────────────────────────────
    public static final String EMPTY_JSON_BODY = "{}";

    // ── Regex UUID v4 ───────────────────────────────────────────────────────────
    public static final String UUID_REGEX = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}";

    // ── Mensajes de aserción — PetServiceTest ───────────────────────────────────
    public static final String MSG_ALL_FIELDS_HAVE_VALUE = "Todos los campos deben tener valor";
    public static final String MSG_ID_NOT_NULL           = "ID no debe ser null";
    public static final String MSG_NAME_NOT_NULL         = "Name no debe ser null";
    public static final String MSG_STATUS_NOT_NULL       = "Status no debe ser null";
    public static final String MSG_TRANSACTION_NOT_NULL  = "TransactionId no debe ser null";
    public static final String MSG_DATE_CREATED_NOT_NULL = "DateCreated no debe ser null";
    public static final String MSG_UUID_FORMAT           = "TransactionId debe tener formato UUIDv4";
    public static final String MSG_UUID_UNIQUE           = "Cada llamada debe generar un UUID diferente";

    // ── @DisplayName — PetServiceTest ───────────────────────────────────────────
    public static final String DN_SVC_CLASS        = "PetService — Pruebas Unitarias";
    public static final String DN_SVC_GET_OK       = "GET - Debe obtener pet por ID correctamente";
    public static final String DN_SVC_GET_FIELDS   = "GET - Debe retornar campos no nulos";
    public static final String DN_SVC_GET_CACHE    = "GET - Debe usar cache en la segunda consulta";
    public static final String DN_SVC_POST_FIELDS  = "POST - Debe retornar transactionId y dateCreated";
    public static final String DN_SVC_POST_UUID    = "POST - TransactionId debe tener formato UUIDv4";
    public static final String DN_SVC_POST_UNIQUE  = "POST - Cada llamada debe generar un transactionId unico";
    public static final String DN_SVC_POST_ONCE    = "POST - Debe llamar al cliente externo exactamente una vez";
    public static final String DN_SVC_POST_NAME    = "POST - El nombre del response debe coincidir con el de la API externa";

    // ── @DisplayName — PetControllerTest ────────────────────────────────────────
    public static final String DN_CTRL_CLASS            = "PetController — Pruebas Unitarias";
    public static final String DN_CTRL_GET_200          = "GET /api/pet/{petId} - Debe retornar 200 con datos del pet";
    public static final String DN_CTRL_GET_FIELDS       = "GET /api/pet/{petId} - Response debe tener los 3 campos requeridos";
    public static final String DN_CTRL_GET_400          = "GET /api/pet/{petId} - Debe retornar 400 si el ID no es numerico";
    public static final String DN_CTRL_POST_200         = "POST /api/pet - Debe retornar 200 con transactionId y dateCreated";
    public static final String DN_CTRL_POST_400_NOMBRE  = "POST /api/pet - Debe retornar 400 si el nombre esta vacio";
    public static final String DN_CTRL_POST_400_STATUS  = "POST /api/pet - Debe retornar 400 si el status esta vacio";
    public static final String DN_CTRL_POST_400_ID      = "POST /api/pet - Debe retornar 400 si el ID es null";
    public static final String DN_CTRL_POST_400_BODY    = "POST /api/pet - Debe retornar 400 si el body esta vacio";
    public static final String DN_CTRL_POST_UUID        = "POST /api/pet - TransactionId en response debe ser un UUID valido";

}
