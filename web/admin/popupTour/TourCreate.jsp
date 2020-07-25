<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.travel.bean.ProvinceBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<ProvinceBean> listProvince = (ArrayList<ProvinceBean>) request.getAttribute("listProvince");
%>
<div id="tour_create" class="modal" tabindex="-1" role="dialog">
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
                                action="/admin/createTour"
                                method="post">
                            <div class="row">
                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Name</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               name="tour_name"
                                               class="form-control form-control-line"
                                        >
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Title</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               name="tour_title"
                                               class="form-control form-control-line"
                                        >
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Detail</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               class="form-control form-control-line"
                                               name="tour_detail"
                                        >
                                    </div>
                                </div>
                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Price</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               class="form-control form-control-line"
                                               name="tour_price"
                                        >
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="col-sm-12">Province</label>
                                    <div class="col-sm-12">
                                        <select class="form-control form-control-line" name="province_id">
                                            <option value="${province.getId()}">${province.getName()}</option>
                                            <c:forEach items="${listProvince}" var="province">
                                                <option value="${province.getId()}">
                                                        ${province.getName()}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>

                                <div class="form-group col-md-6">
                                    <label class="col-md-12">Image</label>
                                    <div class="col-md-12">
                                        <input type="text"
                                               class="form-control form-control-line"
                                               name="tour_image"
                                        >
                                    </div>
                                </div>

                                <%--List images--%>
                                <div class="col-md-12">
                                    <div class="form-group" id="form-group-image">
                                        <label class="col-sm-5">List Image</label>

                                            <div class="row col-md-12">
                                                <div class="col-sm-5">
                                                    <div class="row-cols-sm-5">URL
                                                        <input type="text"
                                                               class="form-control form-control-line"
                                                               name="image_URL"
                                                        >
                                                    </div>
                                                </div>
                                                <div class="col-sm-5">
                                                    <div class="row-cols-sm-5">Description
                                                        <input type="text"
                                                               class="form-control form-control-line"
                                                               name="image_description"
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
                                <button type="submit"
                                        class="btn btn-primary"
                                        name="tour_id"
                                        >
                                    Save changes
                                </button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>


        </div>

    </div>
</div>