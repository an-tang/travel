'use strict';

$(document).ready(function () {
    initBannerCarousel();
    processTourDetail();
});

function initBannerCarousel() {
    let bannerSlider = $('.tour-banner-slider');
    if (!bannerSlider.length) return;
    const setting = {
        autoplay: true,
        autoplaySpeed: 5000,
        pauseOnHover: false,
        slidesToShow: 1,
        slidesToScroll: 1,
        infinite: true,
        arrows: false,
        dots: true,
        lazyLoad: 'ondemand'
    };
    bannerSlider.slick(setting);
}

function processTourDetail() {
    let tourDetail = $('.tour-detail');
    const text = tourDetail.text();
    const html = text.replace(/(?:\\[rn])+/g, '<br />').replace('<br />', '');
    tourDetail.html(html);
}
