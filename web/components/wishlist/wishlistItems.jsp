<%@ page import="com.travel.bean.TourBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<TourBean> tours = (ArrayList<TourBean>) request.getAttribute("tours");
%>

<c:choose>
    <c:when test="${tours.size() > 0}">
        <c:forEach items="${tours}" var="tour">
            <c:url var="tourUrl" value="/tour">
                <c:param name="id" value="${tour.getId()}"/>
            </c:url>
            <div class="item col-lg-6">
                <div class="item_image">
                    <img src="${tour.getImage()}" alt="">
                </div>
                <div class="item_content">
                    <div class="item_title">${tour.getName()}</div>
                    <div class="item_price">${CustomStringFormatter.getFormattedPrice(tour.getPrice(), "đ")}</div>
                    <div class="rating rating_5 d-block" data-rating="5">
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                        <i class="fa fa-star"></i>
                    </div>
                    <div class="item_more_link">
                        <a href="${tourUrl}">Xem thêm</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <div class="col-12">
            <h5 class="text-center">Chưa có tour yêu thích.</h5>
        </div>
    </c:otherwise>
</c:choose>