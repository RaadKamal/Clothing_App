package clothingapp.business;

import org.json.JSONException;
import org.json.JSONObject;

import clothingapp.objects.Order;

public class OrderHandler {
    public void generateOrder(String squarePayload) throws JSONException {
        JSONObject jsonPayload = new JSONObject(squarePayload);
        JSONObject payment = jsonPayload.getJSONObject("payment");
        JSONObject amountMoney = jsonPayload.getJSONObject("amount_money");

        String paymentID = payment.getString("id");
        String dateCreated = payment.getString("created_at");
        int price = amountMoney.getInt("amount");
        String currency = amountMoney.getString("currency");

        Order order = new Order(paymentID, dateCreated, price, currency);

    }
}
