package com.subakcine.dao;

import com.subakcine.db.TmdbApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SearchDAO {
    private TmdbApiClient apiClient = new TmdbApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> search(String query) throws IOException {
        String jsonResponse = apiClient.search(query);
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }
}
