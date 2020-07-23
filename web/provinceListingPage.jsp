<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.bean.ProvinceBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<ProvinceBean> northernProvinces = (ArrayList<ProvinceBean>) request.getAttribute("northernProvinces");
    ArrayList<ProvinceBean> centralProvinces = (ArrayList<ProvinceBean>) request.getAttribute("centralProvinces");
    ArrayList<ProvinceBean> southernProvinces = (ArrayList<ProvinceBean>) request.getAttribute("southernProvinces");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/provinceListingPage.css">
    <title>Tất cả tỉnh thành</title>
</head>

<body>
    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp"/>

        <!-- Menu -->
        <jsp:include page="components/menu.jsp"/>

        <!-- Home -->
        <div class="search-banner province-listing-banner">
            <div class="home_background">
                <img src="assets/images/last.jpg" alt="">
            </div>
            <div class="home_content container">
                <div class="home_title">Tìm tour theo tỉnh thành</div>
                <div class="home_title_small">Lựa chọn điểm đến trong mơ của bạn</div>
            </div>
        </div>

        <!-- Footer -->
        <section class="province-listing-section">
            <div class="container">
                <div class="row pb-4">
                    <div class="col-12">
                        <h2 class="section-title">Tất cả tỉnh thành</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6 col-lg-4">
                        <div class="region-name">
                            <h3>Miền Bắc</h3>
                        </div>
                        <div class="regional-province-list">
                            <c:forEach items="${northernProvinces}" var="province">
                                <c:url var="provinceUrl" value="/province">
                                    <c:param name="prv_id" value="${province.getId()}"/>
                                </c:url>
                                <div class="regional-province">
                                    <a href="${provinceUrl}">${province.getName()}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-4">
                        <div class="region-name">
                            <h3>Miền Trung</h3>
                        </div>
                        <div class="regional-province-list">
                            <c:forEach items="${centralProvinces}" var="province">
                                <c:url var="provinceUrl" value="/province">
                                    <c:param name="prv_id" value="${province.getId()}"/>
                                </c:url>
                                <div class="regional-province">
                                    <a href="${provinceUrl}">${province.getName()}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <div class="col-md-6 col-lg-4">
                        <div class="region-name">
                            <h3>Miền Nam</h3>
                        </div>
                        <div class="regional-province-list">
                            <c:forEach items="${southernProvinces}" var="province">
                                <c:url var="provinceUrl" value="/province">
                                    <c:param name="prv_id" value="${province.getId()}"/>
                                </c:url>
                                <div class="regional-province">
                                    <a href="${provinceUrl}">${province.getName()}</a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp"/>
    </div>

    <jsp:include page="components/globalScripts.jsp"/>
</body>
</html>
