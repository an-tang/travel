'use strict';

$(document).ready(function () {
    handleFieldInvalid();
    clearInvalid();
    handleLoginFormSubmit();
});

function handleLoginFormSubmit() {
    $('form.login').on('submit', function (e) {
        e.preventDefault();
        const valid = validateForm.call(this, e);

        if (valid) {
            let form = $(this);
            $.ajax({
                url: form.attr('action'),
                type: 'post',
                dataType: 'json',
                data: form.serialize()
            })
                .done(data => {
                    if (data.success) {
                        window.location.href = data.redirectUrl;
                    } else {
                        alert(data.message);
                    }
                })
                .fail(error => {
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
