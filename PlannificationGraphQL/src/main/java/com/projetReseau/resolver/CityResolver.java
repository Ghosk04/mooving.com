package com.projetReseau.resolver;

import com.projetReseau.model.travel.City;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Component
public class CityResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public City createCity(City city) {
        String url = externalApiBaseUrl + "/cities";
        return restTemplate.postForObject(url, city, City.class);
    }

    public City getCityById(UUID id) {
        String url = externalApiBaseUrl + "/cities/{id}";
        return restTemplate.getForObject(url, City.class, id);
    }

    public List<City> getCitiesByName(String name) {
        String url = externalApiBaseUrl + "/cities/name/{name}";
        City[] cities = restTemplate.getForObject(url, City[].class, name);
        return Arrays.asList(cities);
    }

    public List<City> getAllCities() {
        String url = externalApiBaseUrl + "/cities";
        City[] cities = restTemplate.getForObject(url, City[].class);
        return Arrays.asList(cities);
    }

    public City updateCity(UUID id, City city) {
        String url = externalApiBaseUrl + "/cities/{id}";
        restTemplate.put(url, city, id);
        return city;
    }

    public void deleteCity(UUID id) {
        String url = externalApiBaseUrl + "/cities/{id}";
        restTemplate.delete(url, id);
    }
}
