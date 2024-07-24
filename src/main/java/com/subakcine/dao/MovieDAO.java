package com.subakcine.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.subakcine.db.ConnectionProvider;
import com.subakcine.db.TmdbApiClient;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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



    public boolean addToCollection(int movieId, String movieTitle, String posterPath) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO movie (movie_no, movie_id, movie_title, poster_path) VALUES (seq_movie.nextval, ?, ?, ?)";
        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            pstmt.setString(2, movieTitle);
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

    public boolean likeMovie(int movieId, String movieTitle, String posterPath) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO LIKES (like_no, movie_id, movie_title, poster_path) VALUES (seq_like.nextval, ?, ?, ?)";
        try {
            conn = ConnectionProvider.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, movieId);
            pstmt.setString(2, movieTitle);
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