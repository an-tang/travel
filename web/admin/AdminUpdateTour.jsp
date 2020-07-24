<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.viewmodel.CreateTourRequest" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    CreateTourRequest tourUpdate = (CreateTourRequest) request.getAttribute("tourUpdate");
    String province = (String) request.getAttribute("province");
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
                    <h3 class="text-themecolor m-b-0 m-t-0">Profile</h3>
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">Home</a></li>
                        <li class="breadcrumb-item active">Tour Detail</li>
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
                <div class="col-lg-12 col-xlg-9 col-md-7">
                    <div class="card">
                        <div class="card-block">
                            <form
                                    action="/admin/Tours"
                                    method="post">
                                <div class="row">
                                    <div class="form-group col-md-6">
                                        <label class="col-md-12">Name</label>
                                        <div class="col-md-12">
                                            <input type="text"
                                                   name="tour_name"
                                                   class="form-control form-control-line"
                                                   value="<%=tourUpdate.getName()%>"
                                            >
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label class="col-md-12">Title</label>
                                        <div class="col-md-12">
                                            <input type="text"
                                                   name="tour_title"
                                                   class="form-control form-control-line"
                                                   value="<%=tourUpdate.getTitle()%>"
                                            >
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label for="example-email" class="col-md-12">Detail</label>
                                        <div class="col-md-12">
                                            <input type="email"
                                                   class="form-control form-control-line"
                                                   name="tour_detail"
                                                   id="example-email"
                                                   value="<%=tourUpdate.getDetail()%>"
                                            >
                                        </div>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="col-md-12">Price</label>
                                        <div class="col-md-12">
                                            <input type="text"
                                                   class="form-control form-control-line"
                                                   name="tour_price"
                                                   value="<%=tourUpdate.getPrice()%>"
                                            >
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label class="col-sm-12">Province</label>
                                        <div class="col-sm-12">
                                            <select class="form-control form-control-line">
                                                <option>London</option>
                                                <option>India</option>
                                                <option>Usa</option>
                                                <option>Canada</option>
                                                <option>Thailand</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group col-md-6">
                                        <label class="col-md-12">Image</label>
                                        <div class="col-md-12">
                                            <input type="text"
                                                   class="form-control form-control-line"
                                                   name="tour_image"
                                                   value="<%=tourUpdate.getImage()%>"
                                            >
                                        </div>
                                    </div>

                                    <%--List images--%>
                                    <div class="col-md-12">
                                        <div class="form-group" id="form-group-image">
                                            <label class="col-sm-5">List Image</label>

                                            <c:forEach items="${tourUpdate.getImages()}" var="image">
                                            <div class="row col-md-12">
                                                    <div class="col-sm-5">
                                                        <div class="row-cols-sm-5">URL
                                                            <input type="text"
                                                                   class="form-control form-control-line"
                                                                   name="image_URL"
                                                                   value="${image.getUrl()}"
                                                            >
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-5">
                                                        <div class="row-cols-sm-5">Description
                                                            <input type="text"
                                                                   class="form-control form-control-line"
                                                                   name="image_description"
                                                                   value="${image.getDescription()}"
                                                            >
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-2"
                                                         style="display: flex; align-items: flex-end; margin-bottom: 5px">
                                                        <button class="removeItem btn btn-primary" type="button">
                                                            Remove
                                                        </button>

                                                    </div>
                                            </div>
                                            </c:forEach>


                                        </div>

                                    </div>

                                    <div class="col-md-12">
                                        <div class="col-sm-12"
                                             style="display: flex; align-items: flex-end; margin-bottom: 5px">
                                            <button class="addItem btn btn-red" type="button">
                                                Add
                                            </button>

                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="submit" class="btn btn-primary">Save changes</button>
                                </div>
                            </form>

                        </div>
                    </div>

                </div>
                <!-- Column -->
            </div>
            <!-- Row -->
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

<jsp:include page="../admin/popupTour/TourScript.jsp"/>

</body>

</html>
