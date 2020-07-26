<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/error.css">
    <title>Lỗi</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="error-section">
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h2 class="mb-0">Trang được yêu cầu không hợp lệ hoặc không còn tồn tại.</h2>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp" />
</div>

<jsp:include page="components/globalScripts.jsp" />
</body>
</html>
