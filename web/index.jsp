<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.travel.bean.HomeProvinceBean" %>
<%@ page import="com.travel.bean.TourBean" %>
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
    <title>UIT Travel</title>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="Destino project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="WebContent/styles/bootstrap4/bootstrap.min.css">
    <link href="WebContent/plugins/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="WebContent/plugins/OwlCarousel2-2.2.1/owl.carousel.css">
    <link rel="stylesheet" type="text/css" href="WebContent/plugins/OwlCarousel2-2.2.1/owl.theme.default.css">
    <link rel="stylesheet" type="text/css" href="WebContent/plugins/OwlCarousel2-2.2.1/animate.css">
    <link href="WebContent/plugins/magnific-popup/magnific-popup.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="WebContent/styles/main_styles.css">
    <link rel="stylesheet" type="text/css" href="WebContent/styles/my_main_style.css">
    <link rel="stylesheet" type="text/css" href="WebContent/styles/responsive.css">
</head>

<body>

<div class="super_container">

    <!-- Header -->

    <header class="header">
        <div class="container">
            <div class="row">
                <div class="col">
                    <div class="header_container d-flex flex-row align-items-center justify-content-start">

                        <!-- Logo -->
                        <div class="logo_container">
                            <div class="logo">
                                <div>uit</div>
                                <div>travel</div>
                                <div class="logo_image"><img src="WebContent/images/logo.png" alt=""></div>
                            </div>
                        </div>

                        <!-- Main Navigation -->
                        <nav class="main_nav ml-auto">
                            <ul class="main_nav_list">
                                <li class="main_nav_item active"><a href="#">Trang chủ</a></li>
                                <li class="main_nav_item"><a href="../../../travel/travel-info/client/about.html">Về
                                    Chúng Tôi</a></li>
                                <li class="main_nav_item"><a href="../../../travel/travel-info/client/contact.html">Liên
                                    hệ</a></li>

                            </ul>
                        </nav>

                        <!-- Search -->
                        <div class="search">
                            <form action="#" class="search_form">
                                <input type="search" name="search_input" class="search_input ctrl_class"
                                       required="required" placeholder="Từ khóa">
                                <div class="search_button ml-auto ctrl_class"><img src="WebContent/images/search.png"
                                                                                   alt=""></div>
                            </form>
                        </div>

                        <!-- Hamburger -->
                        <div class="hamburger ml-auto"><i class="fa fa-bars" aria-hidden="true"></i></div>

                    </div>
                </div>
            </div>
        </div>
    </header>

    <!-- Menu -->

    <div class="menu_container menu_mm">

        <!-- Menu Close Button -->
        <div class="menu_close_container">
            <div class="menu_close"></div>
        </div>

        <!-- Menu Items -->
        <div class="menu_inner menu_mm">
            <div class="menu menu_mm">
                <div class="menu_search_form_container">
                    <form action="#" id="menu_search_form">
                        <input type="search" class="menu_search_input menu_mm">
                        <button id="menu_search_submit" class="menu_search_submit" type="submit"><img
                                src="WebContent/images/search_2.png" alt=""></button>
                    </form>
                </div>
                <ul class="menu_list menu_mm">
                    <li class="menu_item menu_mm"><a href="#">Trang chủ</a></li>
                    <li class="menu_item menu_mm"><a href="../../../travel/travel-info/client/about.html">Về Chúng
                        Tôi</a></li>
                    <li class="menu_item menu_mm"><a href="../../../travel/travel-info/client/contact.html">Liên hệ</a>
                    </li>

                </ul>

                <!-- Menu Social -->
                <div class="menu_social_container menu_mm">
                    <ul class="menu_social menu_mm">
                        <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-pinterest"
                                                                            aria-hidden="true"></i></a></li>
                        <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-instagram"
                                                                            aria-hidden="true"></i></a></li>
                        <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-facebook"
                                                                            aria-hidden="true"></i></a></li>
                        <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-twitter"
                                                                            aria-hidden="true"></i></a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>

    <!-- Home -->

    <div class="home">
        <div class="home_background" style="background-image:url(WebContent/images/home.jpg)"></div>
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
                                            <div class="top_item_price">0 đ</div>
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
                                            <div class="top_item_price">0 đ</div>
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
                                            <div class="top_item_price">0 đ</div>
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
                                        <div class="top_item_price">0 đ</div>
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
                        <div class="owl-item">
                            <div class="special_item">
                                <div class="special_item_background">
                                    <img src="${province.getUrl()}" alt="https://unsplash.com/@garciasaldana_">
                                </div>
                                <div class="special_item_content text-center">
                                    <div class="special_title">
                                        <a href="#">${province.getName()}</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>

                </div>
                <div class="special_slider_nav d-flex flex-column align-items-center justify-content-center">
                    <img src="WebContent/images/special_slider.png" alt="">
                </div>
            </div>
        </div>
    </div>

    <!-- Newsletter -->

    <div class="newsletter">
        <!-- Image by https://unsplash.com/@garciasaldana_ -->
        <div class="newsletter_background" style="background-image:url(WebContent/images/newsletter.jpg)"></div>
        <div class="container">
            <div class="row">
                <div class="col-lg-10 offset-lg-1">
                    <div class="newsletter_content">
                        <div class="newsletter_title text-center">Nhận thông tin mới nhất từ chúng tôi</div>
                        <div class="newsletter_form_container">
                            <form action="#" id="newsletter_form" class="newsletter_form">
                                <div class="d-flex flex-md-row flex-column align-content-center justify-content-between">
                                    <input type="email" id="newsletter_input" class="newsletter_input"
                                           placeholder="Địa chỉ e-mail của bạn">
                                    <button type="submit" id="newsletter_button" class="newsletter_button">Đăng ký
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->

    <footer class="footer">
        <div class="container">
            <div class="row">

                <!-- Footer Column -->
                <div class="col-lg-4 footer_col">
                    <div class="footer_about">
                        <!-- Logo -->
                        <div class="logo_container">
                            <div class="logo">
                                <div>uit</div>
                                <div>travel</div>
                                <div class="logo_image"><img src="WebContent/images/logo.png" alt=""></div>
                            </div>
                        </div>
                        <div class="footer_about_text">Trang thông tin du lịch trong nước.</div>
                        <div class="copyright">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                            Copyright &copy;<script>
                            document.write(new Date().getFullYear());

                        </script>
                            . All rights reserved.
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </div>
                    </div>
                </div>

                <!-- Footer Column -->
                <div class="col-lg-4 footer_col">
                    <div class="footer_latest">
                        <div class="footer_title">Tin mới nhất</div>
                        <div class="footer_latest_content">

                            <!-- Footer Latest Post -->
                            <div class="footer_latest_item">
                                <div class="footer_latest_image"><img src="WebContent/images/latest_1.jpg"
                                                                      alt="https://unsplash.com/@peecho"></div>
                                <div class="footer_latest_item_content">
                                    <div class="footer_latest_item_title"><a href="#">Một kỳ nghỉ hoàn hảo</a>
                                    </div>
                                    <div class="footer_latest_item_date">Mar 25, 2019</div>
                                </div>
                            </div>

                            <!-- Footer Latest Post -->
                            <div class="footer_latest_item">
                                <div class="footer_latest_image"><img src="WebContent/images/latest_2.jpg"
                                                                      alt="https://unsplash.com/@sanfrancisco"></div>
                                <div class="footer_latest_item_content">
                                    <div class="footer_latest_item_title"><a href="#">Mùa hè ở miền biển nhiệt đới</a>
                                    </div>
                                    <div class="footer_latest_item_date">Jan 09, 2018</div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <!-- Footer Column -->
                <div class="col-lg-4 footer_col">
                    <div class="tags footer_tags">
                        <div class="footer_title">Tags</div>
                        <ul class="tags_content d-flex flex-row flex-wrap align-items-start justify-content-start">
                            <li class="tag"><a href="#">travel</a></li>
                            <li class="tag"><a href="#">summer</a></li>
                            <li class="tag"><a href="#">cruise</a></li>
                            <li class="tag"><a href="#">beach</a></li>
                            <li class="tag"><a href="#">offer</a></li>
                            <li class="tag"><a href="#">vacation</a></li>
                            <li class="tag"><a href="#">trip</a></li>
                            <li class="tag"><a href="#">city break</a></li>
                            <li class="tag"><a href="#">adventure</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </footer>
</div>

<script src="WebContent/js/jquery-3.2.1.min.js"></script>
<script src="WebContent/styles/bootstrap4/popper.js"></script>
<script src="WebContent/styles/bootstrap4/bootstrap.min.js"></script>
<script src="WebContent/plugins/OwlCarousel2-2.2.1/owl.carousel.js"></script>
<script src="WebContent/plugins/easing/easing.js"></script>
<script src="WebContent/plugins/parallax-js-master/parallax.min.js"></script>
<script src="WebContent/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="WebContent/js/custom.js"></script>
<script src="WebContent/js/home_data.js"></script>
<script src="WebContent/js/tour_search.js"></script>
</body>

</html>
