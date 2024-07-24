package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.ConnectionProvider;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

    public boolean addToCollection(int tvShowId, String tvShowTitle, String posterPath) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO tvShow (TVShow_no, TVShow_id, TVShow_title, poster_path) VALUES (seq_tvShow.nextval, ?, ?, ?)";
        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tvShowId);
            pstmt.setString(2, tvShowTitle);
            pstmt.setString(3, posterPath);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionProvider.close(pstmt, conn);
        }
    }

    public boolean likeTVShow(int tvShowId, String tvShowTitle, String posterPath) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO tvLIKEs (like_no, TVShow_id, TVShow_title, poster_path) VALUES (seq_tvlike.nextval, ?, ?, ?)";
        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, tvShowId);
            pstmt.setString(2, tvShowTitle);
            pstmt.setString(3, posterPath);
            int result = pstmt.executeUpdate();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionProvider.close(pstmt, conn);
        }
    }
}
