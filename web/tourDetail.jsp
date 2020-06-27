<%@ page import="com.travel.bean.ImageBean" %>
<%@ page import="com.travel.bean.TourInfoBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TourInfoBean tourInfo = (TourInfoBean) request.getAttribute("tourInfo");
    ArrayList<ImageBean> tourImages = tourInfo.getImages();
    String a = "/(?:\\[rn])+/g";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />

    <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/slick/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="assets/css/tourDetail.css">
    <title id="title-header">Thông tin tour - <%=tourInfo.getTitle()%></title>
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
                                <p class="tour-price mb-0">Giá: <span><%=String.format("%,d", tourInfo.getPrice())%></span></p>
                            </div>
                            <div class="tour-actions">
                                <a href="#" class="btn btn-danger btn-rounded mr-2" name="bookThisTour">Đặt tour</a>
                                <button class="btn btn-secondary">Thêm vào yêu thích</button>
                            </div>
                        </div>
                        <hr>

                        <!-- Post Content -->
                        <p class="tour-detail"><%=tourInfo.getDetail()%></p>
                        <hr>

                        <!-- Comments Form -->
                        <div class="card my-4">
                            <h5 class="card-header">Leave a Comment:</h5>
                            <div class="card-body">
                                    <div class="form-group">
                                        <input class="form-control" id="commentName" placeholder="Name">
                                    </div>
                                    <div class="form-group">
                                        <textarea class="form-control" rows="3" id="commentContent" placeholder="Content"></textarea>
                                    </div>
                                    <button onclick="submitComment()" class="btn btn-primary" type="button">Submit</button>
                            </div>
                        </div>

                        <!-- Single Comment -->
                        <div id="areaComment"></div>
                    </div>

                    <!-- Sidebar Widgets Column -->
                    <div class="external-tour-links col-md-4">
                        <div class="card mb-4">
                            <h5 class="card-header">Một số website tour du lịch</h5>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <ul class="list-unstyled mb-0">
                                            <li>
                                                <a href="https://www.saigontourist.net/tour-trong-nuoc">SaiGonTourist</a>
                                            </li>
                                            <li>
                                                <a href="https://dulichviet.com.vn/du-lich-trong-nuoc">Du Lịch Việt</a>
                                            </li>
                                            <li>
                                                <a href="https://www.vietravel.com/">Việt Travel</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-lg-6">
                                        <ul class="list-unstyled mb-0">
                                            <li>
                                                <a href="https://www.ivivu.com/du-lich">Ivivu</a>
                                            </li>
                                            <li>
                                                <a href="https://datviettour.com.vn/du-lich-trong-nuoc">Đất Việt Tour</a>
                                            </li>
                                            <li>
                                                <a href="https://travel.com.vn/">Travel</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Side Widget -->
                        <div class="card d-none">
                            <h5 class="card-header">Side Widget</h5>
                            <div class="card-body">
                                You can put anything you want inside of these side widgets. They are easy to use, and
                                feature the new
                                Bootstrap 4 card containers!
                            </div>
                        </div>

                    </div>
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
    <script src="assets/vendor/easing/easing.js"></script>
    <script src="assets/vendor/slick/slick.min.js"></script>
    <script src="assets/js/tourDetail.js"></script>
</body>

</html>
