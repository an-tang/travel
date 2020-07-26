<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.travel.bean.TourInfoBean" %>
<%@ page import="com.travel.helper.CustomStringFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    TourInfoBean tourInfo = (TourInfoBean) request.getAttribute("tourInfo");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="assets/css/tourDetail.css">
    <title id="title-header"><%=tourInfo.getTitle()%></title>
</head>

<body>
    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp" />

        <!-- Menu -->
        <jsp:include page="components/menu.jsp" />

        <section class="tour-banner">
            <div class="tour-banner-slider">
                <c:forEach items="${tourInfo.getImages()}" var="image">
                    <div class="tour-banner-slide">
                        <picture>
                            <source srcset="${image.getUrl()}" media="(max-width: 767px)">
                            <img src="${image.getUrl()}" alt="">
                        </picture>
                        <div class="banner-text container">
                            <p>${image.getDescription()}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- Page Content -->
        <section class="post-content">
            <div class="container">

                <div class="row">
                    <!-- Post Content Column -->
                    <div class="col-lg-8">

                        <!-- Title -->
                        <h2 class="tour-title"><%=tourInfo.getTitle()%></h2>

                        <!-- Price -->
                        <div class="d-flex align-items-center">
                            <div class="tour-price-wrapper flex-grow-1">
                                <p class="tour-price mb-0">Giá:<span><%=CustomStringFormatter.getFormattedPrice(tourInfo.getPrice(), "đ")%></span> / người</p>
                            </div>
                            <div class="tour-actions">
                                <button
                                        id="bookThisTour"
                                        class="btn btn-danger btn-accent mr-2"
                                        data-action="/tour"
                                        data-tour-id="<%=tourInfo.getTourID()%>"
                                        data-request-error="Request to server unexpectedly failed. Please try again later."
                                >Đặt tour</button>
                                <button
                                        id="addToWishlist"
                                        class="btn btn-secondary"
                                        data-action="/wishlist"
                                        data-tour-id="<%=tourInfo.getTourID()%>"
                                        data-request-error="Request to server unexpectedly failed. Please try again later."
                                >Thêm vào yêu thích</button>
                            </div>
                        </div>
                        <hr>

                        <!-- Post Content -->
                        <p class="tour-detail"><%=tourInfo.getDetail()%></p>
                        <hr>

                        <!-- Comment Form -->
                        <jsp:include page="components/tourDetail/commentForm.jsp" />

                        <!-- Comments - will be fetched and rendered using an AJAX request -->
                        <h4 class="mb-3">Tất cả bình luận</h4>
                        <div class="comments"></div>
                    </div>

                    <!-- Sidebar Widgets Column -->
                    <jsp:include page="components/tourDetail/sideWidgets.jsp" />
                </div>
            </div>
        </section>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp" />
    </div>

    <jsp:include page="components/globalScripts.jsp" />
    <script src="assets/vendor/greensock/TweenMax.min.js"></script>
    <script src="assets/vendor/greensock/TimelineMax.min.js"></script>
    <script src="assets/vendor/scrollmagic/ScrollMagic.min.js"></script>
    <script src="assets/vendor/greensock/animation.gsap.min.js"></script>
    <script src="assets/vendor/greensock/ScrollToPlugin.min.js"></script>
    <script src="assets/vendor/slick/slick.min.js"></script>
    <script src="assets/js/tourDetail.js"></script>
</body>

</html>
