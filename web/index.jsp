<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.travel.bean.HomeProvinceBean" %>
<%@ page import="com.travel.bean.TourBean" %>
<%@ page import="com.travel.helper.CustomStringFormatter" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    ArrayList<TourBean> northernTours = (ArrayList<TourBean>) request.getAttribute("northernTours");
    ArrayList<TourBean> centralTours = (ArrayList<TourBean>) request.getAttribute("centralTours");
    ArrayList<TourBean> southernTours = (ArrayList<TourBean>) request.getAttribute("southernTours");
    ArrayList<TourBean> popularTours = (ArrayList<TourBean>) request.getAttribute("popularTours");
    ArrayList<HomeProvinceBean> homepageProvinces = (ArrayList<HomeProvinceBean>) request.getAttribute("homepageProvinces");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp"/>
    <link rel="stylesheet" type="text/css" href="assets/vendor/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="assets/vendor/OwlCarousel2-2.2.1/animate.css">
    <link rel="stylesheet" type="text/css" href="assets/css/homePage.css">
    <title id="title-header">UIT Travel - Trang chủ</title>
</head>

<body>

    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp"/>

        <!-- Menu -->
        <jsp:include page="components/menu.jsp"/>

        <!-- Home -->
        <div class="home-banner">
            <div class="home_background" style="background-image:url(assets/images/home.jpg)"></div>
            <div class="home_content">
                <div class="home_content_inner">
                    <div class="home_text_large">discover</div>
                    <div class="home_text_small">Khám phá & trải nghiệm</div>
                </div>
            </div>
        </div>

        <!-- Destinations by Region -->
        <div class="body-section top">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="section_title text-center">
                            <h2>Điểm đến 3 miền</h2>
                        </div>
                    </div>
                </div>

                <div class="col">
                    <!-- Miền Bắc -->
                    <div class="row row-region">
                        <h4><em>Miền Bắc</em></h4>
                        <div class="row top_content">

                            <c:forEach items="${northernTours}" var="tour">
                                <c:url var="tourUrl" value="/tour">
                                    <c:param name="id" value="${tour.getId()}"/>
                                </c:url>

                                <div class="col-lg-4 mb-2 mb-lg-0">
                                    <div class="top_item">
                                        <a href="${tourUrl}">
                                            <div class="top_item_image">
                                                <img src="${tour.getImage()}" alt="">
                                            </div>
                                            <div class="top_item_content">
                                                <div class="top_item_title">${tour.getName()}</div>
                                                <div class="top_item_price">${CustomStringFormatter.getFormattedPrice(tour.getPrice(), "đ")}</div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>

                    <!-- Miền Trung -->
                    <div class="row row-region">
                        <h4><em>Miền Trung</em></h4>
                        <div class="row top_content">

                            <c:forEach items="${centralTours}" var="tour">
                                <c:url var="tourUrl" value="/tour">
                                    <c:param name="id" value="${tour.getId()}"/>
                                </c:url>

                                <div class="col-lg-4 mb-2 mb-lg-0">
                                    <div class="top_item">
                                        <a href="${tourUrl}">
                                            <div class="top_item_image">
                                                <img src="${tour.getImage()}" alt="">
                                            </div>
                                            <div class="top_item_content">
                                                <div class="top_item_title">${tour.getName()}</div>
                                                <div class="top_item_price">${CustomStringFormatter.getFormattedPrice(tour.getPrice(), "đ")}</div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>

                    <!-- Miền Nam -->
                    <div class="row row-region">
                        <h4><em>Miền Nam</em></h4>
                        <div class="row top_content">

                            <c:forEach items="${southernTours}" var="tour">
                                <c:url var="tourUrl" value="/tour">
                                    <c:param name="id" value="${tour.getId()}"/>
                                </c:url>

                                <div class="col-lg-4 mb-2 mb-lg-0">
                                    <div class="top_item">
                                        <a href="${tourUrl}">
                                            <div class="top_item_image">
                                                <img src="${tour.getImage()}" alt="">
                                            </div>
                                            <div class="top_item_content">
                                                <div class="top_item_title">${tour.getName()}</div>
                                                <div class="top_item_price">${CustomStringFormatter.getFormattedPrice(tour.getPrice(), "đ")}</div>
                                            </div>
                                        </a>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Popular Tours -->
        <div class="body-section popular">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="section_title text-center">
                            <h2>Các tour phổ biến</h2>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col">
                        <div class="popular_content d-flex flex-md-row flex-column flex-wrap align-items-md-center align-items-start justify-content-md-between justify-content-start">

                            <c:forEach items="${popularTours}" var="tour">
                                <c:url var="tourUrl" value="/tour">
                                    <c:param name="id" value="${tour.getId()}"/>
                                </c:url>

                                <div class="popular_item">
                                    <a href="${tourUrl}">
                                        <div class="popular_item_image">
                                            <img src="${tour.getImage()}" alt="">
                                        </div>
                                        <div class="popular_item_content">
                                            <div class="popular_item_title">${tour.getName()}</div>
                                            <div class="top_item_price">${CustomStringFormatter.getFormattedPrice(tour.getPrice(), "đ")}</div>
                                        </div>
                                    </a>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Provinces -->
        <div class="body-section special">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="section_title text-center">
                            <h2>Tìm tour theo tỉnh thành</h2>
                        </div>
                    </div>
                </div>
            </div>

            <div class="special_content">
                <div class="special_slider_container">
                    <div class="owl-carousel owl-theme special_slider">

                        <c:forEach items="${homepageProvinces}" var="province">
                            <c:url var="provinceUrl" value="/province">
                                <c:param name="prv_id" value="${province.getProvinceID()}"/>
                            </c:url>
                            <div class="owl-item">
                                <div class="special_item">
                                    <div class="special_item_background">
                                        <img src="${province.getUrl()}" alt="https://unsplash.com/@garciasaldana_">
                                    </div>
                                    <div class="special_item_content text-center">
                                        <div class="special_title">
                                            <a href="${provinceUrl}">${province.getName()}</a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="special_slider_nav d-flex flex-column align-items-center justify-content-center">
                        <img src="assets/images/special_slider.png" alt="">
                    </div>
                </div>
            </div>
        </div>

        <!-- Newsletter -->
        <jsp:include page="components/newsletter.jsp"/>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp"/>
    </div>

    <jsp:include page="components/globalScripts.jsp"/>
    <script src="assets/vendor/OwlCarousel2-2.2.1/owl.carousel.js"></script>
</body>

</html>
