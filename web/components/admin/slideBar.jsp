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
<html>

<head>


</head>


<body class="fix-header fix-sidebar card-no-border">
<div id="main-wrapper">
    <!-- Topbar header - style you can find in pages.scss -->
    <!-- ============================================================== -->
    <header class="topbar">
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
    <!-- ============================================================== -->
    <!-- End Topbar header -->
    <!-- ============================================================== -->
    <!-- ============================================================== -->
    <!-- Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->
    <aside class="left-sidebar">
        <!-- Sidebar scroll-->
        <div class="scroll-sidebar">
            <!-- Sidebar navigation-->
            <nav class="sidebar-nav">
                <ul id="sidebarnav">
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-gauge"></i><span class="hide-menu">Dashboard</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-account-check"></i><span class="hide-menu">Profile</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-table"></i><span class="hide-menu">Users</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-emoticon"></i><span class="hide-menu">Orders</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-earth"></i><span class="hide-menu">Tours</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-book-open-variant"></i><span class="hide-menu">Comment</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-help-circle"></i><span class="hide-menu">Feedback</span></a>
                    </li>
                    <li><a class="waves-effect waves-dark" href="" aria-expanded="false"><i
                            class="mdi mdi-help-circle"></i><span class="hide-menu">Report</span></a>
                    </li>
                </ul>
            </nav>
            <!-- End Sidebar navigation -->
        </div>
        <!-- End Sidebar scroll-->
        <!-- Bottom points-->
        <div class="sidebar-footer">
            <!-- item--><a href="" class="link" data-toggle="tooltip" title="Settings"><i
                class="ti-settings"></i></a>
            <!-- item--><a href="" class="link" data-toggle="tooltip" title="Email"><i
                class="ti-email"></i></a>
            <!-- item--><a href="" class="link" data-toggle="tooltip" title="Logout"><i
                class="ti-power-off"></i></a>
        </div>
        <!-- End Bottom points-->
    </aside>
    <!-- ============================================================== -->
    <!-- End Left Sidebar - style you can find in sidebar.scss  -->
    <!-- ============================================================== -->

</div>

</body>
</html>
