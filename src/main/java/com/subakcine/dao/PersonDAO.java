package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class PersonDAO {
    private TmdbApiClient apiClient = new TmdbApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> getPopularPeople() throws IOException {
        String jsonResponse = apiClient.getPopularPeople();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public Map<String, Object> getPopularPeopleDetails(String peopleId) throws IOException {
        String jsonResponse = apiClient.getPopularPeopleDetails(peopleId);
        return objectMapper.readValue(jsonResponse, Map.class);
    }
}
