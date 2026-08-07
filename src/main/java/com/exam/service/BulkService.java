package com.exam.service;

import com.exam.client.PetStoreClient;
import com.exam.model.response.BulkResponse;
import com.exam.model.response.ResultadoResponse;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;


@Service
public class BulkService {

    private static final Logger log = LoggerFactory.getLogger(BulkService.class);
    private final ExecutorService petExecutor;
    private final PetStoreClient petStoreClient;

    public BulkService(
            ExecutorService petExecutor,
            PetStoreClient petStoreClient
    ){
        this.petExecutor = petExecutor;
        this.petStoreClient = petStoreClient;
    }

    public BulkResponse findPets(List<Long> petIds){

        String transactionId = UUID.randomUUID().toString();

        List<CompletableFuture<ResultadoResponse>> futures = petIds.stream()
                        .map(id -> CompletableFuture.supplyAsync(() -> getPet(id, transactionId), petExecutor))
                        .collect(Collectors.toList());

        List<ResultadoResponse> results = futures.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList());

        long success = results.stream()
                        .filter(ResultadoResponse::isSuccess)
                        .count();

        BulkResponse response = new BulkResponse();

        response.setTransactionId(transactionId);
        response.setTotalRequested(petIds.size());
        response.setTotalSuccess((int)success);
        response.setTotalFailed(
                petIds.size() - (int)success
        );

        response.setResults(results);
        return response;

    }

    private ResultadoResponse getPet(
            Long petId,
            String transactionId
    ){

        long start = System.currentTimeMillis();
        ResultadoResponse result = new ResultadoResponse();
        result.setPetId(petId);

        try {
            log.info("transactionId={}, petId={}, thread={}",
                    transactionId,
                    petId,
                    Thread.currentThread().getName()
            );
            var pet = petStoreClient.getPetById(petId);
            result.setSuccess(true);
            result.setName(pet.getName());
            result.setStatus(pet.getStatus());
        }catch(Exception e){
            result.setSuccess(false);
            result.setError("PET_NOT_FOUND");
        }finally {
            log.info(
                    "transactionId={}, petId={}, duration={} ms",
                    transactionId,
                    petId,
                    System.currentTimeMillis()-start
            );
        }
        return result;
    }
}