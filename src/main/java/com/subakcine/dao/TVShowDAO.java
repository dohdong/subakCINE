package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TVShowDAO {
    private TmdbApiClient apiClient = new TmdbApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> getPopularTVShows() throws IOException {
        String jsonResponse = apiClient.getPopularTVShows();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public List<Map<String, Object>> getAiringTodayTVShows() throws IOException {
        String jsonResponse = apiClient.getAiringTodayTVShows();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public List<Map<String, Object>> getOnTheAirTVShows() throws IOException {
        String jsonResponse = apiClient.getOnTheAirTVShows();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public List<Map<String, Object>> getTopRatedTVShows() throws IOException {
        String jsonResponse = apiClient.getTopRatedTVShows();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }


    public Map<String, Object> getTVShowDetails(String tvShowId) throws IOException {
        String jsonResponse = apiClient.getTVShowDetails(tvShowId);
        return objectMapper.readValue(jsonResponse, Map.class);
    }
}
