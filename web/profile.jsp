<%@ page import="com.travel.bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    UserBean customer = (UserBean) request.getAttribute("customer");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/profile.css">
    <title>Thông tin cá nhân</title>
</head>
<body>
    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp" />

        <!-- Menu -->
        <jsp:include page="components/menu.jsp" />

        <section class="customer-profile">
            <div class="container">
                <div class="row">
                    <div class="col-12">
                        <h2 id="customerProfile">Thông tin khách hàng</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-xl-4"></div>
                    <div class="col-md-6 col-xl-4">
                        <form
                            action="/profile"
                            class="profile"
                            method="post"
                            data-general-error="Invalid field. Please check your input again."
                            data-request-error="Request to server unexpectedly failed. Please try again later.">

                            <input type="hidden" name="form_name" value="update_profile">

                            <div class="form-group required">
                                <label class="form-control-label" for="profileUsername">Tên đăng nhập</label>
                                <input
                                    readonly
                                    type="text"
                                    id="profileUsername"
                                    class="form-control"
                                    name="user"
                                    value="<%=customer.getUserName()%>"
                                    required pattern="^[\w]{6,18}$"
                                    data-missing-error="Vui lòng nhập tên đăng nhập"
                                    data-pattern-mismatch="Tên đăng nhập dài 6-18 ký tự và không được chứa ký tự đặc biệt">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group">
                                <label class="form-control-label" for="profileName">Tên</label>
                                <input
                                    type="text"
                                    id="profileName"
                                    class="form-control"
                                    name="name"
                                    value="<%=customer.getName()%>"
                                    maxlength="50"
                                    data-range-error="Tên không dài quá 50 ký tự">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group required">
                                <label class="form-control-label" for="profilePhone">Số điện thoại</label>
                                <input
                                    type="text"
                                    id="profilePhone"
                                    class="form-control"
                                    name="phone"
                                    value="<%=customer.getPhone()%>"
                                    required pattern="((?=(0))[0-9]{10})$"
                                    data-missing-error="Vui lòng nhập số điện thoại"
                                    data-pattern-mismatch="Vui lòng sử dụng số điện thoại hợp lệ">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group">
                                <label class="form-control-label" for="profileEmail">Email</label>
                                <input
                                    type="email"
                                    id="profileEmail"
                                    class="form-control"
                                    name="email"
                                    value="<%=customer.getEmail()%>"
                                    pattern="^[\w.%+-]+@[\w.-]+\.[\w]{2,6}$"
                                    data-pattern-mismatch="Vui lòng sử dụng email hợp lệ">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group text-center mb-0">
                                <button type="submit" name="profile" class="btn btn-danger btn-accent">Lưu thay đổi</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3 col-xl-4"></div>
                </div>

                <div class="row pt-5">
                    <div class="col-12">
                        <h2 id="changePassword">Đổi mật khẩu</h2>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 col-xl-4"></div>
                    <div class="col-md-6 col-xl-4">
                        <form
                            action="/profile"
                            class="change-password"
                            method="post"
                            data-general-error="Invalid field. Please check your input again."
                            data-request-error="Request to server unexpectedly failed. Please try again later.">

                            <input type="hidden" name="form_name" value="change_password">

                            <div class="form-group required">
                                <label class="form-control-label" for="profileCurrentPassword">Mật khẩu hiện tại</label>
                                <input
                                    type="password"
                                    id="profileCurrentPassword"
                                    class="form-control"
                                    name="current_pwd"
                                    required minlength="6"
                                    data-missing-error="Vui lòng nhập mật khẩu"
                                    data-range-error="Mật khẩu phải dài tối thiểu 6 ký tự">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group required">
                                <label class="form-control-label" for="profileNewPassword">Mật khẩu mới</label>
                                <input
                                    type="password"
                                    id="profileNewPassword"
                                    class="form-control"
                                    name="new_pwd"
                                    required minlength="6"
                                    data-missing-error="Vui lòng nhập mật khẩu"
                                    data-range-error="Mật khẩu phải dài tối thiểu 6 ký tự">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group required">
                                <label class="form-control-label" for="profileConfirmNewPassword">Xác nhận mật khẩu mới</label>
                                <input
                                    type="password"
                                    id="profileConfirmNewPassword"
                                    class="form-control"
                                    name="new_pwd_confirm"
                                    required
                                    data-missing-error="Vui lòng xác nhận mật khẩu"
                                    data-password-mismatch="Mật khẩu không trùng khớp">
                                <div class="invalid-msg"></div>
                            </div>

                            <div class="form-group text-center mb-0">
                                <button type="submit" name="change_password" class="btn btn-danger btn-accent">Lưu thay đổi</button>
                            </div>
                        </form>
                    </div>
                    <div class="col-md-3 col-xl-4"></div>
                </div>
            </div>
        </section>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp" />
    </div>

    <jsp:include page="components/globalScripts.jsp" />
    <script src="assets/js/components/clientSideValidation.js"></script>
    <script src="assets/js/profile.js"></script>
</body>
</html>
