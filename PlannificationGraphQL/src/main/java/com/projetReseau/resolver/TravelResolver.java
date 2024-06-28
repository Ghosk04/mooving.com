package com.projetReseau.resolver;

import com.projetReseau.model.travel.Travel;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class TravelResolver implements GraphQLQueryResolver, GraphQLMutationResolver{

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public Travel createTravel(Travel travel) {
        String url = externalApiBaseUrl + "/travels";
        return restTemplate.postForObject(url, travel, Travel.class);
    }

    public Travel getTravelById(UUID id) {
        String url = externalApiBaseUrl + "/travels/{id}";
        return restTemplate.getForObject(url, Travel.class, id);
    }

    public List<Travel> getAllTravels() {
        String url = externalApiBaseUrl + "/travels";
        Travel[] travels = restTemplate.getForObject(url, Travel[].class);
        return Arrays.asList(travels);
    }

    public List<Travel> getTravelsByState(short state) {
        String url = externalApiBaseUrl + "/travels/state/{state}";
        Travel[] travels = restTemplate.getForObject(url, Travel[].class, state);
        return Arrays.asList(travels);
    }

    public Travel updateTravel(UUID id, Travel travel) {
        String url = externalApiBaseUrl + "/travels/{id}";
        restTemplate.put(url, travel, id);
        return travel;
    }

    public List<Travel> getTravelsBetween(LocalDate startDate, LocalDate endDate) {
        String url = externalApiBaseUrl + "/travels/into?startDate={startDate}&endDate={endDate}";
        Travel[] travels = restTemplate.getForObject(url, Travel[].class, startDate, endDate);
        return Arrays.asList(travels);
    }

    public void deleteTravel(UUID id) {
        String url = externalApiBaseUrl + "/travels/{id}";
        restTemplate.delete(url, id);
    }
}
