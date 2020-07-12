package com.travel.ajax;

import org.json.simple.JSONObject;

public class AjaxResponse {
    protected boolean success;
    protected String message;
    protected String redirectUrl;

    public AjaxResponse() {
        this.success = false;
        this.message = null;
        this.redirectUrl = null;
    }

    public AjaxResponse(boolean success, String message, String redirectUrl) {
        this.success = success;
        this.message = message;
        this.redirectUrl = redirectUrl;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public boolean isEmpty() {
        return !this.success && this.message == null && this.redirectUrl == null;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        if (!this.isEmpty()) {
            jsonObject.put("success", this.success);
            jsonObject.put("message", this.message);
            jsonObject.put("redirectUrl", this.redirectUrl);
        }
        return jsonObject;
    }

    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
