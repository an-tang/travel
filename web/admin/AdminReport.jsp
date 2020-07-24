<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <li class="breadcrumb-item active">Reports</li>

                    </ol>
                </div>
                <div class="nhan">
                    <label for="start">Start date:</label>

                    <input type="date" id="start" name="trip-start">

                    <label for="start"> End date:</label>
                    <input type="date" id="end" name="trip-start">
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
                            <h4 class="card-title">Thống kê Doanh Thu</h4>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Area</th>
                                        <th>Province</th>
                                        <th>Tour</th>
                                        <th>Grand Total</th>
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
                                        <form action="/admin/Tours"
                                              method="post">
                                            <tr>
                                                <%
                                                    out.println("<td>" + i + "</td>");
                                                    i++;
                                                %>
                                                <td>${tour.getName()}</td>
                                                <td>${tour.getTitle()}</td>
                                                <td>${tour.getPrice()}</td>
                                                <td>${tour.getProvince()}</td>
                                            </tr>
                                        </form>
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
            © 2020 UIT Travel Admin
        </footer>
        <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>


</div>
<jsp:include page="../components/admin/adminScipts.jsp"/>

</body>

</html>
