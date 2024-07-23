package com.subakcine.db;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class TmdbApiClient {
    private static final String API_BASE_URL = "https://api.themoviedb.org/3";
    private static String API_KEY;



    public static void init(ServletContext context) {
        Properties prop = new Properties();
        try {
            String path = context.getRealPath("/WEB-INF/properties/Config.properties");
            try (InputStream input = Files.newInputStream(Paths.get(path))) {
                if (input == null) {
                    throw new IOException("Config.properties 파일을 찾을 수 없습니다");
                }
                prop.load(input);
                API_KEY = prop.getProperty("tmdb.api.key");
            }
        } catch (IOException ex) {
            throw new RuntimeException("Config.properties 로드 중 오류 발생", ex);
        }
    }

    private final OkHttpClient client = new OkHttpClient();

    
    // 검색
    public String search(String query) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/search/multi?api_key=" + API_KEY + "&query=" + query)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }


    // 영화

    public String getPopularMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/popular?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getMovieDetails(String movieId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/" + movieId + "?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getNowPlayingMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/now_playing?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getUpcomingMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/upcoming?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getTopRatedMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/top_rated?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }




    // TVShow

    public String getPopularTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/popular?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getTVShowDetails(String tvShowId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/" + tvShowId + "?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getAiringTodayTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/airing_today?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getOnTheAirTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/on_the_air?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public String getTopRatedTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/top_rated?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }




    // 인물

    public String getPopularPeople() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/person/popular?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
    public String getPopularPeopleDetails(String personId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/person/" + personId + "?api_key=" + API_KEY)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }




}
