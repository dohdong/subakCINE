package com.subakcine.action;

import com.subakcine.dao.LikeCountDAO;
import com.subakcine.dao.LikeItemDAO;
import com.subakcine.dao.TVShowDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TVShowDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 TV 쇼 ID와 액션을 가져옵니다.
        String tvShowId = request.getParameter("id");
        String action = request.getParameter("action");
        boolean isLiked = false;

        try {
            // TVShowDAO 인스턴스를 생성합니다.
            TVShowDAO tvShowDao = new TVShowDAO();
            // TV 쇼 세부 정보를 가져옵니다.
            Map<String, Object> tvShowDetails = tvShowDao.getTVShowDetails(tvShowId);
            // TV 쇼 세부 정보를 요청 객체에 설정합니다.
            request.setAttribute("tvShow", tvShowDetails);

            // 좋아요 수 가져오기
            LikeCountDAO likeCountDAO = new LikeCountDAO();
            int likeCount = likeCountDAO.getLikeCount(tvShowId, "tv");
            request.setAttribute("likeCount", likeCount);

            String usersID = (String) request.getSession().getAttribute("usersID");

            if (usersID != null) {
                LikeItemDAO likeItemDAO = new LikeItemDAO();
                isLiked = likeItemDAO.isLiked(tvShowId, usersID, "tv");
                request.setAttribute("isLiked", isLiked);

                if (action != null && action.equals("likeTVShow")) {
                    boolean success;
                    if (isLiked) {
                        success = likeItemDAO.unlikeItem(tvShowId, usersID, "tv");
                        isLiked = !success;
                        request.setAttribute("message", success ? "Removed like from the TV show." : "Failed to remove like.");
                    } else {
                        success = likeItemDAO.likeItem(tvShowId, usersID, "tv");
                        isLiked = success;
                        request.setAttribute("message", success ? "Liked the TV show successfully!" : "Failed to like the TV show.");
                    }

                    // 좋아요 수 다시 가져오기
                    likeCount = likeCountDAO.getLikeCount(tvShowId, "tv");
                    request.setAttribute("likeCount", likeCount);
                    request.setAttribute("isLiked", isLiked);
                }

            } else {
                if (action != null && action.equals("likeTVShow")) {
                    request.setAttribute("message", "로그인이 필요합니다.");
                    return "views/signIn.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception details
            throw e; // Rethrow for non-AJAX requests
        }

        // TV 쇼 상세 페이지로 이동합니다.
        return "/views/tvShowDetailPage.jsp";
    }
}
