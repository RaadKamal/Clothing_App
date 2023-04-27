package clothingapp.business;

import java.util.ArrayList;
import java.util.UUID;

import clothingapp.objects.Order;
import clothingapp.objects.Product;
import clothingapp.persistence.DBController;
import clothingapp.persistence.DBInterface;

public class QueryOrders {
    private DBInterface db;

    public QueryOrders(){
        db = DBController.getController();
    }

    public ArrayList<Order> getOrders() {
        return db.getOrders();
    }
    public Order getOrder(String paymentID){return db.getOrder(paymentID);}
    public void insertOrder(Order order){ db.insertOrder(order); };
}
