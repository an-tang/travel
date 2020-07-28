<%@ page import="com.travel.bean.ProvinceBean" %>
<%@ page import="com.travel.viewmodel.OrderReport" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.bean.AreaBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<OrderReport> orderReports = (ArrayList<OrderReport>) request.getAttribute("orderReports");
    ArrayList<ProvinceBean> listProvince = (ArrayList<ProvinceBean>) request.getAttribute("listProvince");
    ArrayList<AreaBean> listArea = (ArrayList<AreaBean>) request.getAttribute("listArea");
    AreaBean areaBean = (AreaBean) request.getAttribute("areaBean");
    ProvinceBean provinceBean = (ProvinceBean) request.getAttribute("provinceBean");
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

    <div class="page-wrapper">
        <!-- ============================================================== -->
        <!-- Container fluid  -->
        <!-- ============================================================== -->
        <div class="container-fluid">
            <!-- ============================================================== -->
            <!-- Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <div class="row page-titles">
                <div class="col-md-12 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">Table</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Reports</li>
                        <form action="/admin/Report"
                              method="get">
                            <div class=" ">

                                <label>Area </label>

                                <select class="form-control form-control-line col-md-1" name="area_id">
                                    <option value="${areaBean.getId()}">${areaBean.getName()}</option>
                                    <option value="0">All</option>
                                    <c:forEach items="${listArea}" var="area">
                                        <option value="${area.getId()}">
                                                ${area.getName()}
                                        </option>
                                    </c:forEach>
                                </select>
                                <label>Province </label>
                                <select class="form-control form-control-line col-md-1" name="province_id">
                                    <option value="${provinceBean.getId()}">${provinceBean.getName()}</option>
                                    <option value="0">All</option>
                                    <c:forEach items="${listProvince}" var="province">
                                        <option value="${province.getId()}">
                                                ${province.getName()}
                                        </option>
                                    </c:forEach>
                                </select>

                                <label for="start">Start date:</label>

                                <input type="date" id="start"
                                       class="form-control form-control-line col-md-2"
                                       name="dateFrom">

                                <label for="start"> End date:</label>
                                <input type="date" id="end"
                                       class="form-control form-control-line col-md-2"
                                       name="dateTo">

                                <button class="btn waves-effect waves-green btn-twitter hidden-md-down btn-show-create"
                                        type="submit">
                                    filter
                                </button>
                            </div>
                        </form>
                    </ol>
                </div>
            </div>
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- Start Page Content -->
            <div class="row">
                <!-- column -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Statistics of Revenue</h4>
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
                                    <c:forEach items="${orderReports}" var="order">
                                        <form action="/admin/Report"
                                              method="post">
                                            <tr>
                                                <%
                                                    out.println("<td>" + i + "</td>");
                                                    i++;
                                                %>
                                                <td>${order.getArea()}</td>
                                                <td>${order.getProvince()}</td>
                                                <td>${order.getTourName()}</td>
                                                <td>${order.getGrandAmount()}</td>
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
            <!-- End PAge Content -->
        </div>
        <!-- End Container fluid  -->
        <!-- footer -->
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

<script>

</script>
</html>
