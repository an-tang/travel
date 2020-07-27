'use strict';

$(document).ready(function () {
    handleAdminSearchInputChange(fetchAdminTours);
    handleAdminSearchSubmit(fetchAdminTours);
});

function fetchAdminTours() {
    let form = $(this);
    let getCommentsURL = new URL('/admin-tour-search', location.origin);
    getCommentsURL.searchParams.append('q', form.find('input[name="q"]').val().trim());

    $.ajax({
        url: getCommentsURL.toString(),
        type: 'get',
        dataType: 'html',
    })
        .done(response => {
            $('#tourDetailTable tbody').empty().html(response.trim());
        });
}
