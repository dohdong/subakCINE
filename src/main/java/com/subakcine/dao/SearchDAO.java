package com.subakcine.dao;

import com.subakcine.db.TmdbApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SearchDAO {
    // TMDb API 클라이언트 인스턴스 생성
    private TmdbApiClient apiClient = new TmdbApiClient();

    // JSON 데이터를 Java 객체로 변환하기 위한 ObjectMapper 인스턴스 생성
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * TMDb API를 통해 영화, TV 쇼, 인물을 검색합니다.
     *
     * @param query 검색어
     * @return 검색 결과 목록을 포함한 Map 객체의 리스트
     * @throws IOException 입출력 오류가 발생한 경우
     */
    public List<Map<String, Object>> search(String query) throws IOException {
        // API 클라이언트를 사용하여 검색어에 대한 JSON 응답을 가져옴
        String jsonResponse = apiClient.search(query);

        // JSON 응답을 Map 객체로 변환
        Map<String, Object> result = objectMapper.readValue(jsonResponse, Map.class);

        // Map 객체에서 "results" 키에 해당하는 값을 List<Map<String, Object>>로 캐스팅하여 반환
        return (List<Map<String, Object>>) result.get("results");
    }
}
