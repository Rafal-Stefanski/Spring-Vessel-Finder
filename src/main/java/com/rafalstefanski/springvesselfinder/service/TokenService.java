package com.rafalstefanski.springvesselfinder.service;

import com.rafalstefanski.springvesselfinder.model.Token;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    private String aisToken;
    private String url = "https://id.barentswatch.no/connect/token";
    private Token token;

//    public TokenService(String asiToken) {
//        this.asiToken = asiToken;
//    }


    public ResponseEntity<Token> getAisToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        headers.add("content-type", MediaType.APPLICATION_FORM_URLENCODED.toString());
//        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "client_credentials");
        requestBody.add("client_id", "barentswatch@mailplus.pl:veselfinder");
        requestBody.add("client_secret", "1roprucafrit");
        requestBody.add("scope", "api");

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        ResponseEntity<Token> response =
                restTemplate.exchange("https://id.barentswatch.no/connect/token", HttpMethod.POST, formEntity, Token.class);


//        HttpEntity<String> entity = new HttpEntity<>("body", headers);
//
//        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//
//        aisToken = restTemplate.getForObject()


        return response;
    }
}
