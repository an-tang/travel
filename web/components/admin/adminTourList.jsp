<%@ page import="com.travel.viewmodel.TourDetail" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<TourDetail> listTours = (ArrayList<TourDetail>) request.getAttribute("listTours");
%>
<c:choose>
    <c:when test="${listTours.size() > 0}">
        <c:forEach items="${listTours}" var="tour" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${tour.getName()}</td>
                <td>${tour.getTitle()}</td>
                <td>${tour.getPrice()}</td>
                <td>${tour.getProvince()}</td>
                <td>${tour.getStatus() == 1 ? "Active" : "Inactive"}</td>
                <td>
                    <form class="d-flex" action="/admin/Tours" method="post">
                        <button class="btn waves-effect waves-green btn-instagram hidden-md-down mr-2"
                                type="submit"
                                name="id_tour_update"
                                value="${tour.getId()}">
                            Update
                        </button>
                        <button class="btn waves-effect waves-green btn-facebook hidden-md-down mr-2"
                                type="submit"
                                name="id_tour_active"
                                value="${tour.getId()}">
                            Activate
                        </button>
                        <button class="btn waves-effect waves-red btn-red hidden-md-down"
                                type="submit"
                                name="id_tour_deactive"
                                value="${tour.getId()}">
                            Deactivate
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="6">
                <div class="text-center">
                    <h5 class="mb-0 mt-3" style="color: #67757c;">Không có kết quả tìm kiếm phù hợp.</h5>
                </div>
            </td>
        </tr>
    </c:otherwise>
</c:choose>
