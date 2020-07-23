<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.bean.UserBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    ArrayList<UserBean> listUsers = (ArrayList<UserBean>) request.getAttribute("listUsers");
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
                        <li class="breadcrumb-item active">Users</li>
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
                            <h4 class="card-title">Users</h4>
                            <div class="table-responsive">

                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th readonly="">Tên đăng nhập</th>
                                        <th>Họ và tên</th>
                                        <th>Email</th>
                                        <th>Trạng Thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i = 1;

                                    %>
                                    <c:forEach items="${listUsers}" var="user">
                                        <c:url var="userUrl" value="/profile">
                                            <c:param name="id" value="${user.getId()}"/>
                                        </c:url>
                                        <form action="/admin/Users"
                                              method="post">
                                            <tr>
                                                <%
                                                    out.println("<td>" + i + "</td>");
                                                    i++;
                                                %>
                                                <td>${user.getUserName()}</td>
                                                <td>${user.getName()}</td>
                                                <td>${user.getEmail()}</td>
                                                <td>${user.getStatus() == 1 ? "Đang hoạt động":"Ngưng hoạt động"}</td>
                                                <td>

                                                    <button class="btn waves-effect waves-green btn-facebook hidden-md-down"
                                                            type="submit"
                                                            name="id_user_active" value="${user.getId()}">
                                                        Active

                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn waves-effect waves-red btn-red hidden-md-down"
                                                            type="submit"
                                                            name="id_user_deactive"
                                                            value="${user.getId()}">
                                                        Deactive

                                                    </button>
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
    <!-- ============================================================== -->
    <!-- End Page wrapper  -->
    <!-- ============================================================== -->


</div>
<jsp:include page="../components/admin/adminScipts.jsp"/>

</body>

</html>
