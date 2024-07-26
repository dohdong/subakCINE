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

    // TmdbApiClient 에서 리턴하는 값들은 모두 JSON 형식의 문자열을 반환함
    // OkHttp 라이브러리를 사용하여 TMDB API에 HTTP GET 요청을 보내고, 응답을 JSON 문자열로 받아서 그대로 반환함

    /**
     * properties 파일에서 API 키를 초기화합니다.
     *
     * @param context properties 파일의 실제 경로를 얻기 위한 서블릿 컨텍스트
     */
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

    /**
     * 검색어를 기반으로 영화, TV 쇼, 인물을 검색합니다.
     *
     * @param query 검색어
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String search(String query) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/search/multi?api_key=" + API_KEY + "&query=" + query + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // 영화 관련 메서드

    /**
     * 인기 있는 영화 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getPopularMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/popular?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 특정 영화의 세부 정보를 가져옵니다.
     *
     * @param movieId 영화 ID
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getMovieDetails(String movieId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/" + movieId + "?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 현재 상영 중인 영화 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getNowPlayingMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/now_playing?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 상영 예정인 영화 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getUpcomingMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/upcoming?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 최고 평점을 받은 영화 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getTopRatedMovies() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/movie/top_rated?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // TV 쇼 관련 메서드

    /**
     * 인기 있는 TV 쇼 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getPopularTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/popular?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 특정 TV 쇼의 세부 정보를 가져옵니다.
     *
     * @param tvShowId TV 쇼 ID
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getTVShowDetails(String tvShowId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/" + tvShowId + "?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 오늘 방송되는 TV 쇼 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getAiringTodayTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/airing_today?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 현재 방송 중인 TV 쇼 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getOnTheAirTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/on_the_air?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 최고 평점을 받은 TV 쇼 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getTopRatedTVShows() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/tv/top_rated?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    // 인물 관련 메서드

    /**
     * 인기 있는 인물 목록을 가져옵니다.
     *
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getPopularPerson() throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/person/popular?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    /**
     * 특정 인물의 세부 정보를 가져옵니다.
     *
     * @param personId 인물 ID
     * @return API의 JSON 응답을 문자열로 반환
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public String getPopularPersonDetails(String personId) throws IOException {
        Request request = new Request.Builder()
                .url(API_BASE_URL + "/person/" + personId + "?api_key=" + API_KEY + "&language=ko-KR")
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
