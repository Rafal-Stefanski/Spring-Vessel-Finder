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
    private String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjBCM0I1NEUyRkQ5OUZCQkY5NzVERDMxNDBDREQ4OEI1QzA5RkFDRjMiLCJ0eXAiOiJhdCtqd3QiLCJ4NXQiOiJDenRVNHYyWi03LVhYZE1VRE4ySXRjQ2ZyUE0ifQ.eyJuYmYiOjE2Mzg1NTIwOTgsImV4cCI6MTYzODU1NTY5OCwiaXNzIjoiaHR0cHM6Ly9pZC5iYXJlbnRzd2F0Y2gubm8iLCJhdWQiOiJhcGkiLCJjbGllbnRfaWQiOiJiYXJlbnRzd2F0Y2hAbWFpbHBsdXMucGw6dmVzZWxmaW5kZXIiLCJzY29wZSI6WyJhcGkiXX0.OtT2abjQd68JDqGyJCT5t62ajYTsWo7uu0Tkve7wEanBtuJEDBVGbPUSG8C6JfF5tP-VL00VF98hvKZQuwMR2f2aiAa5bi1Qoi_mZiBIWP0OBrudcLAiWV9uV1UlhY41xFzU-AsgepMCZvzZuoGvB45ik_hFVnXvECBUkqrv5RB4mlBtQ0ZpDWN2zlSlAVja3x6_gL-PoUCTFfSGs9et0PnNzSVmm1C2-Z2e-wFuHImO9KrlSnXmetvKHpwgKntDHdLn_6BNzCLZYCrzP9fgfXFZQvkJxfOD_t8YXzdb4wi7qoYqaDYiseMNXmcuPZWzq0dYqWOGuT8KnNBAKQAVZeJB5fQ6IrQvLH_aEvSq2L6Qke4v-v8WDwbiobTz5ufaKJdd4XbagwlliP-y6yCD3W38Ogkm7swBKKbftOIAEUE33TXgw4DrRm0CSU9qhBa9lhHY1r-C8VYsA4cthxSVCTCrXiZdii0r5t1VTvytIJ_Arr2x6FFYhXrrPxHZTdJQymweRQn_S0U6LJs8W5CkJ8riCx6XgEpDRqaF6JFxjBaSDyCO2fwyyfJKSdP0EWCxs8MMMlqeuTBGTEkgviq8eq8h_12CGqyqCk50B0PKNbhX_ovI46I2WU6cj6B_G-jU7Gd9VIHX4-dNmtdcWpPf0z7kx-2Ukg7PkFEBgVDgb94";
    private String headerValue = "bearer " + token;
    private TokenService tokenService;

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


        System.out.println(tokenService.getAisToken().toString());


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
