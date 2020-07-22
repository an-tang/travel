<%@ page import="com.travel.helper.URLHelpers" %>
<%@ page import="com.travel.enumerize.PagingSize" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String pageSize = String.valueOf(PagingSize.PROVINCE_TOURS.getValue());
    String provinceId = String.valueOf(request.getAttribute("provinceId"));
    String provinceName = (String) request.getAttribute("provinceName");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/search.css">
    <title>Tour <%=provinceName%></title>
</head>

<body>

<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp"/>

    <!-- Menu -->
    <jsp:include page="components/menu.jsp"/>

    <!-- Home -->
    <jsp:include page="components/search/searchBanner.jsp"/>

    <!-- Offers -->
    <div class="offers container">
        <div class="row">
            <div class="col">
                <div class="section_title text-center">
                    <h2 id="section_title-with-q">Các tour <%=provinceName%></h2>
                </div>
            </div>
        </div>
        <div class="row mb-4">
            <div class="col-8"></div>
            <div class="col-1 d-flex align-items-center">
                <h5 class="mb-0">Sắp xếp:</h5>
            </div>
            <div class="col-3">
                <select class="form-control custom-select" id="sortOptions">
                    <option
                        selected
                        value="<%=URLHelpers.buildRelativeURL("/province", "prv_id", provinceId, "sortField", "name", "sortType", "ASC", "start", "0", "size", pageSize)%>">
                        Tên từ A-Z
                    </option>
                    <option
                        value="<%=URLHelpers.buildRelativeURL("/province", "prv_id", provinceId, "sortField", "name", "sortType", "DESC", "start", "0", "size", pageSize)%>">
                        Tên từ Z-A
                    </option>
                    <option
                        value="<%=URLHelpers.buildRelativeURL("/province", "prv_id", provinceId, "sortField", "price", "sortType", "ASC", "start", "0", "size", pageSize)%>">
                        Giá tăng dần
                    </option>
                    <option
                        value="<%=URLHelpers.buildRelativeURL("/province", "prv_id", provinceId, "sortField", "price", "sortType", "DESC", "start", "0", "size", pageSize)%>">
                        Giá giảm dần
                    </option>
                </select>
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
        <div class="row tour-listing-row">
            <jsp:include page="components/search/tourListing.jsp" />
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
