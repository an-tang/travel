<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/wishlist.css">
    <title>Tour yêu thích</title>
</head>
<body>
<div class="super_container">
    <!-- Header -->
    <jsp:include page="components/header.jsp" />

    <!-- Menu -->
    <jsp:include page="components/menu.jsp" />

    <section class="wishlist-section">
        <div class="container">
            <div class="row pb-4">
                <div class="col-12">
                    <h2>Tour yêu thích</h2>
                </div>
            </div>
            <div id="wishlistTours" class="row"></div>
        </div>
    </section>

    <!-- Footer -->
    <jsp:include page="components/footer.jsp" />
</div>

<jsp:include page="components/globalScripts.jsp" />
<script src="assets/js/wishlist.js"></script>
</body>
</html>
