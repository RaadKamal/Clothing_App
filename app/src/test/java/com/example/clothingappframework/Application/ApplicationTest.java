package com.example.clothingappframework.Application;

import android.content.Context;

import org.hsqldb.Database;
import org.junit.Test;

import clothingapp.application.Main;
import clothingapp.persistence.DBController;
import clothingapp.persistence.util.DatabaseCopier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

public class ApplicationTest {

    @Test
    public void mainTest() {
        Main.setDBPathName("");
        assert(Main.getDBPathName().equals(""));

        Main.setStub(true);

        Main.start();
        assert(DBController.getController() != null);

        Main.stop();
        assert(DBController.getController() == null);
    }
}