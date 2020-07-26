<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String resetPasswordToken = (String) request.getAttribute("resetPasswordToken");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/resetPassword.css">
    <title>Đặt lại mật khẩu</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="reset-password-form-section">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h2 class="section-title text-center mb-4">Đặt lại mật khẩu</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 col-xl-4"></div>
                <div class="col-md-6 col-xl-4">
                    <form
                        action="/reset-password"
                        class="reset-password"
                        method="post"
                        data-general-error="Invalid field. Please check your input again."
                        data-request-error="Request to server unexpectedly failed. Please try again later.">

                        <div class="form-group required">
                            <label class="form-control-label" for="newPassword">Mật khẩu mới</label>
                            <input
                                type="password"
                                id="newPassword"
                                class="form-control"
                                name="new_pwd"
                                required minlength="6"
                                data-missing-error="Vui lòng nhập mật khẩu"
                                data-range-error="Mật khẩu phải dài tối thiểu 6 ký tự">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group required">
                            <label class="form-control-label" for="confirmNewPassword">Xác nhận mật khẩu mới</label>
                            <input
                                type="password"
                                id="confirmNewPassword"
                                class="form-control"
                                name="new_pwd_confirm"
                                required
                                data-missing-error="Vui lòng xác nhận mật khẩu"
                                data-password-mismatch="Mật khẩu không trùng khớp">
                            <div class="invalid-msg"></div>
                        </div>

                        <input type="hidden" name="token" value="<%=resetPasswordToken%>">

                        <div class="form-group text-center mb-0">
                            <button type="submit" class="btn btn-danger btn-accent">Đặt lại mật khẩu</button>
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
<script src="assets/js/resetPassword.js"></script>
</body>
</html>
