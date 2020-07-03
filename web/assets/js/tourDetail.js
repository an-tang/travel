'use strict';

$(document).ready(function () {
    initBannerCarousel();
    processTourDescription();
    handleBookTour();
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
        arrows: true,
        dots: true,
        lazyLoad: 'ondemand'
    };
    bannerSlider.slick(setting);
}

function processTourDescription() {
    let tourDetail = $('.tour-detail');
    const text = tourDetail.text();
    const html = text.replace(/(?:\\[rn])+/g, '<br />').replace('<br />', '');
    tourDetail.html(html);
}

function handleBookTour() {
    $('#bookThisTour').on('click.bookThisTour', function () {
        let self = $(this);
        $.ajax({
            url: self.data('action'),
            type: 'post',
            dataType: 'json',
            data: { checkoutTourId: self.data('tour-id') }
        })
            .done(data => {
                if (!data.success) {
                    alert(data.message);
                }
                window.location.href = data.redirectUrl;
            })
            .fail(error => {
                if (error.responseJSON) {
                    const responseObj = JSON.parse(error.responseJSON);
                    alert(responseObj.errorMessage);
                } else {
                    alert(self.data('request-error'));
                }
            });
    });
}
