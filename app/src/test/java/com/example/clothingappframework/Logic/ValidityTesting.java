package com.example.clothingappframework.Logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import clothingapp.business.QueryProducts;
import clothingapp.business.Validity;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;
import clothingapp.persistence.DBController;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidityTesting {

    @Test
    public void queryTest()
    {
        DBController.createController(true);

        QueryProducts query = new QueryProducts();
        ArrayList<Product> products = query.getProducts();
        Product product = products.get(0);
        Product nullProduct = new Product(null, null, null, null, null, null);

        assertTrue(Validity.productValid(product));
        assertFalse(Validity.productValid(nullProduct));

        Product errorProduct = new Product("Jeans", "It keeps you looking cool!", "Pants", "test/path/header3.png",
            new String[]{"Color", "Color"},
            new Variant[]{
                new Variant(true, "test/path/file.png", 55.99,
                    new HashMap<String, Boolean>() {{
                        put("LOL", false);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Blue");
                        put("TEST", "Large");
                    }}),
                new Variant(true, "test/path/file.png", 55.99,
                    new HashMap<String, Boolean>() {{
                        put("LOL", false);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Blue");
                        put("TEST", "Large");
                    }}),
                new Variant(true, "test/path/file.png", 54.99,
                    new HashMap<String, Boolean>() {{
                        put("Pickup", true);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Gray");
                        put("Color", "Medium");
                        put("Color", "Large");
                    }}
                )
            }
        );

        assertFalse(Validity.productValid(errorProduct));

        Product errorProduct2 = new Product("Jeans", "It keeps you looking cool!", "Pants", "test/path/header3.png",
            new String[]{"Color", null},
            new Variant[]{
                new Variant(true, "test/path/file.png", 55.99,
                    new HashMap<String, Boolean>() {{
                        put("LOL", false);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Blue");
                        put("TEST", "Large");
                    }}),
                new Variant(true, "test/path/file.png", 55.99,
                    new HashMap<String, Boolean>() {{
                        put("LOL", false);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Blue");
                        put("TEST", "Large");
                    }}),
                new Variant(true, "test/path/file.png", 54.99,
                    new HashMap<String, Boolean>() {{
                        put("Pickup", true);
                    }},
                    new HashMap<String, String>() {{
                        put("Color", "Gray");
                        put("Color", "Medium");
                        put("Color", "Large");
                    }}
                )
            }
        );

        DBController.closeController();
    }
}

