package com.mongodb.starter.services;

import com.mongodb.starter.model.patient.v4.Patient;
import org.bson.Document;


import java.util.Collection;

public interface PatientService {

    Patient getFhirPatientByUUID(String uuid);

    Collection<Patient> getFhirPatientByTenantName( String name, String tenantId);

    Collection<Document> getFlatPatientByUUID(String uuid);
}
