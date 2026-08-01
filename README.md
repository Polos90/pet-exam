# Prueba Técnica — SSr Backend Developer
## Francisco Gallardo Perez

### Configuración del proyecto
- **Spring Boot:** 3.2.7
- **Build:** Gradle 8.8 - Groovy
- **Java:** 17
- **Packaging:** Jar
- **Dependencias:** Spring Web

---

### Estructura de carpetas
```
src/main/java/com/exam/
├── controller/
│   └── PetController.java         ← APIs REST (GET, POST)
├── service/
│   └── PetService.java            ← Lógica de negocio + cache en memoria
├── client/
│   └── PetStoreClient.java        ← Conexión a API externa PetStore
├── model/
│   ├── request/
│   │   └── PetRequest.java        ← Datos de entrada con validaciones
│   └── response/
│       ├── PetResponse.java       ← Salida GET
│       └── AddPetResponse.java    ← Salida POST
├── exception/
│   └── GlobalExceptionHandler.java ← Manejo global de errores
├── filter/
│   └── ApiKeyFilter.java          ← Seguridad por API Key
└── ExamApplication.java
```

---

### Cómo ejecutar
```bash
./gradlew bootRun
```
Servidor en: **http://localhost:8080**

---

### Endpoints

#### GET /api/pet/{petId}
```
GET http://localhost:8080/api/pet/10000023
Header: X-API-KEY: exam-secret-key-2024
```
**Response 200:**
```json
{
  "id": 10000023,
  "name": "testingPet1",
  "status": "available"
}
```

#### POST /api/pet
```
POST http://localhost:8080/api/pet
Header: X-API-KEY: exam-secret-key-2024
```
**Body:**
```json
{
  "id": 10000023,
  "status": "available",
  "name": "testingPet1"
}
```
**Response 200:**
```json
{
  "transactionId": "60cc5c22-3250-4e07-a519-a6dab99c6713",
  "dateCreated": "2024-06-25T19:22:42.181753",
  "status": "available",
  "name": "testingPet1"
}
```





---

### Errores manejados
| Código | Cuando ocurre |
|--------|--------------|
| 400 | Body inválido o campos vacíos |
| 401 | X-API-KEY ausente o incorrecto |
| 404 | Pet no encontrado en PetStore |
| 500 | Error interno del servidor |

---

### API Externa consumida
**PetStore Swagger:** https://petstore.swagger.io/v2
- `GET /pet/{petId}` — obtener pet por ID
- `POST /pet` — agregar nuevo pet

