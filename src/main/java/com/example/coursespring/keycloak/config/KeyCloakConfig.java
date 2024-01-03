package com.example.coursespring.keycloak.config;



import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyCloakConfig {




    private final  KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties;

    @Autowired
    KeyCloakConfig(KeycloakInitializerConfigurationProperties kc){
        this.keycloakInitializerConfigurationProperties = kc;
    }



@Bean
    public Keycloak keycloak(){
        return KeycloakBuilder.builder()


                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakInitializerConfigurationProperties.getMasterRealm())
                .clientId(keycloakInitializerConfigurationProperties.getClientId())
                .username(keycloakInitializerConfigurationProperties.getUsername())
                .password(keycloakInitializerConfigurationProperties.getPassword())
                .serverUrl(keycloakInitializerConfigurationProperties.getUrl())
                .build();
    }
}
