'use strict';

function handleAdminSearchInputChange(callback) {
    $('form.admin-quick-search input').on('change', function () {
        const searchQuery = $(this).val().trim();
        if(searchQuery && searchQuery.length >= 2) {
            callback.call($('form.admin-quick-search')[0]);
        }
    });
}

/* Handle admin search */
function handleAdminSearchSubmit(callback) {
    $('form.admin-quick-search').on('submit', function (e) {
        e.preventDefault();

        let input = $(this).find('input');
        const searchQuery = input.val().trim();
        if (!searchQuery) {
            alert('Vui lòng nhập từ khóa hợp lệ.');
        }
        input.val(searchQuery);
        callback.call(this);
    });
}
