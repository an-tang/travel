'use strict';

$(document).ready(function () {
    prepareWishlistTours();
});

function prepareWishlistTours() {
    startSpinner();
    const jsonTourIDs = window.localStorage.getItem('tourIDs');
    const tourIDs = jsonTourIDs ? JSON.parse(jsonTourIDs) : [];

    $.ajax({
        url: '/wishlist',
        type: 'post',
        dataType: 'html',
        data: { tourIDs: tourIDs }
    })
        .done(response => {
            stopSpinner();
            if (response) {
                $('#wishlistTours').append(response);
            }
        });
}
