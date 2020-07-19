'use strict';

$(document).ready(function () {
    handleFieldInvalid();
    clearInvalid();
    handleProfileFormSubmit();
    handleChangePwdFormSubmit();
});

function handleProfileFormSubmit() {
    $('form.profile').on('submit.profile', function (e) {
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
                    alert(data.message);
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

function handleChangePwdFormSubmit() {
    $('form.change-password').on('submit.changePassword', function (e) {
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
                    alert(data.message);
                    if (data.redirectUrl) {
                        window.location.href = data.redirectUrl;
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
