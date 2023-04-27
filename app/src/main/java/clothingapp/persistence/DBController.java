package clothingapp.persistence;

import clothingapp.application.Main;

public class DBController {
    private static DBInterface dataController;

    public static DBInterface createController(boolean useStub){
        if(useStub){
            dataController = new DBMock();
        }
        else{
            if(dataController == null){
                dataController = new DBObject();
                dataController.open(Main.getDBPathName());
            }
        }
        return dataController;
    }

    public static DBInterface getController(){return dataController;}

    public static void closeController(){
        if(dataController != null)
            dataController.close();
            dataController = null;
    }
}
