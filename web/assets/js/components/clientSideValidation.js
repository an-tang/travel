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
    } else {
        let pwdInput = $('form input[name="pwd"], form input[name="new_pwd"]');
        let pwdConfirmInput = $('form input[name="pwd_confirm"], form input[name="new_pwd_confirm"]');

        if (
            pwdInput.length
            && pwdConfirmInput.length
            && pwdInput.val() !== pwdConfirmInput.val()
        ) {
            valid = false;

            if (event) {
                event.preventDefault();
                event.stopPropagation();
                event.stopImmediatePropagation();
            }

            pwdConfirmInput.trigger('invalid', { passwordMismatch: true });
        }
    }

    return valid;
}

function handleFieldInvalid() {
    $('form input, form select, form textarea').on('invalid', function (e, customValidationData) {
        e.preventDefault();

        this.setCustomValidity('');
        let self = $(this);
        let validationMessage = this.validationMessage || self.parents('form').data('general-error');

        if (!this.validity.valid) {
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
        } else if (customValidationData) {
            if (customValidationData.passwordMismatch && self.data('password-mismatch')) {
                validationMessage = self.data('password-mismatch');
            }
        }

        self.parents('.form-group').find('.invalid-msg').text(validationMessage);
        self.addClass('is-invalid');
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
