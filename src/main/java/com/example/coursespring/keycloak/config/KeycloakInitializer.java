package com.example.coursespring.keycloak.config;

import com.fasterxml.jackson.databind.ObjectMapper;
 import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RealmRepresentation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


import javax.ws.rs.NotFoundException;
import java.io.IOException;
import java.util.List;
@Slf4j
@Service
public class KeycloakInitializer implements InitializingBean {

    private   Keycloak keycloak;
    private   KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties;

    private ObjectMapper mapper;

    private static String REALM_ID;

    private static final String INIT_KEYCLOAK_PATH = "initializer/init-keycloak.json";


    private boolean isRealmAlreadyInitialized= false;
    public KeycloakInitializer(Keycloak keycloak,
                               KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties,
                               ObjectMapper mapper) {
        this.keycloak = keycloak;
        this.keycloakInitializerConfigurationProperties = keycloakInitializerConfigurationProperties;
        this.mapper = mapper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        REALM_ID = keycloakInitializerConfigurationProperties.getApplicationRealm();

        if (keycloakInitializerConfigurationProperties.initializeOnStartup()) {
            init(false);
        }
    }


    public void init(boolean overwrite) {

        log.info("Initializer start");

        List<RealmRepresentation> realms = keycloak.realms().findAll();
        isRealmAlreadyInitialized =
                realms.stream().anyMatch(realm -> realm.getId().equals(REALM_ID));

//        if (isAlreadyInitialized && overwrite) {
//            reset();
//        }

        if (!isRealmAlreadyInitialized || overwrite) {
            initKeycloak();

            log.info("Keycloak initialized successfully");
        } else {
            log.warn("Keycloak initialization cancelled: realm already exist");
        }
    }

    private void initKeycloak() {



        initKeycloakRealm();
    }

    private void initKeycloakRealm() {
        RealmRepresentation realmRepresentation = new RealmRepresentation();
        realmRepresentation.setRealm(REALM_ID);
        realmRepresentation.setId(REALM_ID);

        Resource resource = new ClassPathResource(INIT_KEYCLOAK_PATH);
        try {
            RealmRepresentation realmRepresentationToImport =
                    mapper.readValue(resource.getFile(), RealmRepresentation.class);
            keycloak.realms().create(realmRepresentationToImport);
        } catch (IOException e) {
            String errorMessage =
                    String.format("Failed to import keycloak realm representation : %s", e.getMessage());
            log.error(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public void reset() {
        try {
            keycloak.realm(REALM_ID).remove();
        } catch (NotFoundException e) {
            log.error("Failed to reset Keycloak", e);
        }
    }
}
