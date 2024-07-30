package com.subakcine.action;

import com.subakcine.dao.LikeCountDAO;
import com.subakcine.dao.LikeItemDAO;
import com.subakcine.dao.PersonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * PersonDetailPageAction 클래스는 인물 상세 페이지를 처리합니다.
 * 인물 세부 정보를 가져오고, 컬렉션 추가 및 좋아요 기능을 제공합니다.
 */
public class PersonDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 인물 ID와 액션을 가져옵니다.
        String personId = request.getParameter("id");
        String action = request.getParameter("action");
        boolean isLiked = false;

        try {
            // PersonDAO 인스턴스를 생성합니다.
            PersonDAO personDao = new PersonDAO();
            // 인물 세부 정보를 가져옵니다.
            Map<String, Object> personDetails = personDao.getPopularPersonDetails(personId);
            // 인물 세부 정보를 요청 객체에 설정합니다.
            request.setAttribute("personDetails", personDetails);

            // 좋아요 수 가져오기
            LikeCountDAO likeCountDAO = new LikeCountDAO();
            int likeCount = likeCountDAO.getLikeCount(personId, "person");
            request.setAttribute("likeCount", likeCount);

            String usersID = (String) request.getSession().getAttribute("usersID");

            if (usersID != null) {
                LikeItemDAO likeItemDAO = new LikeItemDAO();
                isLiked = likeItemDAO.isLiked(personId, usersID, "person");
                request.setAttribute("isLiked", isLiked);

                if (action != null && action.equals("likePerson")) {
                    boolean success;
                    if (isLiked) {
                        success = likeItemDAO.unlikeItem(personId, usersID, "person");
                        isLiked = !success;
                        request.setAttribute("message", success ? "Removed like from the person." : "Failed to remove like.");
                    } else {
                        success = likeItemDAO.likeItem(personId, usersID, "person");
                        isLiked = success;
                        request.setAttribute("message", success ? "Liked the person successfully!" : "Failed to like the person.");
                    }

                    // 좋아요 수 다시 가져오기
                    likeCount = likeCountDAO.getLikeCount(personId, "person");
                    request.setAttribute("likeCount", likeCount);
                    request.setAttribute("isLiked", isLiked);
                }

            } else {
                if (action != null && action.equals("likePerson")) {
                    request.setAttribute("message", "로그인이 필요합니다.");
                    return "views/signIn.jsp";
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log exception details
            throw e; // Rethrow for non-AJAX requests
        }

        // 인물 상세 페이지로 이동합니다.
        return "/views/personDetailPage.jsp";
    }
}
