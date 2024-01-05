package com.example.coursespring.keycloak.controllers;


import com.example.coursespring.keycloak.dto.LoginRequest;
import com.example.coursespring.keycloak.dto.RefreshTokenRequest;
import com.example.coursespring.keycloak.dto.UserRegistrationRecord;
import com.example.coursespring.keycloak.service.KeycloakUserService;
import com.example.coursespring.keycloak.service.impl.KeycloakUserServiceImpl;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import okhttp3.Cookie;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import java.net.HttpCookie;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class KeycloakController {



@Autowired
        private final KeycloakUserService keycloakUserService;


        @PostMapping("/register")
        public ResponseEntity<String> createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {
            Response response = keycloakUserService.createUser(userRegistrationRecord);
            if(response.getStatus() == 201){
                return  ResponseEntity.status(HttpStatus.OK).body("User has been created");
            }else{
                return  ResponseEntity.status(HttpStatus.FORBIDDEN).body("FORBIDDEN");
            }

        }

        @GetMapping("/get-user")
        public UserRepresentation getUser(Principal principal) {

            return keycloakUserService.getUserById(principal.getName());
        }

    @GetMapping("/test")
    public String getUser() {

        return "test";
    }


    @PostMapping("/login")

    public ResponseEntity<AccessTokenResponse> loginUser(@RequestBody LoginRequest loginRequest){
        AccessTokenResponse token = null;

             try {

                         token = keycloakUserService.loginUser(loginRequest);
                return ResponseEntity.status(HttpStatus.OK).body(token);
            }
            catch (BadRequestException e) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(token);

            }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<JsonNode> getRefreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
            ResponseEntity<JsonNode> token = null;


                token = keycloakUserService.refreshToken(refreshTokenRequest.refresh_token());
                if(token.getStatusCode().equals(HttpStatus.OK)){
                    return  token;
                }else{
                    return ResponseEntity.status(token.getStatusCode() ).body(token.getBody());
                }

    }

    @DeleteMapping("/{userId}")
        public void deleteUserById(@PathVariable String userId) {


            keycloakUserService.deleteUserById(userId);
        }



        @PutMapping("/{userId}/send-verify-email")
        public void sendVerificationEmail(@PathVariable String userId) {
            keycloakUserService.emailVerification(userId);
        }
    }


