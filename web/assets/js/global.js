'use strict';

$(document).ready(function () {
    setHeader();
    $(window).on('resize', setHeader);
    $(document).on('scroll', setHeader);

    initHamburger();
    initSpecialSlider();
    initSearch();
    handleSearchFormSubmit();
    handleLoginLinkClick();
});

/* Set Header */
function setHeader() {
    let header = $('header.header');

    if ($(window).scrollTop() > header.height() + 30) {
        header.addClass('scrolled');
    } else {
        header.removeClass('scrolled');
    }

    if (window.innerWidth > 991 && isMenuActive()) {
        closeMenu();
    }
}

/* Initialize Hamburger */
function initHamburger() {
    if ($('.hamburger').length) {
        $('.hamburger').on('click', function (event) {
            event.stopPropagation();
            let menuContainer = $('.menu_container');

            if (!isMenuActive()) {
                openMenu();

                $(document).one('click', function cls(e) {
                    if ($(e.target).hasClass('menu_mm')) {
                        $(document).one('click', cls);
                    } else {
                        closeMenu();
                    }
                });
            } else {
                menuContainer.removeClass('active');
            }
        });
    }
}

function isMenuActive() {
    return $('.menu_container').hasClass('active');
}

function openMenu() {
    $('.menu_container').addClass('active');
}

function closeMenu() {
    $('.menu_container').removeClass('active');
}

/* Set Header */
function initSpecialSlider() {
    if ($('.special_slider').length) {
        var specialSlider = $('.special_slider');
        specialSlider.owlCarousel({
            loop: true,
            autoplay: false,
            center: true,
            stagePadding: 190,
            margin: 5,
            nav: false,
            dots: false,
            smartSpeed: 700,
            responsive: {
                0: {
                    items: 1,
                    margin: 5,
                    stagePadding: 0
                },
                992: {
                    items: 2,
                    margin: 5,
                    stagePadding: 130
                },
                1280: {
                    items: 3,
                    margin: 5,
                    stagePadding: 190
                }
            }
        });
    }

    if ($('.special_slider_nav').length) {
        var next = $('.special_slider_nav');
        next.on('click', function () {
            specialSlider.trigger('next.owl.carousel');
        });
    }
}

/* Init Search */
function initSearch() {
    if ($('.search').length) {
        var search = $('.search');
        search.on('click', function (e) {
            var target = $(e.target);
            if (!target.hasClass('ctrl_class')) {
                $(this).toggleClass('active');
            }
        });
    }
}

/* Handle search form */
function handleSearchFormSubmit() {
    $('.search_form').on('submit', function (e) {
        let input = $(this).find('input');
        const searchQuery = input.val().trim();
        if (!searchQuery) {
            alert('Vui lòng nhập từ khóa hợp lệ.');
            return false;
        }
        input.val(searchQuery);
        return true;
    });
}

/* Handle click login link */
function handleLoginLinkClick() {
    $('#headerLoginLink').on('click', function (e) {
        const replacementHref = $(this).data('replacement-href');
        if (replacementHref) {
            window.location.href = replacementHref;
            return false;
        }
        return true;
    });
}
