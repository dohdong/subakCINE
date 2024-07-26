package com.subakcine.action;

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

        // PersonDAO 인스턴스를 생성합니다.
        PersonDAO personDao = new PersonDAO();

        // 인물 세부 정보를 가져옵니다.
        Map<String, Object> personDetails = personDao.getPopularPersonDetails(personId);

        // 인물 세부 정보를 요청 객체에 설정합니다.
        request.setAttribute("personDetails", personDetails);

        // 요청에 액션이 포함된 경우 추가 작업을 수행합니다.
        if (action != null) {
            String userId = (String) request.getSession().getAttribute("userId"); // 세션에서 사용자 ID를 가져옵니다.
            String itemType = "person"; // 아이템 타입을 "person"으로 설정합니다.

            // 액션에 따라 적절한 메서드를 호출합니다.
            if (action.equals("addToCollection")) {
                boolean success = personDao.addToCollection(request.getParameter("collectionId"), personId, itemType);
                request.setAttribute("message", success ? "Added to collection successfully!" : "Failed to add to collection.");
            } else if (action.equals("likePerson")) {
                boolean success = personDao.likePerson(personId, userId, itemType);
                request.setAttribute("message", success ? "Liked the person successfully!" : "Failed to like the person.");
            }
        }

        // 인물 상세 페이지로 이동합니다.
        return "/views/personDetailPage.jsp";
    }
}
