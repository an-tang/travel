'use strict';

$(document).ready(function () {
    initParallax();
    initIsotope();
    handleShowMoreClick();
    handleSortOptionChange();
});

/* Init Parallax */
function initParallax() {
    // Add parallax effect to every element with class prlx
    // Add class prlx_parent to the parent of the element
    if ($('.prlx_parent').length && $('.prlx').length) {
        var elements = $('.prlx_parent');

        elements.each(function () {
            var ele = this;
            var bcg = $(ele).find('.prlx');

            var slideParallaxScene = new ScrollMagic.Scene({
                triggerElement: ele,
                triggerHook: 1,
                duration: "200%"
            })
                .setTween(TweenMax.from(bcg, 1, { y: '-30%', ease: Power0.easeNone }))
                .addTo(ctrl);
        });
    }
}

/* Init Isotope */
function initIsotope() {
    var sortingButtons = $('.item_sorting_btn');

    if ($('.item_grid').length) {
        var grid = $('.item_grid').isotope({
            itemSelector: '.item',
            getSortData:
            {
                price: function (itemElement) {
                    var priceEle = $(itemElement).find('.item_price').text().replace('From $', '');
                    return parseFloat(priceEle);
                },
                name: '.item_title',
                stars: function (itemElement) {
                    var starsEle = $(itemElement).find('.rating');
                    var stars = starsEle.attr("data-rating");
                    return stars;
                }
            },
            animationOptions:
            {
                duration: 750,
                easing: 'linear',
                queue: false
            }
        });

        // Sort based on the value from the sorting_type dropdown
        sortingButtons.each(function () {
            $(this).on('click', function () {
                var parent = $(this).parent().parent().find('.sorting_text');
                parent.text($(this).text());
                var option = $(this).attr('data-isotope-option');
                option = JSON.parse(option);
                grid.isotope(option);
            });
        });

        // Filtering
        $('.item_filter_btn').on('click', function () {
            var parent = $(this).parent().parent().find('.sorting_text');
            parent.text($(this).text());
            var filterValue = $(this).attr('data-filter');
            grid.isotope({ filter: filterValue });
        });

        // Change view to Box
        if ($('.box_view').length) {
            var box = $('.box_view');
            box.on('click', function () {
                if (window.innerWidth > 767) {
                    $('.item').addClass('box');
                    var option = '{ "sortBy": "original-order" }';
                    option = JSON.parse(option);
                    grid.isotope(option);
                }
            });
        }

        // Change view to List
        if ($('.detail_view').length) {
            var detail = $('.detail_view');
            detail.on('click', function () {
                if (window.innerWidth > 767) {
                    $('.item').removeClass('box');
                    var option = '{ "sortBy": "original-order" }';
                    option = JSON.parse(option);
                    grid.isotope(option);
                    setTimeout(function () {
                        grid.isotope(option);
                    }, 500);
                }
            });
        }
    }
}

function fetchResults(url, isReload) {
    $.ajax({
        url: url,
        type: 'get',
        dataType: 'html'
    })
        .done(response => {
            if (typeof response === 'string' && response) {
                if (isReload) {
                    $('.tour-listing-row').empty().append(response);
                } else {
                    $('.show-more').replaceWith(response);
                }
                $('.show-more .btn').on('click.showMoreResults', function () {
                    fetchResults($(this).data('url'), false);
                });
            }
        });
}

function handleSortOptionChange() {
    $('#sortOptions').on('change.sortOptions', function () {
        const url = $(this).val();
        fetchResults(url, true);
    });
}

function handleShowMoreClick() {
    $('.show-more .btn').on('click.showMoreResults', function () {
        const url = $(this).data('url');
        fetchResults(url, false);
    });
}
