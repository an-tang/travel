<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.bean.OrderBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.travel.bean.TourBean" %>
<%@ page import="com.travel.viewmodel.TourDetail" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<TourDetail> listTours = (ArrayList<TourDetail>) request.getAttribute("listTours");
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
                        <button class="btn waves-effect waves-green btn-facebook hidden-md-down btn-show-update"
                                type="button">
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
            <div class="row">
                <!-- column -->
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-block">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>STT</th>
                                        <th>Name</th>
                                        <th>Title</th>
                                        <th>Price</th>
                                        <th>Province</th>
                                        <th>Status</th>
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
                                                <td>${tour.getStatus() == 1 ? "Đang hoạt động":"Ngưng hoạt động"}</td>
                                                <td>

                                                    <button class="btn waves-effect waves-green btn-facebook hidden-md-down btn-show-update"
                                                            type="button">
                                                        Update
                                                    </button>
                                                </td>
                                                <td>

                                                    <button class="btn waves-effect waves-green btn-facebook hidden-md-down"
                                                            type="submit"
                                                            name="id_tour_active"
                                                            value="${tour.getId()}">
                                                        Active
                                                    </button>
                                                </td>
                                                <td>
                                                    <button class="btn waves-effect waves-red btn-red hidden-md-down"
                                                            type="submit"
                                                            name="id_tour_deactive"
                                                            value="${tour.getId()}">
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
<div id="modal" class="modal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document" style="max-width: 900px!important;">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Create Tour</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
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
                                        <%--                                           value="<%=admin.getUserName()%>"--%>
                                        >
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Title</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               name="tour_title"
                                               class="form-control form-control-line"
                                        <%--                                           value="<%=admin.getName()%>"--%>
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
                                        <%--                                           value="<%=admin.getEmail()%>"--%>
                                        >
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Price</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               class="form-control form-control-line"
                                               name="tour_price"
                                        <%--                                           value="<%=admin.getPhone()%>"--%>
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


                                <div class="col-md-12">
                                    <div class="form-group" id="form-group-image">
                                        <label class="col-sm-5">Image</label>
                                        <div class="row col-md-12">
                                            <div class="col-sm-5">
                                                <div class="row-cols-sm-5">URL
                                                    <input type="text"
                                                           class="form-control form-control-line"
                                                           name="image_URL"
                                                    <%--                                           value="<%=admin.getPhone()%>"--%>
                                                    >
                                                </div>
                                            </div>
                                            <div class="col-sm-5">
                                                <div class="row-cols-sm-5">Description
                                                    <input type="text"
                                                           class="form-control form-control-line"
                                                           name="image_description"
                                                    <%--                                           value="<%=admin.getPhone()%>"--%>
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
                                <button type="button" class="btn btn-secondary close" data-dismiss="modal">Close
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>


        </div>

    </div>
</div>
<script>
    var html = ' <div class="row col-md-12">\n' +
        '                                            <div class="col-sm-5">\n' +
        '                                                <div class="row-cols-sm-5">URL\n' +
        '                                                    <input type="text"\n' +
        '                                                           class="form-control form-control-line"\n' +
        '                                                           name="image_URL"\n' +
        '                                                    <%--                                           value="<%=admin.getPhone()%>"--%>\n' +
        '                                                    >\n' +
        '                                                </div>\n' +
        '                                            </div>\n' +
        '                                            <div class="col-sm-5">\n' +
        '                                                <div class="row-cols-sm-5">Description\n' +
        '                                                    <input type="text"\n' +
        '                                                           class="form-control form-control-line"\n' +
        '                                                           name="image_description"\n' +
        '                                                    <%--                                           value="<%=admin.getPhone()%>"--%>\n' +
        '                                                    >\n' +
        '                                                </div>\n' +
        '                                            </div>\n' +
        '                                            <div class="col-sm-2"\n' +
        '                                                 style="display: flex; align-items: flex-end; margin-bottom: 5px">\n' +
        '                                                <button class="removeItem btn btn-primary" type="button">\n' +
        '                                                    Remove\n' +
        '                                                </button>\n' +
        '\n' +
        '                                            </div>\n' +
        '\n' +
        '                                        </div>'
    $(document).ready(function () {
        $(document).on('click', '.btn-show-update', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#modal').addClass('show');
        })
        $(document).on('click', '.close', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#modal').removeClass('show');
        })

        $(document).on('click', '.addItem', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click')
            $('#form-group-image').append(html)
        })

        $(document).on('click', '.removeItem', function (e) {
            e.preventDefault()
            e.stopPropagation()
            console.log('click remove')
            $($($(e.target).parent()).parent()).remove()
        })

    });
    $(document).ready(function () {

    })
</script>

</body>
</html>
