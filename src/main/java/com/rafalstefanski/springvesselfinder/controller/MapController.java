package com.rafalstefanski.springvesselfinder.controller;

import com.rafalstefanski.springvesselfinder.model.Token;
import com.rafalstefanski.springvesselfinder.service.TokenService;
import com.rafalstefanski.springvesselfinder.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MapController {

    private final TrackService trackService;
    private TokenService tokenService;


    @Autowired
    public MapController(TrackService trackService, TokenService tokenService) {
        this.trackService = trackService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public String getMap(Model model) {
        model.addAttribute("tracks", trackService.getTracks());
        return "index";
    }

    // Api view with JSON for checking token
    @GetMapping("/token")
    public ResponseEntity<TokenService> getToken() {
        return new ResponseEntity<>(tokenService, HttpStatus.OK);
    }
}
