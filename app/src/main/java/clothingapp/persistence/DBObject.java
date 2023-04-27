package clothingapp.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import clothingapp.objects.Order;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;

public class DBObject implements DBInterface {

    private static String dbType = "REAL";
    private Connection connection;
    private Statement statement;
    private DBMock db;

    public DBObject(){
        connection = null;
    }

    public boolean open(String dbPath) {
        String url;
        boolean connectionSuccessful;

        try {
            Class.forName("org.hsqldb.jdbcDriver").newInstance();

            url = "jdbc:hsqldb:file:" + dbPath + ";shutdown=true;readonly=true";
            connection = DriverManager.getConnection(url, "SA", "");
            connectionSuccessful= true;

        } catch (Exception e) {
            connectionSuccessful = false;
            e.printStackTrace(System.out);
        }

        return connectionSuccessful;
    }

    public boolean close(){
        boolean connectionClosed;

        if(connection != null){
            try {
                Statement statement = connection.createStatement();
                statement.execute("SHUTDOWN COMPACT");
                connection.close();
                System.out.println("CLOSED CONNECTION TO DB");
                connectionClosed = true;
            }catch (SQLException e) {
                connectionClosed = false;
                e.printStackTrace();
            }
        }
        else{
            connectionClosed = false;
        }

        return connectionClosed;
    }

    public ArrayList<Product> getProducts(){
        ArrayList<Product> products = new ArrayList<Product>();

        if(products.size() == 0){
            if(connection != null){
                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS");
                    ResultSet results = statement.executeQuery();

                    while (results.next()) {
                        products.add(new Product(
                            results.getString("DOCID"),
                            results.getString("NAME"),
                            results.getString("DESCRIPTION"),
                            results.getString("CATEGORY"),
                            "",
                            new String[]{"Color", "Size"},
                            new Variant[]{
                                new Variant(true, "test/path/file.png", 27.99,
                                    new HashMap<String, Boolean>() {{
                                        put("Pickup", true);
                                        put("Delivery", false);
                                    }},
                                    new HashMap<String, String>() {{
                                        put("Color", "Red");
                                        put("Size", "Large");
                                    }}),
                                new Variant(true, "test/path/file.png", 75.99,
                                    new HashMap<String, Boolean>() {{
                                        put("Pickup", true);
                                        put("Delivery", false);
                                    }},
                                    new HashMap<String, String>() {{
                                        put("Color", "Blue");
                                        put("Size", "Medium");
                                    }}
                                )
                            }
                        ));
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;
    }

    public Product getProduct(UUID docID) {
        Product returnProduct = null;
        boolean productFound = false;

        System.err.println(docID.toString());
        if(connection != null){
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM PRODUCTS WHERE DOCID = '"+docID.toString()+"'");
                ResultSet results = statement.executeQuery();

                results.next();

                returnProduct = new Product(
                    results.getString("DOCID"),
                    results.getString("NAME"),
                    results.getString("DESCRIPTION"),
                    results.getString("CATEGORY"),
                    "",
                    new String[]{"Color", "Size"},
                    new Variant[]{
                        new Variant(true, "test/path/file.png", 27.99,
                            new HashMap<String, Boolean>() {{
                                put("Pickup", true);
                                put("Delivery", false);
                            }},
                            new HashMap<String, String>() {{
                                put("Color", "Red");
                                put("Size", "Large");
                            }}),
                        new Variant(true, "test/path/file.png", 75.99,
                            new HashMap<String, Boolean>() {{
                                put("Pickup", true);
                                put("Delivery", false);
                            }},
                            new HashMap<String, String>() {{
                                put("Color", "Blue");
                                put("Size", "Medium");
                            }}
                        )
                    }
                );

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.err.println(returnProduct.getName());
        return returnProduct;
    }

    public ArrayList<Order> getOrders(){
        ArrayList<Order> orders = new ArrayList<Order>();

        if(orders.size() == 0){
            if(connection != null){
                try {
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS");
                    ResultSet results = statement.executeQuery();

                    while (results.next()) {
                        orders.add(new Order(
                            results.getString("PAYMENT_ID"),
                            results.getString("PAYMENT_CREATED"),
                            results.getInt("AMOUNT"),
                            results.getString("CURRENCY")));
                    };
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return orders;
    }

    public Order getOrder(String paymentID){
        Order returnOrder = null;
        boolean orderFound = false;

        if(connection != null){
            try {
                PreparedStatement statement = connection.prepareStatement("SELECT * FROM ORDERS WHERE PAYMENT_ID='"+paymentID.toString()+"'");
                ResultSet results = statement.executeQuery();

                results.next();

                returnOrder = new Order(
                    results.getString("PAYMENT_ID"),
                    results.getString("PAYMENT_CREATED"),
                    results.getInt("AMOUNT"),
                    results.getString("CURRENCY"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return returnOrder;
    }

    public void insertOrder(Order order){
        if(connection != null){
            try {
                Statement statement = connection.createStatement();
                int result = statement.executeUpdate("INSERT INTO ORDERS VALUES (`"+order.getPaymentID()+"`,`"+order.getPaymentCreated()+"`+"+order.getAmount()+"`"+order.getCurrency()+"`)");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
