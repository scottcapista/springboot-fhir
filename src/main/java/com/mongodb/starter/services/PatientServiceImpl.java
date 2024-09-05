
package com.mongodb.starter.services;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Field;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.starter.exception.EntityNotFoundException;
import com.mongodb.starter.model.patient.v4.Patient;
import org.bson.BsonArray;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.mongodb.client.model.Aggregates.replaceRoot;
import static com.mongodb.client.model.Aggregates.set;
import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;
import static org.springframework.data.mongodb.core.aggregation.SelectionOperators.First.first;

@Service
public class PatientServiceImpl implements PatientService {

    private final static Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
    private final MongoCollection<Patient> enriched_Patient;
    private final MongoCollection<Document> flattened_Patient;

    @Autowired
    public PatientServiceImpl(MongoTemplate mongoTemplate) {
        this.enriched_Patient = mongoTemplate.getDb().getCollection("enriched_Patient", Patient.class);
        this.flattened_Patient = mongoTemplate.getDb().getCollection("enriched_Patient", Document.class);
    }

    public Patient getFhirPatientByUUID(String uuid) {
        LOGGER.info("=> Searching patient by ID: {}", uuid);
        Bson filter = and(
                eq("metadata.searchParameters.key", "identifier"),
                eq("metadata.searchParameters.value", uuid)
        );
        Patient doc = enriched_Patient.find(filter).projection(projectResource()).first();

        if (doc == null) {
            throw new EntityNotFoundException("singlePatient ID lookup");
        }
        return doc;
    }

    public Collection<Patient> getFhirPatientByTenantName(String name, String tenantId) {
        LOGGER.info("=> Searching patient by Tenant: {} and Name: {}", tenantId, name);
        Bson filter = and(
                eq("metadata.searchParameters.key", "name"),
                eq("metadata.searchParameters.value", name),
                eq("metadata.tenant_id", tenantId)
        );
        Collection<Patient> docs = enriched_Patient.find(filter).projection(projectResource()).into(new ArrayList<>());
        if (docs.isEmpty()) {
            throw new EntityNotFoundException("name and tenantId lookup not found");
        }
        return docs;
    }

    public Collection<Document> getFlatPatientByUUID(String uuid) {
        LOGGER.info("=> Searching patient by ID: {}", uuid);

        //match on the _id the same way as with find
        Bson matchStage = Aggregates.match(
                Filters.and(
                        Filters.eq("metadata.searchParameters.key", "identifier"),
                        Filters.eq("metadata.searchParameters.value", uuid)
                )
        );

        // Get a specific record from the array based on extension.url field
        Bson projectStage = Aggregates.project(
                Projections.fields(
                        Projections.computed("resource_type", "$resource.resourceType"),
                        Projections.computed("race", new Document("$first", new Document("$filter", new Document("input", "$resource.extension")
                                .append("as", "extension")
                                .append("cond", new Document("$eq", Arrays.asList("$$extension.url", "http://hl7.org/fhir/us/core/StructureDefinition/us-core-race"))))))
                )
        );

        // set the fields to their final value before returning to client
        Bson setStage = Aggregates.set(
                new Field<>("race", new Document("$first", "$race.extension.valueString")),
                new Field<>("_id", new Document("$toString", "$_id"))
        );

        // Build the aggregation pipeline
        List<Bson> pipeline = Arrays.asList(matchStage, projectStage, setStage);
        Collection<Document> doc = flattened_Patient.aggregate(pipeline).into(new ArrayList<>());
        LOGGER.info("=> Found {} patients", doc.size());
        if (doc.size() == 0) {
            for (Bson stage : pipeline) {
                LOGGER.info("Pipeline stage: {}", stage.toBsonDocument(Document.class, flattened_Patient.getCodecRegistry()).toJson());
            }
            throw new EntityNotFoundException("singlePatient ID lookup failed");
        }
        return doc;
    }

    private Bson projectResource(){
        return fields(
            include("resource"),
            exclude("_id")
        );
    }
}
