'use strict';

$(document).ready(function () {
    handleFieldInvalid();
    clearInvalid();
    handleForgotPasswordFormSubmit();
});

function handleForgotPasswordFormSubmit() {
    $('form.forgot-password').on('submit', function (e) {
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
                    if (data.success) {
                        alert(data.message);
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
