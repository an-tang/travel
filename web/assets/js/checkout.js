'use strict';

$(document).ready(function () {
    handleChangePassengers();
    handleFieldInvalid();
    clearInvalid();
    handleSubmitCheckoutForm();
});

function handleChangePassengers() {
    $('#quantity').on('change.qty', function () {
        const quantity = parseInt($(this).val(), 10);

        $('#subtotalPrice, #totalPrice').each(function () {
            let self = $(this);
            const unitPrice = parseInt(self.data('price'), 10);
            const calculatedPrice = unitPrice * quantity;
            const displayPrice = calculatedPrice.toLocaleString() + 'đ';
            self.text(displayPrice);
        });
    });
}

function placeOrder() {
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
                window.location.href = data.redirectUrl;
            } else {
                alert(data.message);
                if (data.redirectUrl) {
                    window.location.href = data.redirectUrl;
                }
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

function showPlaceOrderModal(formElement) {
    const modalHTML = `
    <div class="modal fade" id="placeOrderModal" tabindex="-1" role="dialog" aria-labelledby="placeOrderModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="placeOrderModalLabel">Xác nhận đặt tour</h5>
                </div>
                <div class="modal-body">
                    <p>Để đặt tour này bạn hãy chuyển khoản số tiền <span class="modal-total-price">${$('#totalPrice').text()}</span> vào tài khoản ngân hàng của chúng tôi và nhấn nút <b>Đặt tour</b>.</p>
                    <p class="mb-0"><em>Thông tin TK ngân hàng:</em></p>
                    <div class="pl-4">
                        <p class="mb-0">Ngân hàng: <b>Vietcombank - CN Bắc Sài Gòn</b></p>
                        <p class="mb-0">Tên chủ TK: <b>Nguyễn Văn Duy</b></p>
                        <p class="mb-0">Số TK: <b>025 100 100 8003</b></p>
                        <p>Nội dung chuyển khoản bao gồm <b>họ tên</b>, <b>số điện thoại</b> của bạn và <b>tên tour</b> mà bạn đặt.</p>
                    </div>
                    <p class="mb-0">Chúng tôi sẽ liên hệ với bạn trong vòng <b>12-24 giờ</b> kể từ khi đơn hàng được tạo thành công.</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button id="confirmPlaceOrder" type="button" class="btn btn-danger btn-accent">Đặt tour</button>
                </div>
            </div>
        </div>
    </div>`;

    $('#placeOrderModal').remove();
    $('body').append(modalHTML);
    $('#confirmPlaceOrder').off('click').on('click.confirmPlaceOrder', placeOrder.bind(formElement));
    $('#placeOrderModal').modal('show');
}

function handleSubmitCheckoutForm() {
    $('form.checkout').on('submit.placeOrder', function (e) {
        e.preventDefault();

        const valid = validateForm.call(this, e);
        if (valid) {
            const payment = $('input[name="payment"]:checked').val();
            if (payment === '1') {
                placeOrder.call(this);
            } else if (payment === '2') {
                showPlaceOrderModal(this);
            }
        }
    });
}
