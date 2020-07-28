<%@ page import="com.travel.bean.UserBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<UserBean> listUsers = (ArrayList<UserBean>) request.getAttribute("listUsers");
%>
<c:choose>
    <c:when test="${listUsers.size() > 0}">
        <c:forEach items="${listUsers}" var="user" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.getUserName()}</td>
                <td>${user.getName()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getStatus() == 1 ? "Active" : "Inactive"}</td>
                <td>
                    <form class="d-flex" action="/admin/Users" method="post">
                        <button class="btn waves-effect waves-green btn-google hidden-md-down mr-2"
                                type="submit"
                                name="id_user_history"
                                value="${user.getUserName()}">
                            History
                        </button>
                        <button class="btn waves-effect waves-green btn-facebook hidden-md-down mr-2"
                                type="submit"
                                name="id_user_active"
                                value="${user.getId()}">
                            Activate
                        </button>
                        <button class="btn waves-effect waves-red btn-red hidden-md-down"
                                type="submit"
                                name="id_user_deactive"
                                value="${user.getId()}">
                            Deactivate
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="5">
                <div class="text-center">
                    <h5 class="mb-0 mt-3" style="color: #67757c;">Không có kết quả tìm kiếm phù hợp.</h5>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>
