package com.mongodb.starter.controllers;

import com.mongodb.starter.exception.EntityNotFoundException;
import com.mongodb.starter.model.patient.v4.Patient;
import com.mongodb.starter.services.PatientService;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/patient")
@Component
public class PatientController {

    private final static Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
    private final PatientService patientService;


    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/fhir/{uuid}/")
    Patient getFhirPatientByUUID(@PathVariable String uuid) {
        return patientService.getFhirPatientByUUID(uuid);
    }

    @GetMapping("/fhir/{name}/{tenantId}/")
    Collection<Patient> getFhirPatientByUUID(@PathVariable String name, @PathVariable String tenantId) {
        return patientService.getFhirPatientByTenantName(name, tenantId);
    }

    @GetMapping("/flat/{uuid}/")
    Collection<Document> getFlatPatientByUUID(@PathVariable String uuid) {
        return patientService.getFlatPatientByUUID(uuid);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "MongoDB didn't find any document.")
    public final void handleNotFoundExceptions(EntityNotFoundException e) {
        LOGGER.info("=> Patient not found: {}", e.toString());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Internal Server Error")
    public final void handleAllExceptions(RuntimeException e) {
        LOGGER.error("=> Internal server error.", e);
    }
}
