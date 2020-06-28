<%@ page import="com.travel.bean.TourBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String searchQuery = (String) request.getAttribute("q");
    ArrayList<TourBean> searchResult = (ArrayList<TourBean>) request.getAttribute("searchResult");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/search.css">
    <title>UIT Travel - Tìm kiếm</title>
</head>

<body>

    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp"/>

        <!-- Menu -->
        <jsp:include page="components/menu.jsp"/>

        <!-- Home -->
        <div class="search-banner">
            <div class="home_background parallax-window" data-parallax="scroll" data-image-src="assets/images/offers.jpg" data-speed="0.8"></div>
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="home_content">
                            <div class="home_content_inner">
                                <div class="home_title">Tìm kiếm</div>
                                <div class="home_title_small">Tìm kiếm tour du lịch trong mơ của bạn</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Offers -->
        <div class="offers container">
            <div class="row">
                <div class="col">
                    <div class="section_title text-center">
                        <h2 id="section_title-with-q">Kết quả tìm kiếm cho '<%=searchQuery%>'</h2>
                    </div>
                </div>
            </div>
            <div class="row filtering_row d-none">
                <div class="col">
                    <div class="sorting_group_1">
                        <ul class="item_sorting">
                            <li>
                                <span class="sorting_text">Filter by</span>
                                <i class="fa fa-angle-down"></i>
                                <ul>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "original-order" }'><span>Show All</span>
                                    </li>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "price" }'>
                                        <span>Price</span></li>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "name" }'>
                                        <span>Name</span></li>
                                </ul>
                            </li>
                            <li>
                                <span class="sorting_text">Stars</span>
                                <i class="fa fa-angle-down"></i>
                                <ul>
                                    <li class="item_filter_btn" data-filter="*"><span>Show All</span></li>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "stars" }'>
                                        <span>Ascending</span></li>
                                    <li class="item_filter_btn" data-filter=".rating_1"><span>1</span></li>
                                    <li class="item_filter_btn" data-filter=".rating_2"><span>2</span></li>
                                    <li class="item_filter_btn" data-filter=".rating_3"><span>3</span></li>
                                    <li class="item_filter_btn" data-filter=".rating_4"><span>4</span></li>
                                    <li class="item_filter_btn" data-filter=".rating_5"><span>5</span></li>
                                </ul>
                            </li>
                            <li>
                                <span class="sorting_text">Price</span>
                                <i class="fa fa-angle-down"></i>
                                <ul>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "original-order" }'><span>Default</span>
                                    </li>
                                    <li class="item_sorting_btn" data-isotope-option='{ "sortBy": "price" }'>
                                        <span>Price</span></li>
                                </ul>
                            </li>
                            <li>
                                <span class="sorting_text">Facility</span>
                                <i class="fa fa-angle-down"></i>
                                <ul>
                                    <li><span>Facility</span></li>
                                    <li><span>Facility</span></li>
                                    <li><span>Facility</span></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="sorting_group_2 clearfix">
                        <ul>
                            <li>Airplane X</li>
                            <li>5 stars X</li>
                            <li>Romantic X</li>
                        </ul>
                        <div class="sorting_icons clearfix">
                            <div class="detail_view"><i class="fa fa-bars" aria-hidden="true"></i></div>
                            <div class="box_view"><i class="fa fa-th-large" aria-hidden="true"></i></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">

                <c:choose>
                    <c:when test="${searchResult.size() > 0}">
                        <c:forEach items="${searchResult}" var="tour">
                            <c:url var="tourUrl" value="/tour">
                                <c:param name="id" value="${tour.getId()}"/>
                            </c:url>
                            <div class="item col-lg-6">
                                <div class="item_image">
                                    <img src="${tour.getImage()}" alt="">
                                </div>
                                <div class="item_content">
                                    <div class="item_title">${tour.getName()}</div>
                                    <div class="item_price">${String.format("%,d", tour.getPrice())} đ</div>
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
                            <h5 class="text-center">Không có kết quả tìm kiếm phù hợp.</h5>
                        </div>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>

        <!-- Newsletter -->
        <jsp:include page="components/newsletter.jsp"/>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp"/>
    </div>

    <jsp:include page="components/globalScripts.jsp"/>
    <script src="assets/vendor/greensock/TweenMax.min.js"></script>
    <script src="assets/vendor/greensock/TimelineMax.min.js"></script>
    <script src="assets/vendor/scrollmagic/ScrollMagic.min.js"></script>
    <script src="assets/vendor/greensock/animation.gsap.min.js"></script>
    <script src="assets/vendor/greensock/ScrollToPlugin.min.js"></script>
    <script src="assets/vendor/Isotope/isotope.pkgd.min.js"></script>
    <script src="assets/js/search.js"></script>
</body>

</html>
