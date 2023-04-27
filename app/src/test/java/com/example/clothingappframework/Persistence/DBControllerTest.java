package com.example.clothingappframework.Persistence;

import org.junit.Test;
import clothingapp.persistence.DBController;
import clothingapp.persistence.DBInterface;
import clothingapp.persistence.DBMock;
import clothingapp.persistence.DBObject;

public class DBControllerTest {

    @Test
    public void testStub() {
        boolean useStub = true;
        DBController.createController(useStub);
        DBInterface controller = DBController.getController();

        assert(controller instanceof DBMock);
        assert(controller != null);
        assert(DBController.getController() instanceof DBInterface);
        assert(DBController.getController() != null);

        DBController.closeController();
        assert(DBController.getController() == null);
    }

    @Test
    public void testRealDB(){
        boolean useStub = false;
        DBController.createController(useStub);
        DBInterface controller = DBController.getController();

        assert(controller instanceof DBObject);
        assert(controller != null);
        assert(DBController.getController() instanceof DBInterface);
        assert(DBController.getController() != null);

        DBController.closeController();
        assert(DBController.getController() == null);
    }
}