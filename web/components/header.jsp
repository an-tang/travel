<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    HttpSession currentSession = request.getSession(false);
    boolean isAuthenticated = currentSession != null && currentSession.getAttribute("authenticatedUser") != null;
%>
<header class="header">
    <div class="row">
        <div class="col">
            <div class="header_container d-flex flex-row align-items-center justify-content-start">
                <!-- Logo -->
                <div class="logo_container">
                    <a href="/">
                        <div class="logo">
                            <div>UIT</div>
                            <div>travel</div>
                            <div class="logo_image"><img src="assets/images/logo.png" alt=""></div>
                        </div>
                    </a>
                </div>

                <!-- Main Navigation -->
                <nav class="main_nav ml-auto">
                    <ul class="main_nav_list">
                        <c:choose>
                            <c:when test="<%=isAuthenticated%>">
                                <li class="main_nav_item">
                                    <a href="/account">Xin chào, <%=currentSession.getAttribute("authenticatedUser")%></a>
                                </li>
                                <li class="main_nav_item">
                                    <a href="/logout">Đăng xuất</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="main_nav_item">
                                    <a href="/login">Đăng nhập</a>
                                </li>
                                <li class="main_nav_item">
                                    <a href="/register">Đăng ký</a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>

                <!-- Search -->
                <div class="search">
                    <form action="/search" method="get" class="search_form">
                        <input type="search" name="q" class="search_input ctrl_class" required="required" placeholder="Từ khóa">
                        <div class="search_button ml-auto ctrl_class"><img src="assets/images/search.png" alt="">
                        </div>
                    </form>
                </div>

                <!-- Hamburger -->
                <div class="hamburger ml-auto">
                    <i class="fa fa-bars" aria-hidden="true"></i>
                </div>
            </div>
        </div>
    </div>
</header>
