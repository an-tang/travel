'use strict';

$(document).ready(function () {
    let bannerSlider = $('.home-banner-slider');
    if (!bannerSlider.length) return;

    const setting = {
        autoplay: true,
        autoplaySpeed: 5000,
        pauseOnHover: false,
        slidesToShow: 1,
        slidesToScroll: 1,
        infinite: true,
        arrows: true,
        dots: true,
        lazyLoad: 'ondemand'
    };

    bannerSlider.slick(setting);
});
