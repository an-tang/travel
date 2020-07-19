<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.helper.SessionHelpers" %>
<%@ page import="com.travel.bean.TourInfoBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    HttpSession currentSession = request.getSession(false);
    boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
    String authenticatedUser = (String) currentSession.getAttribute("authenticatedUser");
    String authenticatedName = (String) currentSession.getAttribute("authenticatedName");
    String loginReplacementURL = (String) request.getAttribute("loginReplacementURL");
    TourInfoBean tourInfo = (TourInfoBean) request.getAttribute("tourInfo");
%>
<div class="card comment-form-card">
    <h5 class="card-header">Bình luận</h5>
    <div class="card-body">
        <c:choose>
            <c:when test="<%=isAuthenticated%>">
                <form
                    action="/comment"
                    class="comment"
                    method="post"
                    data-request-error="Request to server unexpectedly failed. Please try again later.">

                    <input type="hidden" name="username" value="<%=authenticatedUser%>">
                    <input type="hidden" name="tour_info" value="<%=tourInfo.getId()%>">

                    <div class="form-group required">
                        <label class="form-control-label" for="commenterName">Bình luận với tư cách:</label>
                        <input
                            type="text"
                            id="commenterName"
                            class="form-control"
                            name="commenter"
                            placeholder="Người bình luận"
                            value="<%=authenticatedName%>"
                            required
                            data-missing-error="Vui lòng nhập tên">
                        <div class="invalid-msg"></div>
                    </div>

                    <div class="form-group required">
                        <textarea
                            id="commentContent"
                            class="form-control"
                            name="comment_content"
                            placeholder="Nội dung bình luận"
                            rows="5"
                            required maxlength="600"
                            data-missing-error="Vui lòng nhập nội dung bình luận"
                            data-range-error="Bình luận không dài quá 600 tự"></textarea>
                        <div class="invalid-msg"></div>
                    </div>

                    <div class="form-group mb-0">
                        <button type="submit" class="btn btn-danger btn-accent">Đăng bình luận</button>
                    </div>
                </form>
            </c:when>
            <c:otherwise>
                <h5 class="mb-0">
                    Hãy <a id="commentLoginLink" href="/login"
                        <c:if test="<%=loginReplacementURL != null%>">
                            data-replacement-href="<%=loginReplacementURL%>"
                        </c:if>>đăng nhập</a> để đăng tải bình luận của riêng bạn.</h5>
            </c:otherwise>
        </c:choose>
    </div>
</div>
