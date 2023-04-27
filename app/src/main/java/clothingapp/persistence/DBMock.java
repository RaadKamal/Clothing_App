package clothingapp.persistence;


import clothingapp.objects.Order;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class DBMock implements DBInterface {
    private static ArrayList<Product> productsTable;
    private static String dbType = "MOCK";

    public DBMock() {

        if (productsTable == null) {

            productsTable = new ArrayList<Product>();

            // Define Product 1
            productsTable.add(new Product(UUID.randomUUID().toString(),"Beanie Hat", "It keeps you warm!", "Hats", "test/path/header1.png",
                new String[]{"Color", "Size"},
                new Variant[]{
                    new Variant(true, "test/path/file.png", 24.99,
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

            // Define Product 2
            productsTable.add(new Product(UUID.randomUUID().toString(),"T-Shirt", "It keeps you cool!", "Shirts", "test/path/header2.png",
                new String[]{"# per Pack", "Color", "Size"},
                new Variant[]{
                    new Variant(true, "test/path/file.png", 71.99,
                        new HashMap<String, Boolean>() {{
                            put("Pickup", false);
                            put("Delivery", false);
                        }},
                        new HashMap<String, String>() {{
                            put("# per Pack", "3");
                            put("Color", "Red");
                            put("Size", "Large");
                        }}),
                    new Variant(true, "test/path/file.png", 72.99,
                        new HashMap<String, Boolean>() {{
                            put("Pickup", true);
                            put("Delivery", true);
                        }},
                        new HashMap<String, String>() {{
                            put("# per Pack", "3");
                            put("Color", "Gray");
                            put("Size", "Medium");
                        }}
                    )
                }
            ));

            // Define Product 3
            productsTable.add(new Product(UUID.randomUUID().toString(),"Jeans", "It keeps you looking cool!", "Pants", "test/path/header3.png",
                    new String[]{"Color", "Color"},
                    new Variant[]{
                            new Variant(true, "test/path/file.png", 55.99,
                                    new HashMap<String, Boolean>() {{
                                        put("Pickup", false);
                                        put("Delivery", false);
                                    }},
                                    new HashMap<String, String>() {{
                                        put("Color", "Blue");
                                        put("Size", "Large");
                                    }}),
                            new Variant(true, "test/path/file.png", 54.99,
                                    new HashMap<String, Boolean>() {{
                                        put("Pickup", true);
                                        put("Delivery", true);
                                    }},
                                    new HashMap<String, String>() {{
                                        put("Color", "Gray");
                                        put("Size", "Medium");
                                    }}
                            )
                    }
            ));
        }
    }

    public ArrayList<Product> getProducts() {return productsTable;}

    @Override
    public ArrayList<Order> getOrders() { return null; }

    @Override
    public boolean open(String dbPath) {return true;}

    @Override
    public boolean close() {return true;}

    @Override
    public Product getProduct(UUID docID) {
        Product returnProduct = null;
        boolean productFound = false;

        if(docID != null){
            for(int i = 0; i < productsTable.size() && !productFound; i++) {
                Product currentProduct = productsTable.get(i);
                Variant[] currentVariants = currentProduct.getVariants();

                if(currentProduct.getDocId().compareTo(docID) == 0){
                    returnProduct = currentProduct;
                    productFound = true;
                }

                for (int j = 0; j < currentVariants.length && !productFound; j++) {
                    if (currentVariants[j].getDocId().compareTo(docID) == 0) {
                        returnProduct = currentProduct;
                        productFound = true;
                    }
                }
            }
        }

        return returnProduct;
    }

    @Override
    public Order getOrder(String paymentID) { return null; }

    @Override
    public void insertOrder(Order order) { }
}