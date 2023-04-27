package clothingapp.objects;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

/**
 * Variant.java
 * Group: Group 8
 * COMP 3350, A03
 *
 * Defines Product Variants
 */
public class Variant {
    private UUID docId;                         //Assigning docID's to UUIDs allows client to have functionally infinite products on store.
    private Boolean inStock;
    private String photoURL;                    //Absolute path to a photo, used to generate images on store.
    private double price;
    private Map<String, Boolean> shipOptions;   //Defines if a product is available for pickup/delivery
    private Map<String, String> variantTypes;   //Defines the UNIQUE types for the variant.
                                                //A product's variants should have at least one different key/value pair to make it unique,
                                                //but the product will check for non unique variants.

    /**
     * @param inStock Defines variants availability in the store.
     * @param photoURL Absolute path to the header photo, appears on the store front.
     * @param price How much the product costs
     * @param shipOptions Map defining if a product is available for pickup/delivery
     * @param variantTypes Map defining the unique
     */
    public Variant(Boolean inStock, String photoURL, double price, Map<String, Boolean> shipOptions, Map<String, String> variantTypes){
        this.docId = UUID.randomUUID();
        this.inStock = inStock;
        this.photoURL = photoURL;
        this.price = price;
        this.shipOptions = new HashMap<>(shipOptions);
        this.variantTypes = new HashMap<>(variantTypes);
    }

    //DEFINE GETTERS/SETTERS
    public UUID getDocId(){return docId;}
    public Boolean isInStock(){return inStock;}
    public String getPhotoURL(){return photoURL;}
    public double getPrice(){return price;}
    public Map<String, Boolean> getShipOptions(){return shipOptions;}
    public Map<String, String> getVariantTypes(){return variantTypes;}

    public void print(){
        System.out.println("   Variant");
        System.out.println("   -------------------------");
        System.out.println("   docID: " + docId.toString());
        System.out.println("   inStock: " + inStock);
        System.out.println("   photoURL " + photoURL);
        System.out.println("   price:  " + price);

        System.out.println("   shipOptions:  {");
        for (String i : shipOptions.keySet()) {
            System.out.println("      " + i + ": " + shipOptions.get(i));
        }
        System.out.println("   }");

        System.out.println("   variantTypes:  {");
        for (String i : variantTypes.keySet()) {
            System.out.println("      " + i + ": " + variantTypes.get(i));
        }
        System.out.println("   }");
    }
}
