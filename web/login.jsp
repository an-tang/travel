<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="components/globalHeadTags.jsp" />
	<link rel="stylesheet" type="text/css" href="assets/css/login.css">
	<title>Đăng nhập</title>
</head>

<body>
<div class="super_container">
	<!-- Header -->
	<jsp:include page="components/header.jsp" />

	<!-- Menu -->
	<jsp:include page="components/menu.jsp" />

	<section class="login-form-section">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<h2 class="section-title text-center mb-4">Đăng nhập</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3 col-xl-4"></div>
				<div class="col-md-6 col-xl-4">
					<form
						action="/login"
						class="login"
						method="post"
						data-general-error="Invalid field. Please check your input again."
						data-request-error="Request to server unexpectedly failed. Please try again later.">
						<div class="form-group required">
							<label class="form-control-label" for="loginUsername">Tên đăng nhập</label>
							<input
									type="text"
									id="loginUsername"
									class="form-control"
									name="user"
									required pattern="^[\w]{6,18}$"
									data-missing-error="Vui lòng nhập tên đăng nhập"
									data-pattern-mismatch="Tên đăng nhập dài 6-18 ký tự và không được chứa ký tự đặc biệt">
							<div class="invalid-msg"></div>
						</div>
						<div class="form-group required">
							<label class="form-control-label" for="loginPassword">Mật khẩu</label>
							<input
									type="password"
									id="loginPassword"
									class="form-control"
									name="pwd"
									required
									data-missing-error="Vui lòng nhập mật khẩu">
							<div class="invalid-msg"></div>
						</div>
						<div class="form-group text-center mb-0">
							<button type="submit" name="login" class="btn btn-danger btn-accent">Đăng nhập</button>
						</div>
						<input type="hidden" id="loginRedirectUrl" name="redirect_url" value="<%=request.getAttribute("redirectUrl")%>">
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
<script src="assets/js/login.js"></script>

</body>

</html>
