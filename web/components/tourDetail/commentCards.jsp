<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.travel.bean.CommentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<CommentBean> comments = (ArrayList<CommentBean>) request.getAttribute("comments");
    String showMoreURL = (String) request.getAttribute("showMoreURL");
    boolean hasShowMore = !showMoreURL.equals("");
%>
<c:forEach items="${comments}" var="comment">
    <div class="card comment-card">
        <h5 class="card-header">${comment.getUserName()}</h5>
        <div class="card-body">
            <p>${comment.getContent()}</p>
        </div>
    </div>
</c:forEach>

<c:if test="<%=hasShowMore%>">
    <div class="show-more">
        <button class="btn btn-outline-danger col-12 col-md-4" data-url="<%=showMoreURL%>">
            Hiển thị thêm bình luận
        </button>
    </div>
</c:if>
