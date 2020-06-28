'use strict';

$(document).ready(function () {
    handleFieldInvalid();
    handleFormSubmit();
    handleSubmitButtonClick();
});

function validateForm(event) {
    let valid = true;

    if (this.checkValidity && !this.checkValidity()) {
        valid = false;

        if (event) {
            event.preventDefault();
            event.stopPropagation();
            event.stopImmediatePropagation();
        }

        $(this).find('input').each(function () {
            if (!this.validity.valid) {
                $(this).trigger('invalid');
            }
        });
    }

    return valid;
}

function clearInvalid(form) {
    form.find('.form-control.is-invalid').removeClass('is-invalid');
}

function handleFieldInvalid() {
    $('form.login input').on('invalid', function (e) {
        e.preventDefault();

        if (!this.validity.valid) {
            let validationMessage = this.validationMessage;
            let self = $(this);
            self.addClass('is-invalid');

            if (this.validity.patternMismatch && self.data('pattern-mismatch')) {
                validationMessage = self.data('pattern-mismatch');
            }

            if ((this.validity.rangeOverflow || this.validity.rangeUnderflow) && self.data('range-error')) {
                validationMessage = self.data('range-error');
            }

            if ((this.validity.tooLong || this.validity.tooShort) && self.data('range-error')) {
                validationMessage = self.data('range-error');
            }

            if (this.validity.valueMissing && self.data('missing-error')) {
                validationMessage = self.data('missing-error');
            }

            self.parents('.form-group').find('.invalid-msg').text(validationMessage);
        }
    });
}

function handleFormSubmit() {
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
                        const responseJSON = JSON.parse(error.responseJSON);
                        alert(responseJSON.errorMessage);
                    } else {
                        alert(form.data('request-error'));
                    }
                });
        }
    });
}

function handleSubmitButtonClick() {
    $('form.login button[type="submit"]').on('click', function () {
        clearInvalid($(this).parents('form.login'));
    });
}
