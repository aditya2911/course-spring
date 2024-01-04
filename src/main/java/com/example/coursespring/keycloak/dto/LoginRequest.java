package com.example.coursespring.keycloak.dto;

import lombok.Data;


public record LoginRequest(  String username,String password) {

}
