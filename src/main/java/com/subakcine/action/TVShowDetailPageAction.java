package com.subakcine.action;

import com.subakcine.dao.TVShowDAO;
import com.subakcine.dao.LikeCountDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class TVShowDetailPageAction implements SubakcineAction {

    @Override
    public String pro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 요청 파라미터에서 TV 쇼 ID와 액션을 가져옴
        String tvShowId = request.getParameter("id");
        String action = request.getParameter("action");

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

        // 요청에 액션이 포함된 경우 추가 작업을 수행합니다.
        if (action != null) {
            // 세션에서 사용자 ID와 이메일을 가져옵니다.
            String usersID = (String) request.getSession().getAttribute("usersID");
            String email = (String) request.getSession().getAttribute("email");
            String itemType = "tv"; // 아이템 타입을 "tv"로 설정합니다.

            // 디버깅을 위해 세션에서 사용자 아이디와 이메일을 출력합니다.
            System.out.println("TVShowDetailPageAction에서 사용자 아이디 확인: " + usersID);
            System.out.println("TVShowDetailPageAction에서 사용자 이메일 확인: " + email);
            System.out.println("TVShowDetailPageAction에서 tvID 확인: " + tvShowId);

            // 세션에서 사용자 ID를 가져오지 못했을 경우 로그인 페이지로 리디렉션
            if (usersID == null || email == null) {
                request.setAttribute("message", "로그인이 필요합니다.");
                return "views/signIn.jsp";
            }

            // 액션에 따라 적절한 메서드를 호출합니다.
            if (action.equals("likeTVShow")) {
                boolean success = tvShowDao.likeTVShow(tvShowId, usersID, itemType);
                request.setAttribute("message", success ? "Liked the TV show successfully!" : "Failed to like the TV show.");
            }
        }

        // TV 쇼 상세 페이지로 이동합니다.
        return "/views/tvShowDetailPage.jsp";
    }
}
