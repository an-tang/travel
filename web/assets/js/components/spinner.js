'use strict';

function startSpinner() {
    $('body').prepend(`
    <div class="spinner-container">
        <div class="spinner-border text-danger" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    `);
}

function stopSpinner() {
    $('.spinner-container').remove();
}
