'use strict';

$(document).ready(function () {
    handleFieldInvalid();
    clearInvalid();
    handleRegisterFormSubmit();
});

function handleRegisterFormSubmit() {
    $('form.register').on('submit', function (e) {
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
                    if (data.success) {
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
