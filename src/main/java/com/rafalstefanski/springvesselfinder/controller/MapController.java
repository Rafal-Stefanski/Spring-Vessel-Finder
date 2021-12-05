package com.rafalstefanski.springvesselfinder.controller;

import com.rafalstefanski.springvesselfinder.repository.VesselRepository;
import com.rafalstefanski.springvesselfinder.service.TokenService;
import com.rafalstefanski.springvesselfinder.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {

    private final TrackService trackService;
    private final TokenService tokenService;
    private final VesselRepository vesselRepository;


    @Autowired
    public MapController(TrackService trackService, TokenService tokenService, VesselRepository vesselRepository) {
        this.trackService = trackService;
        this.tokenService = tokenService;
        this.vesselRepository = vesselRepository;
    }

    @GetMapping
    public String getMap(Model model) {
        model.addAttribute("tracks", trackService.getTracks());
        model.addAttribute("vesselsList", vesselRepository.findAll());
        return "index";
    }

    // Api view with JSON for checking token
    @GetMapping("/token")
    public ResponseEntity<TokenService> getToken() {
        return new ResponseEntity<>(tokenService, HttpStatus.OK);
    }

    // Api view with JSON with Track and Vessel list
    @GetMapping("/vessels")
    public ResponseEntity<TrackService> getVessels() {
        return new ResponseEntity<>(trackService, HttpStatus.OK);
    }
}
