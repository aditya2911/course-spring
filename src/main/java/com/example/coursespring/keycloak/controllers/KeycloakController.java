package com.example.coursespring.keycloak.controllers;


import com.example.coursespring.keycloak.dto.UserRegistrationRecord;
import com.example.coursespring.keycloak.service.KeycloakUserService;
import com.example.coursespring.keycloak.service.impl.KeycloakUserServiceImpl;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;

@RestController
@RequestMapping("/users")
@AllArgsConstructor

public class KeycloakController {



@Autowired
        private final KeycloakUserService keycloakUserService;


        @PostMapping("/register")
        public UserRegistrationRecord createUser(@RequestBody UserRegistrationRecord userRegistrationRecord) {

            return  keycloakUserService.createUser(userRegistrationRecord);
        }

        @GetMapping("/get-user")
        public UserRepresentation getUser(Principal principal) {

            return keycloakUserService.getUserById(principal.getName());
        }

    @GetMapping("/test")
    public String getUser() {

        return "test";
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


