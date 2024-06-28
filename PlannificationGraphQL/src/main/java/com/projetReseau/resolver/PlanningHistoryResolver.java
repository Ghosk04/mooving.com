package com.projetReseau.resolver;

import com.projetReseau.model.planning.Planning;
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

public class PlanningHistoryResolver implements GraphQLQueryResolver, GraphQLMutationResolver {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public List<Planning> getAllPlanningHistories() {
        String url = externalApiBaseUrl + "/planninghistory";
        Planning[] plannings = restTemplate.getForObject(url, Planning[].class);
        return Arrays.asList(plannings);
    }

    public Planning getPlanningHistoryById(UUID id) {
        String url = externalApiBaseUrl + "/planninghistory/{id}";
        return restTemplate.getForObject(url, Planning.class, id);
    }

}
