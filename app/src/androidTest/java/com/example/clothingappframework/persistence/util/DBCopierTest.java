package com.example.clothingappframework.persistence.util;

import android.content.Context;

import org.junit.Before;
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
public class DBCopierTest {

    private DBObject dbObject;

    @Before
    public void setup(){System.out.println("Begin DBCopierTest");}

    @Test
    public void testCopier() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertTrue(DatabaseCopier.copyDatabaseToDevice(appContext));
        assertFalse(DatabaseCopier.copyDatabaseToDevice(null));
    }
}