<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/components/admin/adminHeadTags.jsp"/>
    <link rel="stylesheet" type="text/css" href="../assets/css/admin/adminQuickSearch.css">
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
                <div class="col-md-12 col-8 align-self-center">
                    <h3 class="text-themecolor m-b-0 m-t-0">Table</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Tours</li>
                        <button class="btn waves-effect waves-green btn-twitter hidden-md-down btn-show-create"
                                type="button"
                                style="float: right">
                            Create
                        </button>
                    </ol>
                </div>
            </div>
            <!-- ============================================================== -->
            <!-- End Bread crumb and right sidebar toggle -->
            <!-- ============================================================== -->
            <!-- ============================================================== -->
            <!-- Start Page Content -->
            <!-- ============================================================== -->

            <div class="admin-quick-search-wrapper row">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-block">
                            <form action="/admin-tour-search" method="POST" class="admin-quick-search d-flex align-items-start">
                                <input type="search" class="form-control" name="q" required="required" placeholder="Tìm kiếm">
                                <button class="btn btn-success" type="submit">
                                    <i class="ti-search"></i>
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <!-- column -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-block">
                            <div class="table-responsive">
                                <table id="tourDetailTable" class="table mb-0">
                                    <thead>
                                        <tr>
                                            <th>STT</th>
                                            <th>Name</th>
                                            <th>Title</th>
                                            <th>Price</th>
                                            <th>Province</th>
                                            <th>Status</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <jsp:include page="../components/admin/adminTourList.jsp"/>
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

<%--popup--%>
<jsp:include page="../admin/popupTour/TourCreate.jsp"/>
<jsp:include page="../admin/popupTour/TourScript.jsp"/>
<script src="../assets/js/components/adminQuickSearch.js"></script>

</body>
</html>
