<%@ page import="com.travel.bean.ImageBean" %>
<%@ page import="com.travel.bean.TourInfoBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    TourInfoBean tourInfo = (TourInfoBean) request.getAttribute("tourInfo");
    ArrayList<ImageBean> tourImages = tourInfo.getImages();
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <title id="title-header">Thông tin tour</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="UIT project">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="WebContent/vendor/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="WebContent/vendor/font-awesome-4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="WebContent/vendor/slick/slick.css">
    <link rel="stylesheet" type="text/css" href="WebContent/vendor/slick/slick-theme.css">
    <link rel="stylesheet" type="text/css" href="WebContent/css/about_styles.css">
    <link rel="stylesheet" type="text/css" href="WebContent/css/about_responsive.css">
</head>

<body>

    <div class="super_container">

        <!-- Header -->
        <p id="id" hidden="">14</p>
        <header class="header">
            <div class="container">
                <div class="row">
                    <div class="col">
                        <div class="header_container d-flex flex-row align-items-center justify-content-start">

                            <!-- Logo -->
                            <div class="logo_container">
                                <div class="logo">
                                    <div>UIT</div>
                                    <div>travel</div>
                                    <div class="logo_image"><img src="images/logo.png" alt=""></div>
                                </div>
                            </div>

                            <!-- Main Navigation -->
                            <nav class="main_nav ml-auto">
                                <ul class="main_nav_list">
                                    <li class="main_nav_item"><a href="/">Trang chủ</a></li>
                                    <li class="main_nav_item"><a href="contact">Liên hệ</a></li>
                                </ul>
                            </nav>

                            <!-- Search -->
                            <div class="search">
                                <form action="#" class="search_form">
                                    <input type="search" name="search_input" class="search_input ctrl_class" required="required" placeholder="Từ khóa">
                                    <div class="search_button ml-auto ctrl_class"><img src="images/search.png" alt="">
                                    </div>
                                </form>
                            </div>

                            <!-- Hamburger -->
                            <div class="hamburger ml-auto"><i class="fa fa-bars" aria-hidden="true"></i></div>

                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- Menu -->

        <div class="menu_container menu_mm">

            <!-- Menu Close Button -->
            <div class="menu_close_container">
                <div class="menu_close"></div>
            </div>

            <!-- Menu Items -->
            <div class="menu_inner menu_mm">
                <div class="menu menu_mm">
                    <div class="menu_search_form_container">
                        <form action="#" id="menu_search_form">
                            <input type="search" class="menu_search_input menu_mm">
                            <div id="menu_search_submit" class="menu_search_submit"><img src="images/search_2.png" alt=""></div>
                        </form>
                    </div>
                    <ul class="menu_list menu_mm">
                        <li class="menu_item menu_mm"><a href="/">Trang chủ</a></li>
                        <li class="menu_item menu_mm"><a href="contact">Liên hệ </a></li>
                    </ul>

                    <!-- Menu Social -->

                    <div class="menu_social_container menu_mm">
                        <ul class="menu_social menu_mm">
                            <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                            </li>
                            <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                            </li>
                            <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                            </li>
                            <li class="menu_social_item menu_mm"><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                        </ul>
                    </div>
                </div>

            </div>

        </div>

        <section class="home-banner">
            <div class="home-banner-slider">
                <div class="home-banner-slide">
                    <picture>
                        <source srcset="https://development-asia-samsonite.demandware.net/on/demandware.static/-/Library-Sites-TumiSharedLibrary/default/dwe72dac69/images/homepage/banner/19-Degree-Polycarbonate-homepage-banner-Mobile-750x1000px.jpg" media="(max-width: 767px)">
                        <img src="https://development-asia-samsonite.demandware.net/on/demandware.static/-/Library-Sites-TumiSharedLibrary/default/dw800131aa/images/homepage/banner/19-Degree-Polycarbonate-homepage-banner-Desktop-1920x672.jpg" alt="">
                    </picture>
                    <div class="banner-text m-top d-right">
                        <a href="https://development-asia-samsonite.demandware.net/s/Tumi_KR/collection/collection-tumi/collection-19-degree-polycarbonate/">
                            <h4 class="eng-text-semibold">19 Degree Polycarbonate</h4>
                            <h3 class="eng-text">Cutting-edge Reimagination</h3>
                            <div class="button-more eng-text-bold">Shop Now</div>
                        </a>
                    </div>
                </div>

                <div class="home-banner-slide">
                    <picture>
                        <source srcset="https://development-asia-samsonite.demandware.net/on/demandware.static/-/Library-Sites-TumiSharedLibrary/default/dwa3d76357/images/homepage/banner/Homepage-Banner-Mobile.jpg" media="(max-width: 767px)">
                        <img data-lazy="https://development-asia-samsonite.demandware.net/on/demandware.static/-/Library-Sites-TumiSharedLibrary/default/dwedab4449/images/homepage/banner/Homepage-Banner-Desktop.jpg" alt="">
                    </picture>
                    <div class="banner-text m-top d-left">
                        <a href="https://development-asia-samsonite.demandware.net/s/Tumi_KR/collection/collection-tumi/collection-harrison/">
                            <h4 class="eng-text-semibold">Perfecting the Journey – The Series</h4>
                            <h3 class="eng-text">Daniel Henney on Acting</h3>
                            <h3 class="eng-text">Featuring the Harrison Collection</h3>
                            <div class="button-more eng-text-bold">Shop Now</div>
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <!-- About -->

        <!-- Page Content -->
        <div class="container">

            <div class="row">

                <!-- Post Content Column -->
                <div class="col-lg-8">

                    <!-- Title -->
                    <h1 id="post-title" class="mt-4">Post Title</h1>

                    <!-- Author -->
                    <p class="lead">
                        Giá
                        <p id="price"></p>
                    </p>

                    <hr>

                    <!-- Date/Time -->
                    <p>Posted on January 1, 2019 at 12:00 PM</p>

                    <hr>

                    <!-- Preview Image -->
                    <div class="container">
                        <!--Carousel Wrapper-->
                        <div id="carousel-example-2" class="carousel slide carousel-fade z-depth-1-half" data-ride="carousel">
                            <!--Indicators-->
                            <ol id="indicator" class="carousel-indicators">
                            </ol>
                            <!--/.Indicators-->
                            <!--Slides-->
                            <div id="list-image" class="carousel-inner" role="listbox">
                            </div>
                            <!--Controls-->
                            <a class="carousel-control-prev" href="#carousel-example-2" role="button" data-slide="prev">
                                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                <span class="sr-only">Previous</span>
                            </a>
                            <a class="carousel-control-next" href="#carousel-example-2" role="button" data-slide="next">
                                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                <span class="sr-only">Next</span>
                            </a>
                            <!--/.Controls-->
                        </div>
                        <!--/.Carousel Wrapper-->
                    </div>


                    <!-- Post Content -->
                    <p id="content" class="font-weight-normal">Lorem ipsum dolor sit amet, consectetur adipisicing elit.
                        Ducimus,
                        vero, obcaecati, aut, error quam sapiente nemo saepe quibusdam sit excepturi nam quia corporis
                        eligendi eos
                        magni recusandae laborum minus inventore?
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ut, tenetur natus doloremque laborum
                        quos iste
                        ipsum rerum obcaecati impedit odit illo dolorum ab tempora nihil dicta earum fugiat. Temporibus,
                        voluptatibus.

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Eos, doloribus, dolorem iusto
                        blanditiis unde eius
                        illum consequuntur neque dicta incidunt ullam ea hic porro optio ratione repellat perspiciatis.
                        Enim, iure!

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error, nostrum, aliquid, animi, ut
                        quas placeat
                        totam sunt tempora commodi nihil ullam alias modi dicta saepe minima ab quo voluptatem
                        obcaecati?

                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Harum, dolor quis. Sunt, ut,
                        explicabo, aliquam
                        tenetur ratione tempore quidem voluptates cupiditate voluptas illo saepe quaerat numquam
                        recusandae? Qui,
                        necessitatibus, est!</p>

                    <hr>
                    <div class="modal fade" id="modalSubscriptionForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
                      aria-hidden="true">
                      <div class="modal-dialog" role="document">
                        <div class="modal-content">
                          <div class="modal-header text-center">
                            <h4 class="modal-title w-100 font-weight-bold">Your Information</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                              <span aria-hidden="true">&times;</span>
                            </button>
                          </div>
                          
                          <div class="modal-body mx-3">
                            <div class="md-form mb-5">
                              <i class="fa fa-info-circle" aria-hidden="true"></i>
                              <label data-error="wrong" data-success="right" for="form3">Your name</label>
                              <input type="text"  name="name" class="form-control validate" id="orderName">
                            </div>

                            <div class="md-form mb-4">
                              <i class="fa fa-phone-square" aria-hidden="true"></i>
                              <label data-error="wrong" data-success="right" for="form2">Your Phone Number</label>
                              <input type="Number"  name="phone" class="form-control validate" id="orderPhone">         
                            </div>

                            <div class="md-form mb-4">
                              <i class="fa fa-address-book" aria-hidden="true"></i>
                              <label data-error="wrong" data-success="right" for="form2">Your Address</label>
                              <input type="text"  name="adress" class="form-control validate" id="orderAddress">          
                            </div>

                            <div class="md-form mb-4">
                              <i class="fa fa-id-card-o" aria-hidden="true"></i>
                              <label data-error="wrong" data-success="right" for="form2">Your Passenger</label>
                              <input type="Number" name="passengers" class="form-control validate" id="orderPassenger">         
                            </div>

                            <div class="md-form mb-4">
                              <i class="fa fa-file-text" aria-hidden="true"></i>
                              <label data-error="wrong" data-success="right" for="form2">Decription</label>
                              <input type="text"  name="decription" class="form-control validate" id="orderDecription">       
                            </div>

                            
                          </div>
                          <div class="modal-footer d-flex justify-content-center">
                            <button onclick="summit()" class="btn btn-success">Send <i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                          </div>
                        </div>
                      </div>
                    </div>

<div class="text-center">
  <a href="" class="btn btn-danger btn-rounded mb-4" data-toggle="modal" data-target="#modalSubscriptionForm">Đặt tour</a>
</div>
                    <!-- Comments Form -->
                    <div class="card my-4">
                        <h5 class="card-header">Leave a Comment:</h5>
                        <div class="card-body">
                            	<div class="form-group">
                                    <input class="form-control" id="commentName" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" rows="3" id="commentContent" placeholder="Content"></textarea>
                                </div>
                                <button onclick="submitComment()" class="btn btn-primary" type="button">Submit</button>
                            
                        </div>
                    </div>

                    <!-- Single Comment -->
                    <div id="areaComment">

                    </div>

                    

                </div>

                <!-- Sidebar Widgets Column -->
                <div class="col-md-4">

                    <!-- Categories Widget -->
                    <div class="card my-4">
                        <h5 class="card-header">Một Số Trang Tổ Chức Tour</h5>
                        <div class="card-body">
                            <div class="row">
                                <div class="col-lg-6">
                                    <ul class="list-unstyled mb-0">
                                        <li>
                                            <a href="https://www.saigontourist.net/tour-trong-nuoc">SaiGonTourist</a>
                                        </li>
                                        <li>
                                            <a href="https://dulichviet.com.vn/du-lich-trong-nuoc">Du Lịch Việt</a>
                                        </li>
                                        <li>
                                            <a href="https://www.vietravel.com/">Việt Travel</a>
                                        </li>
                                    </ul>
                                </div>
                                <div class="col-lg-6">
                                    <ul class="list-unstyled mb-0">
                                        <li>
                                            <a href="https://www.ivivu.com/du-lich">Ivivu</a>
                                        </li>
                                        <li>
                                            <a href="https://datviettour.com.vn/du-lich-trong-nuoc">Đất Việt Tour</a>
                                        </li>
                                        <li>
                                            <a href="https://travel.com.vn/">Travel</a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- Side Widget -->
                    <div class="card my-4">
                        <h5 class="card-header">Side Widget</h5>
                        <div class="card-body">
                            You can put anything you want inside of these side widgets. They are easy to use, and
                            feature the new
                            Bootstrap 4 card containers!
                        </div>
                    </div>

                </div>

            </div>
            <!-- /.row -->

        </div>
        <!-- /.container -->

        <!-- Footer -->

        <footer class="footer">
            <div class="container">
                <div class="row">

                    <!-- Footer Column -->
                    <div class="col-lg-4 footer_col">
                        <div class="footer_about">
                            <!-- Logo -->
                            <div class="logo_container">
                                <div class="logo">
                                    <div>UIT</div>
                                    <div>travel</div>
                                    <div class="logo_image"><img src="WebContent/images/logo.png" alt=""></div>
                                </div>
                            </div>
                            <div class="footer_about_text">Trang thông tin du lịch trong nước.</div>
                        </div>
                    </div>

                    <!-- Footer Column -->
                    <div class="col-lg-4 footer_col">

                    </div>

                    <!-- Footer Column -->
                    <div class="col-lg-4 footer_col">
                        <div class="tags footer_tags">
                            <div class="footer_title">Tags</div>
                            <ul class="tags_content d-flex flex-row flex-wrap align-items-start justify-content-start">
                                <li class="tag"><a href="#">du lịch</a></li>
                                <li class="tag"><a href="#">mùa hè</a></li>
                                <li class="tag"><a href="#">nổi tiếng</a></li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
        </footer>
    </div>

    <script src="WebContent/vendor/jquery/jquery-3.5.1.min.js"></script>
    <script src="WebContent/vendor/slick/slick.min.js"></script>
    <script src="WebContent/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="WebContent/vendor/greensock/TweenMax.min.js"></script>
    <script src="WebContent/vendor/greensock/TimelineMax.min.js"></script>
    <script src="WebContent/vendor/scrollmagic/ScrollMagic.min.js"></script>
    <script src="WebContent/vendor/greensock/animation.gsap.min.js"></script>
    <script src="WebContent/vendor/greensock/ScrollToPlugin.min.js"></script>
    <script src="WebContent/vendor/easing/easing.js"></script>
    <script src="WebContent/vendor/parallax-js-master/parallax.min.js"></script>
    <script src="WebContent/js/tourDetail.js"></script>
</body>

</html>
