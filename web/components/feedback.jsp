<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-md">
        <div class="modal-content">
            <form
                action="/feedback"
                class="feedback"
                method="post"
                data-request-error="Request to server unexpectedly failed. Please try again later.">

                <div class="modal-header">
                    <h5 class="modal-title" id="feedbackModalLabel">Feedback</h5>
                </div>
                <div class="modal-body">
                    <div class="form-group required">
                        <input
                            type="text"
                            id="senderName"
                            class="form-control"
                            name="sender"
                            placeholder="Tên"
                            required
                            data-missing-error="Vui lòng nhập tên">
                        <div class="invalid-msg"></div>
                    </div>
                    <div class="form-group required">
                        <input
                            type="email"
                            id="senderEmail"
                            class="form-control"
                            name="email"
                            placeholder="Email"
                            required pattern="^[\w.%+-]+@[\w.-]+\.[\w]{2,6}$"
                            data-missing-error="Vui lòng nhập email"
                            data-pattern-mismatch="Vui lòng sử dụng email hợp lệ">
                        <div class="invalid-msg"></div>
                    </div>
                    <div class="form-group required">
                        <input
                            type="text"
                            id="feedbackTitle"
                            class="form-control"
                            name="feedback_title"
                            placeholder="Tiêu đề"
                            required
                            data-missing-error="Vui lòng nhập tiêu đề">
                        <div class="invalid-msg"></div>
                    </div>
                    <div class="form-group required">
                        <textarea
                            id="feedbackContent"
                            class="form-control"
                            name="feedback_content"
                            placeholder="Nội dung"
                            rows="5"
                            required maxlength="300"
                            data-missing-error="Vui lòng nhập nội dung feedback"
                            data-range-error="Feedback không dài quá 300 ký tự"></textarea>
                        <div class="invalid-msg"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Hủy</button>
                    <button id="sendFeedback" type="submit" class="btn btn-danger btn-accent">Gửi feedback</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="feedback-button">
    <button id="feedbackBtn" class="btn btn-secondary">Feedback</button>
</div>
