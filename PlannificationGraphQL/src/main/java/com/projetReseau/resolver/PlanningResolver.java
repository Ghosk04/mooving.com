package com.projetReseau.resolver;

import com.projetReseau.model.planning.Planning;
import com.projetReseau.model.travel.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
public class PlanningResolver {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    public Planning createPlanning(String planning) {
        String url=externalApiBaseUrl+"/planning";
        return restTemplate.postForObject(url, planning, Planning.class);
    }
    public Planning getPlanningById(UUID id) {
        String url=externalApiBaseUrl+"/planning/{id}";
        return restTemplate.getForObject(url, Planning.class, id);
    }
    public List<Travel> getAllTravels(UUID plannindId) {
        String url=externalApiBaseUrl+"/planning/{id}/travels";
        Travel[] travels=restTemplate.getForObject(url, Travel[].class, plannindId);
        return Arrays.asList(travels);
    }
    public List<Planning> getAllPlannings() {
        String url=externalApiBaseUrl+"/planning";
        Planning[] plannings=restTemplate.getForObject(url, Planning[].class);
        return Arrays.asList(plannings);
    }
    public HashMap<String, List<Travel>> publishPlanning(UUID id){
        String url=externalApiBaseUrl+"/planning/publish/{id}";
        return restTemplate.getForObject(url,null, HashMap.class, id);
    }
    public Planning updatePlanning(UUID id, Planning planning) {
        String url=externalApiBaseUrl+"/planning/{id}";
        restTemplate.put(url, planning,id);
        return planning;
    }
    public void addTravelToPlanning(UUID planningId, UUID travelID) {
        String url= externalApiBaseUrl+"/planning/{planningId}/travels?travelId={travelId}";
        restTemplate.postForObject(url,null,void.class, travelID,planningId);
    }
    public void removeTravelFromPlanning(UUID planningId, UUID travelId, LocalDateTime newStartDate) {
        String url=externalApiBaseUrl+ "/planning/{planningId}/travels?travelId={travelId}&newStartDate={newStartDate}";
        restTemplate.delete(url,planningId,travelId,newStartDate);
    }
}
