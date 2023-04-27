package clothingapp.application;

import clothingapp.persistence.DBController;

public class Main {
    public static boolean useStub = false; //Setting this to true uses the Mock db instead of hsqldb
    public static final String dbName = "store";
    private static String dbPathName = "db/store";

    public static void start(){
        DBController.createController(useStub);
    }

    public static void stop(){ DBController.closeController(); }

    public static void setStub(boolean stub){useStub = stub;}

    public static String getDBPathName(){return dbPathName;}

    public static void setDBPathName(String pathName) {
        System.out.println("Setting DB path to: " + pathName);
        dbPathName = pathName;
    }
}
