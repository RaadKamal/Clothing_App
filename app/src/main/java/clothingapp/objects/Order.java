package clothingapp.objects;

import java.util.Date;

public class Order {
    String paymentID;
    String paymentCreated;
    int amount;
    String currency;

    public Order(String paymentID, String paymentCreated, int amount, String currency){
        this.paymentID = paymentID;
        this.paymentCreated = paymentCreated;
        this.amount = amount;
        this.currency = currency;
    }

    public String getPaymentID() { return paymentID; }
    public String getPaymentCreated() { return paymentCreated; }
    public int getAmount() { return amount; }
    public String getCurrency() { return currency; }

    public void setPaymentID(String paymentID) {this.paymentID = paymentID;}
    public void setPaymentCreated(String paymentCreated) { this.paymentCreated = paymentCreated; }
    public void setAmount(int amount) { this.amount = amount; }
    public void setCurrency(String currency) { this.currency = currency; }
}
