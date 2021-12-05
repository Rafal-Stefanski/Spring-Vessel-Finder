package com.rafalstefanski.springvesselfinder.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.rafalstefanski.springvesselfinder.dto.VesselDto;
import com.rafalstefanski.springvesselfinder.model.Datum;
import com.rafalstefanski.springvesselfinder.model.Point;
import com.rafalstefanski.springvesselfinder.model.Track;
import com.rafalstefanski.springvesselfinder.model.vessel.Vessel;
import com.rafalstefanski.springvesselfinder.repository.VesselRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TrackService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final TokenService tokenService = new TokenService();
//    private final HttpHeaders httpHeaders = new HttpHeaders();
    private final VesselRepository vesselRepository;

    @Autowired
    public TrackService(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    public List<Point> getTracks() {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", tokenService.getAisToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        // location: Norway (loading time: approx 4-5 min)
//        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=4.83&Xmax=31.04&Ymin=57.90&Ymax=68.31",
        // location: Trondheim
//        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=10.09094&Xmax=10.67047&Ymin=63.3989&Ymax=63.58645",
        // location: Oslo
        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=10.035968&Xmax=11.230466&Ymin=59.146155&Ymax=59.960798",
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

    public List<Vessel> getVessels() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", tokenService.getAisToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        // location: Oslo
        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=10.035968&Xmax=11.230466&Ymin=59.146155&Ymax=59.960798",
                HttpMethod.GET,
                httpEntity,
                Track[].class);

        List<Vessel> vesselList = Stream.of(exchange.getBody()).map(
                vessel -> new Vessel(
                        vessel.getCallsign(),
                        vessel.getName(),
                        vessel.getCountry(),
                        vessel.getShipType(),
                        vessel.getDestination()
                )
        ).collect(Collectors.toList());



        return vesselList;
    }


    public List<VesselDto> getVesselsForDb() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", tokenService.getAisToken());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);

        // location: Oslo
        ResponseEntity<Track[]> exchange = restTemplate.exchange("https://www.barentswatch.no/bwapi/v2/geodata/ais/openpositions?Xmin=10.035968&Xmax=11.230466&Ymin=59.146155&Ymax=59.960798",
                HttpMethod.GET,
                httpEntity,
                Track[].class);

        return Stream.of(Objects.requireNonNull(exchange.getBody())).map(
                vessel -> new VesselDto(
                        vessel.getCallsign(),
                        vessel.getName(),
                        vessel.getCountry(),
                        vessel.getShipType(),
                        vessel.getDestination()
                )
        ).collect(Collectors.toList());
    }


    @EventListener(ApplicationReadyEvent.class)
    public void initDb() {
//        vesselRepository.saveAll(getVessels());
        vesselRepository.saveAll(getVesselsForDb());

        System.out.print(vesselRepository.findAll());
    }


}
