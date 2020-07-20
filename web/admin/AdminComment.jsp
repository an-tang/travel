<%@ page import="com.travel.bean.CommentBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<CommentBean> listComments = (ArrayList<CommentBean>) request.getAttribute("listComments");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/components/admin/adminHeadTags.jsp"/>
</head>

<body class="fix-header fix-sidebar card-no-border">
<div class="preloader">
    <svg class="circular" viewBox="25 25 50 50">
        <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
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
                        <li class="breadcrumb-item active">Comment</li>
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
                            <h4 class="card-title">Comments</h4>
                            <h6 class="card-subtitle">Add class <code>.table</code></h6>
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Bình luận</th>
                                        <th>Tour</th>
                                        <th>Khách hàng</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%--STT--%>
                                    <%
                                        int i = 1;

                                    %>
                                    <c:forEach items="${listComments}" var="comment">
                                        <c:url var="commentUrl" value="/comment">
                                            <c:param name="id" value="${comment.getId()}"/>
                                        </c:url>
                                        <tr>
                                            <%
                                                out.println("<td>" + i + "</td>");
                                                i++;
                                            %>
                                            <td>${comment.getContent()}</td>
                                            <td>${comment.getTourInfoID()}</td>
                                            <td>${comment.getUserName()}</td>
                                            <td>${comment.getStatus() == 1 ? "Đang hoạt động":"Ngưng hoạt động"}</td>
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
