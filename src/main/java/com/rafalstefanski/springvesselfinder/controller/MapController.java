package com.rafalstefanski.springvesselfinder.controller;

import com.rafalstefanski.springvesselfinder.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MapController {

    private final TrackService trackService;

    @Autowired
    public MapController(TrackService trackService) {
        this.trackService = trackService;
    }

//    @GetMapping
//    public String getMap(Model model) {
//        model.addAttribute("tracks", trackService.getTracks());
//        return "index";
//    }
}
