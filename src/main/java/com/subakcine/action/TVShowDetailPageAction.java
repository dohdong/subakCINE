package com.subakcine.action;

import com.subakcine.dao.TVShowDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * TVShowDetailPageAction 클래스는 TV 쇼 상세 페이지를 처리합니다.
 * TV 쇼 세부 정보를 가져오고, 컬렉션 추가 및 좋아요 기능을 제공합니다.
 */
public class TVShowDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 TV 쇼 ID와 액션을 가져옵니다.
        String tvShowId = request.getParameter("id");
        String action = request.getParameter("action");

        // TVShowDAO 인스턴스를 생성합니다.
        TVShowDAO tvShowDao = new TVShowDAO();

        // TV 쇼 세부 정보를 가져옵니다.
        Map<String, Object> tvShowDetails = tvShowDao.getTVShowDetails(tvShowId);

        // TV 쇼 세부 정보를 요청 객체에 설정합니다.
        request.setAttribute("tvShow", tvShowDetails);

        // 요청에 액션이 포함된 경우 추가 작업을 수행합니다.
        if (action != null) {
            String userId = (String) request.getSession().getAttribute("userId"); // 세션에서 사용자 ID를 가져옵니다.
            String itemType = "tvshow"; // 아이템 타입을 "tvshow"로 설정합니다.

            // 액션에 따라 적절한 메서드를 호출합니다.
            if (action.equals("addToCollection")) {
                boolean success = tvShowDao.addToCollection(request.getParameter("collectionId"), tvShowId, itemType);
                request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
            } else if (action.equals("likeTVShow")) {
                boolean success = tvShowDao.likeTVShow(tvShowId, userId, itemType);
                request.setAttribute("message", success ? "Liked the TV show successfully!" : "Failed to like the TV show.");
            }
        }

        // TV 쇼 상세 페이지로 이동합니다.
        return "/views/tvShowDetailPage.jsp";
    }
}
