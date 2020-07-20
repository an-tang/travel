<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.bean.OrderBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.bean.TourBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<TourBean> listTours = (ArrayList<TourBean>) request.getAttribute("listTours");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/components/admin/adminHeadTags.jsp"/>
</head>

<body class="fix-header fix-sidebar card-no-border">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10"/>
    </svg>
</div>
<div id="main-wrapper">

    <jsp:include page="../components/admin/adminHeader.jsp"/>
    <jsp:include page="../components/admin/slideBar.jsp"/>

    <!-- Page wrapper  -->
    <!-- ============================================================== -->
    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="row page-titles">
                <div class="col-md-5 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">Table</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Tours</li>
                    </ol>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <div class="row">
                <!-- column -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Tours</h4>
                            <h6 class="card-subtitle">Add class <code>.table</code></h6>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tour</th>
                                        <th>Giới thiệu</th>
                                        <th>Thông tin</th>
                                        <th>Đơn Giá</th>
<%--                                        <th>Trạng Thái</th>--%>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i = 1;

                                    %>
                                    <c:forEach items="${listTours}" var="tour">
                                        <c:url var="tourUrl" value="/tour">
                                            <c:param name="id" value="${tour.getId()}"/>
                                        </c:url>
                                        <tr>
                                            <%
                                                out.println("<td>" + i + "</td>");
                                                i++;
                                            %>
                                            <td>${tour.getName()}</td>
                                            <td>${tour.getImage()}</td>
                                            <td>${tour.getProvinceID()}</td>
                                            <td>${tour.getPrice()}</td>
<%--                                            <td>${tour.getStatus() == 0 ? "Đang hoạt động":"Ngưng hoạt động"}</td>--%>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End PAge Content -->
            <!-- ============================================================== -->
        </div>
        <!-- ============================================================== -->
        <!-- End Container fluid  -->
        <!-- ============================================================== -->
        <!-- ============================================================== -->
        <!-- footer -->
        <!-- ============================================================== -->
        <footer class="footer">
            © 2017 Material Pro Admin by wrappixel.com
        </footer>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->


</div>
<jsp:include page="../components/admin/adminScipts.jsp"/>

</body>

</html>
