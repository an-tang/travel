<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="components/globalHeadTags.jsp" />
    <link rel="stylesheet" type="text/css" href="assets/css/provinceListingPage.css">
    <title>Tất cả tỉnh thành</title>
</head>

<body>
    <div class="super_container">
        <!-- Header -->
        <jsp:include page="components/header.jsp"/>

        <!-- Menu -->
        <jsp:include page="components/menu.jsp"/>

        <!-- Home -->
        <jsp:include page="components/search/searchBanner.jsp"/>

        <!-- Footer -->
        <section class="province-listing-section"></section>

        <!-- Footer -->
        <jsp:include page="components/footer.jsp"/>
    </div>

    <jsp:include page="components/globalScripts.jsp"/>
</body>
</html>
