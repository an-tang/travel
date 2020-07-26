'use strict';

const COMMENTS_DEFAULT_START = 0;
const COMMENTS_PER_PAGE = 10;

$(document).ready(function () {
    // Prepare tour details
    initBannerCarousel();
    processMultiLineText('.tour-detail');
    handleBookTour();
    handleAddToWishlist();

    // Prepare comment form
    handleFieldInvalid();
    clearInvalid();
    handleCommentFormSubmit();

    // Fetch comments
    fetchComments(null);
});

function processMultiLineText(selector) {
    $(selector).each(function () {
        let item = $(this);
        const itemHtml = item.text().replace(/(?:\\[rn])+/g, '<br />');
        item.html(itemHtml);
    });
}

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

function handleAddToWishlist() {
    $('#addToWishlist').on('click.addToWishlist', function () {
        const tourID = parseInt($(this).data('tour-id'), 10);
        const jsonTourIDs = window.localStorage.getItem('tourIDs');
        let tourIDs = jsonTourIDs ? JSON.parse(jsonTourIDs) : [];
        let message;

        if (!tourIDs.includes(tourID)) {
            tourIDs.push(tourID);
            window.localStorage.setItem('tourIDs', JSON.stringify(tourIDs));
            message = 'Thêm tour vào danh sách yêu thích thành công\n'
                + 'Để xem danh sách tour yêu thích hãy vào mục: Tài khoản > Tour yêu thích';
        } else {
            message = 'Tour đã tồn tại trong danh sách yêu thích';
        }
        alert(message);
    });
}

function fetchComments(url) {
    startSpinner();
    let getCommentsURLString = url;
    let isReloadComments = false;

    if (!url) {
        let getCommentsURL = new URL('/comment', location.origin);
        getCommentsURL.searchParams.append('tour_info', $('form.comment input[name="tour_info"]').val());
        getCommentsURL.searchParams.append('start', COMMENTS_DEFAULT_START.toString());
        getCommentsURL.searchParams.append('size', COMMENTS_PER_PAGE.toString());
        getCommentsURLString = getCommentsURL.toString();
        isReloadComments = true;
    }

    $.ajax({
        url: getCommentsURLString,
        type: 'get',
        dataType: 'html'
    })
        .done(response => {
            stopSpinner();
            if (typeof response === 'string' && response) {
                if (isReloadComments) {
                    $('.comments').empty().append(response);
                } else {
                    $('.show-more').replaceWith(response);
                }
                processMultiLineText('.comment-card .card-body p');
                $('.show-more .btn').on('click.showMoreComments', function () {
                    fetchComments($(this).data('url'));
                });
            }
        });
}

function handleCommentFormSubmit() {
    $('form.comment').on('submit', function (e) {
        e.preventDefault();
        const valid = validateForm.call(this, e);

        if (valid) {
            startSpinner();
            let form = $(this);
            $.ajax({
                url: form.attr('action'),
                type: 'post',
                dataType: 'json',
                data: form.serialize()
            })
                .done(data => {
                    stopSpinner();
                    alert(data.message);
                    if (data.success) {
                        fetchComments(null);
                    } else if (data.redirectUrl) {
                        window.location.href = data.redirectUrl;
                    }
                })
                .fail(error => {
                    stopSpinner();
                    if (error.responseJSON) {
                        const responseObj = JSON.parse(error.responseJSON);
                        alert(responseObj.errorMessage);
                    } else {
                        alert(form.data('request-error'));
                    }
                });
        }
    });
}
