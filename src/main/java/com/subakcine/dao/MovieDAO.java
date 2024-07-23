package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MovieDAO {
    private TmdbApiClient apiClient = new TmdbApiClient();
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, Object>> getPopularMovies() throws IOException {
        String jsonResponse = apiClient.getPopularMovies();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public Map<String, Object> getMovieDetails(String movieId) throws IOException {
        String jsonResponse = apiClient.getMovieDetails(movieId);
        return objectMapper.readValue(jsonResponse, Map.class);
    }

    public List<Map<String, Object>> getNowPlayingMovies() throws IOException {
        String jsonResponse = apiClient.getNowPlayingMovies();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public List<Map<String, Object>> getUpcomingMovies() throws IOException {
        String jsonResponse = apiClient.getUpcomingMovies();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }

    public List<Map<String, Object>> getTopRatedMovies() throws IOException {
        String jsonResponse = apiClient.getTopRatedMovies();
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);
        return (List<Map<String, Object>>) result.get("results");
    }
}