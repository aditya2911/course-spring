package com.example.coursespring.keycloak.service.impl;
import org.springframework.http.*;

import com.example.coursespring.keycloak.config.KeycloakInitializerConfigurationProperties;
import com.example.coursespring.keycloak.dto.LoginRequest;
import com.example.coursespring.keycloak.dto.UserRegistrationRecord;
import com.example.coursespring.keycloak.service.KeycloakUserService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;



import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j


public class KeycloakUserServiceImpl implements KeycloakUserService {
    private final KeycloakInitializerConfigurationProperties keycloakInitializerConfigurationProperties;



    @Value("${keycloak-initializer.application-realm}")
    private String realm;
    private Keycloak keycloak;


    @Autowired
    public KeycloakUserServiceImpl(Keycloak keycloak,KeycloakInitializerConfigurationProperties kc) {
        this.keycloak = keycloak;
        this.keycloakInitializerConfigurationProperties = kc;
    }

    @Override
    public Response createUser(UserRegistrationRecord userRegistrationRecord) {

        UserRepresentation user=new UserRepresentation();
        user.setEnabled(true);
        user.setUsername(userRegistrationRecord.userName());
        user.setEmail(userRegistrationRecord.email());
        user.setFirstName(userRegistrationRecord.firstName());
        user.setLastName(userRegistrationRecord.lastName());
        user.setEmailVerified(false);


        CredentialRepresentation credentialRepresentation=new CredentialRepresentation();
        credentialRepresentation.setValue(userRegistrationRecord.password());
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        List<CredentialRepresentation> list = new ArrayList<>();
        list.add(credentialRepresentation);
        user.setCredentials(list);

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(user);
return response;
//        if(Objects.equals(201,response.getStatus())){
//
//            List<UserRepresentation> representationList = usersResource.searchByUsername(userRegistrationRecord.username(), true);
//            if(!CollectionUtils.isEmpty(representationList)){
//                UserRepresentation userRepresentation1 = representationList.stream().filter(userRepresentation -> Objects.equals(false, userRepresentation.isEmailVerified())).findFirst().orElse(null);
//                assert userRepresentation1 != null;
//            //    emailVerification(userRepresentation1.getId());
//            }
//            return  userRegistrationRecord;
        }

//        response.readEntity()



    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    @Override
    public UserRepresentation getUserById(String userId) {


        return  getUsersResource().get(userId).toRepresentation();
    }

    @Override
    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }


    @Override
    public void emailVerification(String userId){

        UsersResource usersResource = getUsersResource();
        usersResource.get(userId).sendVerifyEmail();
    }

    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

    @Override
    public Keycloak userBuilder(String username, String password) {
        return   KeycloakBuilder.builder()


                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakInitializerConfigurationProperties.getApplicationRealm())
                .clientId(keycloakInitializerConfigurationProperties.getClientId())
                .username(username)
                .password(password)
                .serverUrl(keycloakInitializerConfigurationProperties.getUrl())
                .build();
    }

    @Override
    public  AccessTokenResponse loginUser(LoginRequest loginRequest) {
        Keycloak user = userBuilder(loginRequest.username(), loginRequest.password());

        AccessTokenResponse response = user.tokenManager().getAccessToken();
        user.close();
        return response;


    }

    @Override
    public ResponseEntity<JsonNode> refreshToken(String refreshToken) {
        String url = keycloakInitializerConfigurationProperties.getUrl()
                +"/realms/"
                +keycloakInitializerConfigurationProperties.getApplicationRealm()
                +"/protocol/openid-connect/token";

        RestTemplate resttemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type", "refresh_token");
        map.add("client_id", keycloakInitializerConfigurationProperties.getClientId());
        map.add("refresh_token",refreshToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<JsonNode> response = resttemplate.exchange(url, HttpMethod.POST,request,JsonNode.class);



        return response;

    }
}
