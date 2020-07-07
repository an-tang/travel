package com.travel.viewmodel;

public class Checkout {
    String qrText;
    int tourID;
    float amount;
    String tourName;
    int status;


    public Checkout(String qrText, int tourID, float amount, String tourName, int status) {
        this.qrText = qrText;
        this.tourID = tourID;
        this.amount = amount;
        this.tourName = tourName;
        this.status = status;
    }

    public String getQrText() {
        return qrText;
    }

    public void setQrText(String qrText) {
        this.qrText = qrText;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Checkout{" +
                "qrText='" + qrText + '\'' +
                ", tourID=" + tourID +
                ", amount=" + amount +
                ", tourName='" + tourName + '\'' +
                ", status=" + status +
                '}';
    }
}
