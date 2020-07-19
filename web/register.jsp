<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/register.css">
    <title>Đăng ký</title>
</head>

<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="register-form-section">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <h2 class="text-center mb-4">Đăng ký</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3 col-xl-4"></div>
                <div class="col-md-6 col-xl-4">
                    <form
                            action="/register"
                            class="register"
                            method="post"
                            data-general-error="Invalid field. Please check your input again."
                            data-request-error="Request to server unexpectedly failed. Please try again later.">

                        <div class="form-group required">
                            <label class="form-control-label" for="registerUsername">Tên đăng nhập</label>
                            <input
                                    type="text"
                                    id="registerUsername"
                                    class="form-control"
                                    name="user"
                                    required pattern="^[\w]{6,18}$"
                                    data-missing-error="Vui lòng nhập tên đăng nhập"
                                    data-pattern-mismatch="Tên đăng nhập dài 6-18 ký tự và không được chứa ký tự đặc biệt">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group required">
                            <label class="form-control-label" for="registerPassword">Mật khẩu</label>
                            <input
                                    type="password"
                                    id="registerPassword"
                                    class="form-control"
                                    name="pwd"
                                    required minlength="6"
                                    data-missing-error="Vui lòng nhập mật khẩu"
                                    data-range-error="Mật khẩu phải dài tối thiểu 6 ký tự">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group required">
                            <label class="form-control-label" for="registerConfirmPassword">Xác nhận mật khẩu</label>
                            <input
                                    type="password"
                                    id="registerConfirmPassword"
                                    class="form-control"
                                    name="pwd_confirm"
                                    required
                                    data-missing-error="Vui lòng xác nhận mật khẩu"
                                    data-password-mismatch="Mật khẩu không trùng khớp">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label" for="registerName">Tên</label>
                            <input
                                    type="text"
                                    id="registerName"
                                    class="form-control"
                                    name="name"
                                    maxlength="50"
                                    data-range-error="Tên không dài quá 50 ký tự">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group required">
                            <label class="form-control-label" for="registerPhone">Số điện thoại</label>
                            <input
                                    type="text"
                                    id="registerPhone"
                                    class="form-control"
                                    name="phone"
                                    required pattern="((?=(0))[0-9]{10})$"
                                    data-missing-error="Vui lòng nhập số điện thoại"
                                    data-pattern-mismatch="Vui lòng sử dụng số điện thoại hợp lệ">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group">
                            <label class="form-control-label" for="registerEmail">Email</label>
                            <input
                                    type="email"
                                    id="registerEmail"
                                    class="form-control"
                                    name="email"
                                    pattern="^[\w.%+-]+@[\w.-]+\.[\w]{2,6}$"
                                    data-pattern-mismatch="Vui lòng sử dụng email hợp lệ">
                            <div class="invalid-msg"></div>
                        </div>

                        <div class="form-group text-center mb-0">
                            <button type="submit" name="register" class="btn btn-danger btn-accent">Đăng ký</button>
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
<script src="assets/js/register.js"></script>

</body>

</html>
