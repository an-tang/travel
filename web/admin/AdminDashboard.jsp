<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.viewmodel.ChartValue" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<ChartValue> chartValues = (ArrayList<ChartValue>) request.getAttribute("chartValues");
    ArrayList<ChartValue> chartValues1 = (ArrayList<ChartValue>) request.getAttribute("chartValues1");
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
                <div class="col-lg-8 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <div class="row">
                                <div class="col-12">
                                    <div class="d-flex flex-wrap">
                                        <div>
                                            <h3 class="card-title">Biểu đồ Order theo tỉnh</h3>
                                            <h6 class="card-subtitle">template</h6></div>
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="amp-pxl" style="height: 360px;"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-5">
                    <div class="card">
                        <div class="card-block">
                            <h3 class="card-title">Biểu đồ Order theo Khu vực </h3>
                            <h6 class="card-subtitle">template</h6>
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
                <!-- Column -->
                <div class="col-lg-8 col-lg-7">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Orders mới nhất</h4>
                            <h6 class="card-subtitle">Add class <code>.table</code></h6>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Tour</th>
                                        <th >Giá/lượt</th>
                                        <th>Số lượng khách</th>
                                        <th>Tổng tiền</th>
                                        <th>Phương thức thanh toán</th>
                                        <th>Ghi chú</th>
                                        <th>Trạng Thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i = 1;

                                    %>
                                    <c:forEach items="${listOrders}" var="order">
                                        <c:url var="orderUrl" value="/orders">
                                            <c:param name="id" value="${order.getId()}"/>
                                        </c:url>
                                        <form action="/admin/Orders"
                                              method="post">
                                            <tr>
                                                <%
                                                    out.println("<td>" + i + "</td>");
                                                    i++;
                                                %>
                                                <td>${order.getName()}</td>
                                                <td>${order.getPrice()}</td>
                                                <td>${order.getPassenger()}</td>
                                                <td>${order.getTotalAmount()}</td>
                                                <td>${order.getPaymentMethod()}</td>
                                                <td>${order.getDescription()}</td>
                                                <td>${order.getStatus() == 1 ? "Mới"
                                                        : order.getStatus() == 2? "Đã Thanh Toán"
                                                        :order.getStatus() == 3?"Lỗi"
                                                        :order.getStatus() == 4?"Hoàn tất"
                                                        :order.getStatus() == 5?"Đã hủy"
                                                        :"Ngưng hoạt động"}
                                                </td>
                                            </tr>
                                        </form>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4 col-md-5">
                    <div class="card">
                        <div class="card-block">
                            <h4 class="card-title">Top User order</h4>
                            <h6 class="card-subtitle">Add class <code>.table</code></h6>
                            <div class="table-responsive">

                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Name</th>
                                        <th>Email</th>
                                        <th>Orders</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i1 = 1;

                                    %>
                                    <c:forEach items="${listUsers}" var="user">
                                        <c:url var="userUrl" value="/profile">
                                            <c:param name="id" value="${user.getId()}"/>
                                        </c:url>
                                        <form action="/admin/Users"
                                              method="post">
                                            <tr>
                                                <%
                                                    out.println("<td>" + i1 + "</td>");
                                                    i1++;
                                                %>
                                                <td>${user.getUserName()}</td>
                                                <td>${user.getName()}</td>
                                                <td>${user.getEmail()}</td>
                                                <td>${user.getStatus() == 1 ? "Đang hoạt động":"Ngưng hoạt động"}</td>

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
