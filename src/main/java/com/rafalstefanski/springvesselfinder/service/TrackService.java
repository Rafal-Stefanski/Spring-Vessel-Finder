package com.rafalstefanski.springvesselfinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rafalstefanski.springvesselfinder.model.Datum;
import com.rafalstefanski.springvesselfinder.dto.Point;
import com.rafalstefanski.springvesselfinder.model.Track;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrackService {
    RestTemplate restTemplate = new RestTemplate();
    private String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjBCM0I1NEUyRkQ5OUZCQkY5NzVERDMxNDBDREQ4OEI1QzA5RkFDRjMiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJDenRVNHYyWi03LVhYZE1VRE4ySXRjQ2ZyUE0ifQ.eyJuYmYiOjE2Mzg2MjY0NDEsImV4cCI6MTYzODYzMDA0MSwiaXNzIjoiaHR0cHM6Ly9pZC5iYXJlbnRzd2F0Y2gubm8iLCJhdWQiOiJhcGkiLCJjbGllbnRfaWQiOiJiYXJlbnRzd2F0Y2hAbWFpbHBsdXMucGw6dmVzZWxmaW5kZXIiLCJzY29wZSI6WyJhcGkiXX0.g1t7ZWQchvaTObbJc8O1r9fmQoHEVBmlIpaWHomhkvQOsiFZrf1UrA0AeVWAaXVwpH76BgJ1Tpk1m8zMWiXRh5-pNWlaU1Mf6QQiACns5YjLW6HKXPN7PRz817qjdeuJdIN-3TpSp4q7U5CgppqkdGPEep56x9Od0ENLlRzvOKZUbWHhaOzNUJdhNnrco0_tROiXVcteMznc1ePRzPzuQKp7YUElicbSHW0sS6cZoPys85Hf1Mrzx0YIZd1w8b6uCufJI_3zeggdI0iwyeTTtJxb_X91qX07oAihQtRF2HiMt9f9L0DUfbkHkk1H-knt20OjutZwknloxd6As-mbZ-PJI17ayVGyjVVZQr6fbnEyKmxqy-14xnW5AwmQtbCOuK-vWTYrZ0W23T_eSqFeWF81zMNKshebbY72BiFmaNp3RrWF9gN-RTnksqvUOZhPpfPJERBvdZ3S7vGHTjj4QE2AE1UbCTGUFO_i01g9KLyYP8ktlAf0eplTSfeEIhpXb4dcDeqSkeG5dWn3ohs6rvxoz22lOb4Ihp9HiIYslutWHfgPoqB4LR5LsVWmRpjfbG3ySKnmmrFrXx7gyCJvM40KBxpMx9A08jsaxOGBmbc999_fGz6qTKF-2I7ucrXKFWh9MFk1ISphstJh6A8u47xp5VPRsmIs3pe-yR5jIYE";
    private String headerValue = "bearer " + token;

    public List<Point> getTracks() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", headerValue);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=10.09094&Xmax=10.67047&Ymin=63.3989&Ymax=63.58645",
                HttpMethod.GET,
                httpEntity,
                Track[].class);

        List<Point> collect = Stream.of(exchange.getBody()).map(
                track -> new Point(
                        track.getGeometry().getCoordinates().get(0),
                        track.getGeometry().getCoordinates().get(1),
                        track.getName(),
                        getDestination(track.getDestination(), track.getGeometry().getCoordinates()).getLongitude(),
                        getDestination(track.getDestination(), track.getGeometry().getCoordinates()).getLatitude()
                )
        ).collect(Collectors.toList());


        return collect;
    }

    public Datum getDestination(String destinationName, List<Double> coordinates) {
        try {
            String url = "http://api.positionstack.com/v1/forward?access_key=f9aae45e031a1e66eac64db90ffda427&query=" + destinationName;
            JsonNode data = restTemplate.getForObject(url, JsonNode.class).get("data").get(0);
            double latitude = data.get("latitude").asDouble();
            double longitude = data.get("longitude").asDouble();
            return new Datum(latitude, longitude);

        } catch (Exception ex) {
            return new Datum(coordinates.get(1), coordinates.get(0));
        }
    }
}
