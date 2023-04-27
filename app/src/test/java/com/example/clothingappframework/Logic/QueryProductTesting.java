package com.example.clothingappframework.Logic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import clothingapp.business.QueryProducts;
import clothingapp.objects.Product;
import clothingapp.persistence.DBController;

public class QueryProductTesting {

    @Before
    public void setup() {
        System.out.println("Initiating test for QueryTesting");
    }

    @Test
    public void queryTest()
    {
        DBController.createController(true);

        QueryProducts query = new QueryProducts();
        ArrayList<Product> products = query.getProducts();

        assert(query.getProducts().size() > 0);
        assert(query.getProduct(products.get(0).getDocId()) != null);
        assert(query.getProduct(null) == null);

        DBController.closeController();
    }
}

