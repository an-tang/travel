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

<!-- Page wrapper  -->
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- Container fluid  -->
<!-- ============================================================== -->
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

                                        <button class="btn waves-effect waves-green btn-facebook hidden-md-down"
                                                type="submit"
                                                name="id_tour_update"
                                                value="${tour.getId()}">
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
<button>Update</button>
<!-- ============================================================== -->
<!-- ============================================================== -->
<!-- End Page wrapper  -->
<!-- ============================================================== -->


<jsp:include page="../components/admin/adminScipts.jsp"/>

</body>

</html>
