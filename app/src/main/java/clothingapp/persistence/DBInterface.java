package clothingapp.persistence;

import clothingapp.objects.Order;
import clothingapp.objects.Product;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public interface DBInterface {
    ArrayList<Product> getProducts();
    ArrayList<Order> getOrders();

    Product getProduct(UUID docID);
    Order getOrder(String paymentID);

    void insertOrder(Order order);

    boolean open(String dbPath);

    boolean close();
}
