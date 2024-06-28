package com.projetReseau.resolver;

import com.projetReseau.model.vehicule.Category;
import com.projetReseau.model.vehicule.Vehicle;
import com.projetReseau.model.vehicule.VehiclePhoto;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
public class VehicleResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    // Vehicle operations
    public Vehicle createVehicle(Vehicle vehicle) {
        String url = externalApiBaseUrl + "/vehicles";
        return restTemplate.postForObject(url, vehicle, Vehicle.class);
    }

    public Vehicle getVehicleById(UUID id) {
        String url = externalApiBaseUrl + "/vehicles/{id}";
        return restTemplate.getForObject(url, Vehicle.class, id);
    }

    public Vehicle getVehicleByRegistrationNumber(String registrationNumber) {
        String url = externalApiBaseUrl + "/vehicles/registration/{registrationNumber}";
        return restTemplate.getForObject(url, Vehicle.class, registrationNumber);
    }

    public List<Vehicle> getVehiclesByState(short state) {
        String url = externalApiBaseUrl + "/vehicles/state/{state}";
        Vehicle[] vehicles = restTemplate.getForObject(url, Vehicle[].class, state);
        return Arrays.asList(vehicles);
    }

    public List<Vehicle> getVehiclesByCategory(String category) {
        String url = externalApiBaseUrl + "/vehicles/category";
        Vehicle[] vehicles = restTemplate.getForObject(url + "?category=" + category, Vehicle[].class);
        return Arrays.asList(vehicles);
    }

    public List<Vehicle> getAllVehicles() {
        String url = externalApiBaseUrl + "/vehicles";
        Vehicle[] vehicles = restTemplate.getForObject(url, Vehicle[].class);
        return Arrays.asList(vehicles);
    }

    public List<Vehicle> getAvailableVehiclesAt(LocalDateTime atTime) {
        String url = externalApiBaseUrl + "/vehicles/available/{atTime}";
        Vehicle[] vehicles = restTemplate.getForObject(url, Vehicle[].class, atTime);
        return Arrays.asList(vehicles);
    }

    public void deleteVehicle(UUID id) {
        String url = externalApiBaseUrl + "/vehicles/{id}";
        restTemplate.delete(url, id);
    }

    public Vehicle updateVehicle(UUID id, Vehicle vehicle) {
        String url = externalApiBaseUrl + "/vehicles/{id}";
        restTemplate.put(url, vehicle, id);
        return vehicle;
    }

    // Category operations
    public Category createCategory(Category category) {
        String url = externalApiBaseUrl + "/vehicles/categories";
        return restTemplate.postForObject(url, category, Category.class);
    }

    public Category getCategoryByCode(String code) {
        String url = externalApiBaseUrl + "/vehicles/categories/{code}";
        return restTemplate.getForObject(url, Category.class, code);
    }

    public List<Category> getAllCategories() {
        String url = externalApiBaseUrl + "/vehicles/categories";
        Category[] categories = restTemplate.getForObject(url, Category[].class);
        return Arrays.asList(categories);
    }

    public void deleteCategoryById(String code) {
        String url = externalApiBaseUrl + "/vehicles/categories/{code}";
        restTemplate.delete(url, code);
    }

    public Category updateCategoryById(String code, Category category) {
        String url = externalApiBaseUrl + "/vehicles/categories/{code}";
        restTemplate.put(url, category, code);
        return category;
    }

    // VehiclePhoto operations
    public VehiclePhoto createPhoto(VehiclePhoto vehiclePhoto) {
        String url = externalApiBaseUrl + "/vehicles/photos";
        return restTemplate.postForObject(url, vehiclePhoto, VehiclePhoto.class);
    }

    public void deletePhotoById(UUID id) {
        String url = externalApiBaseUrl + "/vehicles/photos/{id}";
        restTemplate.delete(url, id);
    }

    public VehiclePhoto getPhotoById(UUID id) {
        String url = externalApiBaseUrl + "/vehicles/photos/{id}";
        return restTemplate.getForObject(url, VehiclePhoto.class, id);
    }

    public List<VehiclePhoto> getAllPhotos() {
        String url = externalApiBaseUrl + "/vehicles/photos";
        VehiclePhoto[] photos = restTemplate.getForObject(url, VehiclePhoto[].class);
        return Arrays.asList(photos);
    }

    public List<VehiclePhoto> getAllPhotosByVehicleId(UUID vehicleId) {
        String url = externalApiBaseUrl + "/vehicles/{vehicleId}/photos";
        VehiclePhoto[] photos = restTemplate.getForObject(url, VehiclePhoto[].class, vehicleId);
        return Arrays.asList(photos);
    }

    public VehiclePhoto updatePhoto(UUID id, VehiclePhoto vehiclePhoto) {
        String url = externalApiBaseUrl + "/vehicles/photos/{id}";
        restTemplate.put(url, vehiclePhoto, id);
        return vehiclePhoto;
    }
}

