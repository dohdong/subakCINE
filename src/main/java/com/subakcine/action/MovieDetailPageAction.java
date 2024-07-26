package com.subakcine.action;

import com.subakcine.dao.MovieDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * MovieDetailPageAction 클래스는 영화 상세 페이지를 처리합니다.
 * 영화 세부 정보를 가져오고, 컬렉션 추가 및 좋아요 기능을 제공합니다.
 */
public class MovieDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 영화 ID와 액션을 가져옵니다.
        String movieId = request.getParameter("id");
        String action = request.getParameter("action");

        // MovieDAO 인스턴스를 생성합니다.
        MovieDAO movieDao = new MovieDAO();

        // 영화 세부 정보를 가져옵니다.
        Map<String, Object> movieDetails = movieDao.getMovieDetails(movieId);

        // 디버깅을 위해 영화 세부 정보를 출력합니다.
        System.out.println("movieDetails: " + movieDetails);

        // 영화 세부 정보를 요청 객체에 설정합니다.
        request.setAttribute("movie", movieDetails);

        // 요청에 액션이 포함된 경우 추가 작업을 수행합니다.
        if (action != null) {
            String userId = (String) request.getSession().getAttribute("userId"); // 세션에서 사용자 ID를 가져옵니다.
            String itemType = "movie"; // 아이템 타입을 "movie"로 설정합니다.

            // 액션에 따라 적절한 메서드를 호출합니다.
            if (action.equals("addToCollection")) {
                boolean success = movieDao.addToCollection(request.getParameter("collectionId"), movieId, itemType);
                request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
            } else if (action.equals("likeMovie")) {
                boolean success = movieDao.likeMovie(movieId, userId, itemType);
                request.setAttribute("message", success ? "Liked the movie successfully!" : "Failed to like the movie.");
            }
        }

        // 영화 상세 페이지로 이동합니다.
        return "/views/movieDetailPage.jsp";
    }
}
