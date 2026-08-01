package com.exam.constants;

/**
 * Clase de constantes globales del proyecto.
 * Centraliza todos los strings: logs, mensajes, paths, campos JSON, validaciones y headers.
 */
public final class Constants {

    private Constants() {}

    // ── API paths ───────────────────────────────────────────────────────────────
    public static final String API_PET_PATH     = "/api/pet";
    public static final String PET_PATH         = "/pet";
    public static final String PET_ID_PATH      = "/pet/";

    // ── JSON / Map field names ──────────────────────────────────────────────────
    public static final String FIELD_ID             = "id";
    public static final String FIELD_NAME           = "name";
    public static final String FIELD_STATUS         = "status";
    public static final String FIELD_TRANSACTION_ID = "transactionId";
    public static final String FIELD_DATE_CREATED   = "dateCreated";
    public static final String FIELD_ERROR          = "error";
    public static final String FIELD_MENSAJE        = "mensaje";

    // ── Validation messages ─────────────────────────────────────────────────────
    public static final String MSG_ID_REQUIRED   = "El ID es requerido";
    public static final String MSG_NAME_BLANK    = "El nombre no puede estar vacío";
    public static final String MSG_STATUS_BLANK  = "El status no puede estar vacío";

    // ── Error / response messages ───────────────────────────────────────────────
    public static final String MSG_PET_NOT_FOUND        = "Pet no encontrado";
    public static final String MSG_INTERNAL_ERROR       = "Error interno del servidor";
    public static final String MSG_PET_DELETED          = "Pet eliminado correctamente";
    public static final String MSG_API_KEY_INVALID_JSON = "{\"error\": \"API Key invalida o no proporcionada\"}";

    // ── Security ────────────────────────────────────────────────────────────────
    public static final String HEADER_API_KEY    = "X-API-KEY";
    public static final String CONTENT_TYPE_JSON = "application/json";

    // ── Log separator ───────────────────────────────────────────────────────────
    public static final String LOG_SEPARATOR = "================================================";

    // ── Log messages — PetStoreClient ───────────────────────────────────────────
    public static final String LOG_CLIENT_GET       = "[PetStoreClient] GET {}";
    public static final String LOG_CLIENT_GET_RESP  = "[PetStoreClient] Response GET: {}";
    public static final String LOG_CLIENT_POST      = "[PetStoreClient] POST {} | Body: {}";
    public static final String LOG_CLIENT_POST_RESP = "[PetStoreClient] Response POST: {}";
    public static final String LOG_CLIENT_PUT       = "[PetStoreClient] PUT {} | Body: {}";
    public static final String LOG_CLIENT_PUT_DONE  = "[PetStoreClient] Pet actualizado, obteniendo datos actualizados...";
    public static final String LOG_CLIENT_DELETE    = "[PetStoreClient] DELETE {}";
    public static final String LOG_CLIENT_DELETE_OK = "[PetStoreClient] Pet eliminado correctamente";

    // ── Log messages — PetController ────────────────────────────────────────────
    public static final String LOG_CTRL_GET    = "[PetController] GET /api/pet/{}";
    public static final String LOG_CTRL_POST   = "[PetController] POST /api/pet | Body: {}";
    public static final String LOG_CTRL_PUT    = "[PetController] PUT /api/pet/{} | Body: {}";
    public static final String LOG_CTRL_DELETE = "[PetController] DELETE /api/pet/{}";

    // ── Log messages — ApiKeyFilter ─────────────────────────────────────────────
    public static final String LOG_FILTER_DENIED = "[ApiKeyFilter] Acceso denegado — X-API-KEY invalido o ausente";
    public static final String LOG_FILTER_OK     = "[ApiKeyFilter] X-API-KEY valido — request permitido";

    // ── Log messages — PetService ────────────────────────────────────────────────
    public static final String LOG_SVC_SEARCH       = "[PetService] Buscando pet con ID: {}";
    public static final String LOG_SVC_FOUND        = "[PetService] Pet encontrado:";
    public static final String LOG_SVC_FIELD_ID     = "  ID     : {}";
    public static final String LOG_SVC_FIELD_NOMBRE = "  Nombre : {}";
    public static final String LOG_SVC_FIELD_STATUS = "  Status : {}";
    public static final String LOG_SVC_ADDING       = "[PetService] Agregando nuevo pet: {}";
    public static final String LOG_SVC_ADDED        = "[PetService] Pet agregado exitosamente:";
    public static final String LOG_SVC_TRANSACTION  = "  TransactionId : {}";
    public static final String LOG_SVC_DATE_CREATED = "  DateCreated   : {}";
    public static final String LOG_SVC_FIELD_NAME   = "  Name          : {}";
    public static final String LOG_SVC_UPDATING     = "[PetService] Actualizando pet ID: {}";
    public static final String LOG_SVC_UPDATED      = "[PetService] Pet actualizado:";
    public static final String LOG_SVC_DELETING     = "[PetService] Eliminando pet ID: {}";
    public static final String LOG_SVC_DELETED      = "[PetService] Pet eliminado correctamente";

}
