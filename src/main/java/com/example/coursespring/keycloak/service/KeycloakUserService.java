package com.example.coursespring.keycloak.service;

 import com.example.coursespring.keycloak.dto.LoginRequest;
 import com.example.coursespring.keycloak.dto.UserRegistrationRecord;
 import com.fasterxml.jackson.databind.JsonNode;
 import jakarta.validation.constraints.NotNull;
 import org.keycloak.admin.client.Keycloak;
 import org.keycloak.admin.client.resource.UserResource;
 import org.keycloak.representations.AccessTokenResponse;
 import org.keycloak.representations.idm.UserRepresentation;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.RequestBody;

 import javax.ws.rs.core.Response;

public interface KeycloakUserService {

    Response createUser(UserRegistrationRecord userRegistrationRecord);
    UserRepresentation getUserById(String userId);
    void deleteUserById(String userId);
    void emailVerification(String userId);
    UserResource getUserResource(String userId);

    Keycloak userBuilder(String username, String password);

    AccessTokenResponse loginUser(@NotNull @RequestBody LoginRequest loginRequest);

    ResponseEntity<JsonNode> refreshToken(@NotNull String refreshToken);
}
