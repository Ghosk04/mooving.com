package com.projetreseau.intermediaire;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class PositionResolver implements GraphQLQueryResolver, GraphQLMutationResolver{
   @Autowired
    private RestTemplate restTemplate ;

   @Value("${rest.template.base-url}")
    private String basePositionUrl;

    public List<Position> getAllPositions(){
        return Arrays.asList(restTemplate.getForObject(basePositionUrl+"/positions",Position[].class));

    }

    public List<Position> getPositionsByCentre(String centre){
        return Arrays.asList(restTemplate.getForObject(basePositionUrl+"/positions/centre/"+centre,Position[].class));
    }
    public Optional<Position> getPositionByRayon(int rayon){
        return Optional.ofNullable(restTemplate.getForObject(basePositionUrl+"/positions/rayon/"+rayon,Position.class));

    }

    public Position createPosition(String ville, int rayon, String centre, int tarif) {
        Position position = new Position();
        position.setVille(ville);
        position.setRayon(rayon);
        position.setCentre(centre);
        position.setTarif(tarif);
        return restTemplate.postForObject(basePositionUrl+ "/positions", position, Position.class);
    }

    public Boolean deletePosition(String ville) {
        restTemplate.delete(basePositionUrl+ "/positions/" + ville);
        return true;
    }

    public Float calculateTarif(Float startLatitude,Float startLongitude,Float endLatitude,Float endLongitude,Float distance){
        String url = basePositionUrl+ "/api/calculateTarif";
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setStartLatitude(startLatitude);
        locationRequest.setStartLongitude(startLongitude);
        locationRequest.setEndLatitude(endLatitude);
        locationRequest.setEndLongitude(endLongitude);
        locationRequest.setDistance(distance);
        return restTemplate.postForObject(url, locationRequest, Float.class);
    }

}
