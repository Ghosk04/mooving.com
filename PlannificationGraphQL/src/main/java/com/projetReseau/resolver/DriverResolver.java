package com.projetReseau.resolver;

import com.projetReseau.model.driver.Driver;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class DriverResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public List<Driver> getAllDrivers() {
        String url = externalApiBaseUrl+ "/drivers";
        return Arrays.asList(restTemplate.getForObject(url,Driver[].class));
    }
    public Optional<Driver> getDriverById(UUID id) {
        String url = externalApiBaseUrl +"/drivers/{id}";
        return Optional.of(restTemplate.getForObject(url,Driver.class, id));
    }

    public Optional<Driver> getDriverByLicense(String license) {
        String url = externalApiBaseUrl+ "/drivers/license/{license}";
        return Optional.of(restTemplate.getForObject(url,Driver.class, license));
    }
    public List<Driver> getDriversByState(short state) {
        String url = externalApiBaseUrl+ "/drivers/state/{state}";
        return Arrays.asList(restTemplate.getForObject(url,Driver[].class,state));
    }
    public List<Driver> getDriverByResidence(short residence) {
        String url = externalApiBaseUrl+ "/drivers/residence/{residence}";
        return Arrays.asList(restTemplate.getForObject(url,Driver[].class,residence));
    }
    public Driver createDriver(String name,String license) {
        String url= externalApiBaseUrl+ "/drivers";
        Driver driver = new Driver();
        driver.setName(name);
        driver.setLicense(license);
        return restTemplate.postForObject(url,driver,Driver.class);
    }

}
