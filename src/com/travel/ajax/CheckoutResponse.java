package com.travel.ajax;

import com.travel.viewmodel.Checkout;
import org.json.simple.JSONObject;

public class CheckoutResponse extends AjaxResponse {
    private Checkout checkout;

    public CheckoutResponse(boolean success, String message, String redirectUrl, Checkout checkout) {
        super(success, message, redirectUrl);
        this.checkout = checkout;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public void setCheckout(Checkout checkout) {
        this.checkout = checkout;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject checkoutJSONObject = new JSONObject();
        checkoutJSONObject.put("qrText", this.checkout.getQrText());
        checkoutJSONObject.put("tourID", this.checkout.getTourID());
        checkoutJSONObject.put("amount", this.checkout.getAmount());
        checkoutJSONObject.put("tourName", this.checkout.getTourName());
        checkoutJSONObject.put("status", this.checkout.getStatus());

        JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("checkout", checkoutJSONObject);
        return jsonObject;
    }
}
