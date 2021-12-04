package com.rafalstefanski.springvesselfinder.service;

import com.rafalstefanski.springvesselfinder.model.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {

    public String getAisToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("content-type", MediaType.APPLICATION_FORM_URLENCODED.toString());

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", "barentswatch@mailplus.pl:veselfinder");
        requestBody.add("client_secret", "1roprucafrit");
        requestBody.add("scope", "api");

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        ResponseEntity<Token> response =
                restTemplate.exchange("https://id.barentswatch.no/connect/token", HttpMethod.POST, formEntity, Token.class);

        return response.getBody().getTokenType() + " " + response.getBody().getAccessToken();
    }
}
