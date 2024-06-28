package com.projetReseau.resolver;

import com.projetReseau.model.travel.Travel;
import com.projetReseau.model.travel.TravelHistory;
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
public class TravelHistoryResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public TravelHistory createTravelHistory(Travel travel) {
        String url = externalApiBaseUrl + "/histories";
        return restTemplate.postForObject(url, travel, TravelHistory.class);
    }

    public TravelHistory getTravelHistoryById(UUID id) {
        String url = externalApiBaseUrl + "/histories/{id}";
        return restTemplate.getForObject(url, TravelHistory.class, id);
    }

    public List<TravelHistory> getAllTravelHistories() {
        String url = externalApiBaseUrl + "/histories";
        TravelHistory[] histories = restTemplate.getForObject(url, TravelHistory[].class);
        return Arrays.asList(histories);
    }

    public void deleteTravelHistory(UUID id) {
        String url = externalApiBaseUrl + "/histories/{id}";
        restTemplate.delete(url, id);
    }
}
