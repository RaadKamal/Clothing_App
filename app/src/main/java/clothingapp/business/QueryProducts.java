package clothingapp.business;

import clothingapp.objects.Product;
import clothingapp.persistence.DBController;
import clothingapp.persistence.DBInterface;

import java.util.ArrayList;
import java.util.UUID;

public class QueryProducts {
    private DBInterface db;

    public QueryProducts(){
        db = DBController.getController();
    }

    public ArrayList<Product> getProducts() {
        return db.getProducts();
    }
    public Product getProduct(UUID docID){return db.getProduct(docID);}
} 
        
    

    