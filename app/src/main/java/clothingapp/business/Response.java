package clothingapp.business;

import java.util.HashMap;
import java.util.Map;

public class Response {
    private HashMap<String, Throwable> errorMap;

    public Response(){
        errorMap = new HashMap<>();
    }

    public void pushError(String tag, Throwable e){errorMap.put(tag, e);}

    public boolean hasError() { return errorMap.size() > 0;}
    public HashMap<String, Throwable> getErrorMap(){return errorMap;}

    public void generateErrors(){
          for(Map.Entry<String, Throwable> error : errorMap.entrySet()){
              System.err.print(error.getKey() + " ERROR: ");
              error.getValue().printStackTrace();
          }
    }
}
