<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.viewmodel.ChartValue" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.viewmodel.OrderDetail" %>
<%@ page import="com.travel.bean.TourBean" %>
<%@ page import="com.travel.viewmodel.UserReport" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<ChartValue> chartValues = (ArrayList<ChartValue>) request.getAttribute("chartValues");
    ArrayList<ChartValue> chartValues1 = (ArrayList<ChartValue>) request.getAttribute("chartValues1");
    ArrayList<TourBean> listTopOrders = (ArrayList<TourBean>) request.getAttribute("listTopOrders");
    ArrayList<UserReport> listTopUsers = (ArrayList<UserReport>) request.getAttribute("listTopUsers");
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
                <div class="col-md-5 col-8 align-self-center">
                    <h3 class="text-themecolor">Dashboard</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Dashboard</li>
                    </ol>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->
            <!-- Row -->
            <div class="row">
                <!-- Column -->

                <%--                Biểu đồ theo tỉnh--%>
                <div class="col-lg-8 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-12">
                                    <div class="d-flex flex-wrap">
                                        <div>
                                            <h3 class="card-title">Orders by Province</h3>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="amp-pxl" style="height: 360px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                Biểu đồ theo khu vực--%>
                <div class="col-lg-4 col-md-5">
                    <div class="card">
                        <div class="card-block">
                            <h3 class="card-title">Orders by Region</h3>
                            <div id="visitor" style="height:290px; width:100%;"></div>
                        </div>
                        <div>
                            <hr class="m-t-0 m-b-0">
                        </div>
                        <div class="card-block text-center ">
                            <ul class="list-inline m-b-0">
                                <li>
                                    <h6 class="text-muted  text-primary"><i class="fa fa-circle font-10 m-r-10"></i>Miền
                                        Bắc
                                    </h6>
                                </li>
                                <li>
                                    <h6 class="text-muted text-info"><i class="fa fa-circle font-10 m-r-10 "></i>Miền
                                        Trung
                                    </h6></li>
                                <li>
                                    <h6 class="text-muted  text-success"><i class="fa fa-circle font-10 m-r-10"></i>Miền
                                        Nam
                                    </h6></li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
            <!-- Row -->
            <!-- Row -->
            <div class="row">
                <!-- Top Orders mới nhất -->
                <div class="col-lg-8 col-lg-7">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Top Tour By Order </h4>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tour</th>
                                        <th>Price</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i = 1;

                                    %>
                                    <c:forEach items="${listTopOrders}" var="order">
                                        <tr>
                                            <%
                                                out.println("<td>" + i + "</td>");
                                                i++;
                                            %>
                                            <td>${order.getName()}</td>
                                            <td>${order.getPrice()}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <%--                Top User by order--%>
                <div class="col-lg-4 col-md-5">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Top User order</h4>
                            <div class="table-responsive">

                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Name</th>
                                        <th>Orders</th>
                                        <th>Total</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i1 = 1;

                                    %>
                                    <c:forEach items="${listTopUsers}" var="user">
                                        <tr>
                                            <%
                                                out.println("<td>" + i1 + "</td>");
                                                i1++;
                                            %>
                                            <td>${user.getUserName()}</td>
                                            <td>${user.getOrders()}</td>
                                            <td>${user.getTotalAmount()}</td>
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
            © 2020 UIT Travel Admin
        </footer>    <!-- ============================================================== -->
        <!-- End footer -->
        <!-- ============================================================== -->
    </div>

</div>
<jsp:include page="../components/admin/adminScipts.jsp"/>
<script>
    $(document).ready(function () {
        //chart donut Area
        var chart = c3.generate({
            bindto: '#visitor',
            data: {
                columns: [
                    <c:forEach items="${chartValues}" var="chartArea">
                    ['${chartArea.getKey()}', ${chartArea.getValue()}],
                    </c:forEach>
                ],

                type: 'donut',
                onclick: function (d, i) {
                    console.log("onclick", d, i);
                },
                onmouseover: function (d, i) {
                    console.log("onmouseover", d, i);
                },
                onmouseout: function (d, i) {
                    console.log("onmouseout", d, i);
                }
            },
            donut: {
                label: {
                    show: false
                },
                title: "Debt by Area",
                width: 20,

            },

            legend: {
                hide: true
                //or hide: 'data1'
                //or hide: ['data1', 'data2']
            },
            color: {
                pattern: ['#eceff1', '#745af2', '#26c6da', '#1e88e5']
            }
        });

        //chart column province
        var chart2 = new Chartist.Bar('.amp-pxl', {
            labels: [
                <c:forEach items="${chartValues1}" var="chartArea">
                ['${chartArea.getKey()}',],
                </c:forEach>
            ],
            series: [
                [<c:forEach items="${chartValues1}" var="chartArea">
                    ${chartArea.getValue()},
                    </c:forEach>
                ]
            ]
        }, {
            axisX: {
                // On the x-axis start means top and end means bottom
                position: 'end',
                showGrid: false
            },
            axisY: {
                // On the y-axis start means left and end means right
                position: 'start'
            },
            high: '12',
            low: '0',
            plugins: [
                Chartist.plugins.tooltip()
            ]
        });
    })
</script>
</body>

</html>
