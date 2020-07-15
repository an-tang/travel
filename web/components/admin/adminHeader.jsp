<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.travel.helper.SessionHelpers" %>
<%
    HttpSession currentSession = request.getSession(false);
    boolean isAuthenticated = SessionHelpers.validateSession(currentSession);
    String authenticatedName = (String) currentSession.getAttribute("authenticatedName");
    String authenticatedUser = (String) currentSession.getAttribute("authenticatedUser");
    String loginReplacementURL = (String) request.getAttribute("loginReplacementURL");
%>
<header class="topbar" >
    <nav class="navbar top-navbar navbar-toggleable-sm navbar-light">
        <!-- ============================================================== -->
        <!-- Logo -->
        <!-- ============================================================== -->
        <div class="navbar-header">
            <a class="navbar-brand" href="index.html">
                <!-- Logo icon --><b>
                <!--You can put here icon as well // <i class="wi wi-sunset"></i> //-->

                <!-- Light Logo icon -->
                <img src="/assets/images/logo.png" alt="homepage" aria-valuetext="asd" class="light-logo"/>
                UIT
                travel

            </b>
                <!--End Logo icon -->
                <!-- Logo text --><span>

                         <!-- Light Logo text -->
                </span> </a>
        </div>
        <!-- ============================================================== -->
        <!-- End Logo -->
        <!-- ============================================================== -->
        <div class="navbar-collapse">
            <!-- ============================================================== -->
            <!-- toggle and nav items -->
            <!-- ============================================================== -->
            <ul class="navbar-nav mr-auto mt-md-0">
                <!-- This is  -->
                <li class="nav-item"><a
                        class="nav-link nav-toggler hidden-md-up text-muted waves-effect waves-dark"
                        href="javascript:void(0)"><i class="mdi mdi-menu"></i></a></li>
                <!-- ============================================================== -->
                <!-- Search -->
                <!-- ============================================================== -->
                <li class="nav-item hidden-sm-down search-box"><a
                        class="nav-link hidden-sm-down text-muted waves-effect waves-dark"
                        href="javascript:void(0)"><i class="ti-search"></i></a>
                    <form class="app-search">
                        <input type="text" class="form-control" placeholder="Search & enter"> <a
                            class="srh-btn"><i
                            class="ti-close"></i></a></form>
                </li>
            </ul>
            <!-- ============================================================== -->
            <!-- User profile and search -->
            <!-- ============================================================== -->
            <nav class="main_nav ml-auto">
                <ul class="main_nav_list" style="list-style: none; height: 100%">
                    <c:choose>
                        <c:when test="<%=isAuthenticated%>">
                            <div style="display: flex; height: 100%; align-items: center; justify-content: space-between; width: 250px">

                                <li class="main_nav_item">
                                    <a class="btn waves-effect waves-light btn-warning hidden-md-down"
                                       id="headerAccountLink"
                                       href="/account">
                                        <span>Xin chào, </span>
                                        <c:choose>
                                            <c:when test="<%=authenticatedName != null%>">
                                                <span><em><%=authenticatedName%></em></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span><em><%=authenticatedUser%></em></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                                <li class="main_nav_item">
                                    <a class="btn waves-effect waves-light btn-warning hidden-md-down"
                                       id="headerLogoutLink"
                                       href="/logout">
                                        Đăng xuất
                                    </a>
                                </li>
                            </div>
                        </c:when>
                    </c:choose>
                </ul>
            </nav>
        </div>
    </nav>
</header>
