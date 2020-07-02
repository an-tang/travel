package com.travel.ajax;

import org.json.simple.JSONObject;

public class AjaxResponse {
    private boolean success;
    private String message;
    private String redirectUrl;

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

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("success", this.success);
        jsonObject.put("message", this.message);
        jsonObject.put("redirectUrl", this.redirectUrl);
        return jsonObject;
    }

    public String toJSONString() {
        return this.toJSONObject().toJSONString();
    }
}
