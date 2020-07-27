'use strict';

$(document).ready(function () {
    handleAdminSearchInputChange();
    handleAdminSearchSubmit();
});

function fetchAdminEntries() {
    let form = $(this);
    let getEntriesURL = new URL(form.attr('action'), location.origin);
    getEntriesURL.searchParams.append('q', form.find('input[name="q"]').val().trim());

    $.ajax({
        url: getEntriesURL.toString(),
        type: 'get',
        dataType: 'html',
    })
        .done(response => {
            $('table tbody').empty().html(response.trim());
        });
}

function handleAdminSearchInputChange() {
    $('form.admin-quick-search input').on('change', function () {
        const searchQuery = $(this).val().trim();
        if(searchQuery.length !== 1) {
            fetchAdminEntries.call($(this).parents('form.admin-quick-search')[0]);
        }
    });
}

function handleAdminSearchSubmit() {
    $('form.admin-quick-search').on('submit', function (e) {
        e.preventDefault();

        let input = $(this).find('input');
        const searchQuery = input.val().trim();
        if (searchQuery.length >= 2) {
            fetchAdminEntries.call(this);
        } else {
            alert('Vui lòng nhập từ khóa hợp lệ.');
        }
    });
}
