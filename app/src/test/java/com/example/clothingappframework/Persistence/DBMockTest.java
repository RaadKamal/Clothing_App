package com.example.clothingappframework.Persistence;

import org.junit.Test;

import java.util.ArrayList;
import clothingapp.objects.Product;
import clothingapp.persistence.DBMock;

import static org.junit.Assert.assertTrue;

public class DBMockTest {

    private DBMock dbMock;

    @Test
    public void testMock() {
        dbMock = new DBMock();
        ArrayList<Product> products = dbMock.getProducts();

        assert(dbMock.getProducts() != null);
        assert(dbMock.getProducts().size() > 0);
        assert(dbMock.getProducts() instanceof ArrayList);

        assert(dbMock.getProduct(products.get(0).getDocId()) instanceof Product );
        assert(dbMock.getProduct(null) == null );

        assertTrue(dbMock.open(""));
        assertTrue(dbMock.close());
    }
}