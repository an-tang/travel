'use strict';

$(document).ready(function () {
    prepareWishlistTours();
});

function prepareWishlistTours() {
    const jsonTourIDs = window.localStorage.getItem('tourIDs');
    const tourIDs = jsonTourIDs ? JSON.parse(jsonTourIDs) : [];

    $.ajax({
        url: '/wishlist',
        type: 'post',
        dataType: 'html',
        data: { tourIDs: tourIDs }
    })
        .done(response => {
            if (response) {
                $('#wishlistTours').append(response);
            }
        });
}
