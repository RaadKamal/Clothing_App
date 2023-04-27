package com.example.clothingappframework.persistence;

import android.content.Context;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import clothingapp.application.Main;
import clothingapp.objects.Product;
import clothingapp.persistence.DBMock;
import clothingapp.persistence.DBObject;
import clothingapp.persistence.util.DatabaseCopier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DBObjectTest {

    private DBObject dbObject;

    @Test
    public void testMock() {
        dbObject = new DBObject();
        ArrayList<Product> products = dbObject.getProducts();

        assert(dbObject.getProducts() != null);
        assert(dbObject.getProducts().size() == 0);
        assert(dbObject.getProducts() instanceof ArrayList);

        assert(dbObject.getProduct(null) == null );

        assertFalse(dbObject.open(""));
        assertFalse(dbObject.close());

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        DatabaseCopier.copyDatabaseToDevice(appContext);

        assertTrue(dbObject.open(Main.getDBPathName()));
        assertTrue(dbObject.getProducts().size() > 0);
        assertTrue(dbObject.getProduct(dbObject.getProducts().get(0).getDocId()) instanceof Product);
        assertTrue(dbObject.getProduct(dbObject.getProducts().get(0).getVariants()[0].getDocId()) instanceof Product);
        assertTrue(dbObject.getProduct(dbObject.getProducts().get(0).getVariants()[0].getDocId()) instanceof Product);
        assertTrue(dbObject.close());
    }
}