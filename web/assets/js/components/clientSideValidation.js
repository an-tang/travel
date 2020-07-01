'use strict';

function validateForm(event) {
    let valid = true;

    if (this.checkValidity && !this.checkValidity()) {
        valid = false;

        if (event) {
            event.preventDefault();
            event.stopPropagation();
            event.stopImmediatePropagation();
        }

        $(this).find('input, select, textarea').each(function () {
            if (!this.validity.valid) {
                $(this).trigger('invalid');
            }
        });
    }

    return valid;
}

function handleFieldInvalid() {
    $('form input, form select, form textarea').on('invalid', function (e) {
        e.preventDefault();
        this.setCustomValidity('');

        if (!this.validity.valid) {
            let self = $(this);
            let validationMessage = this.validationMessage;

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
            self.addClass('is-invalid');
        }
    });
}

function clearInvalid() {
    $('form button[type="submit"]').on('click', function () {
        $(this)
            .parents('form')
            .find('.is-invalid')
            .removeClass('is-invalid');
    });
}
