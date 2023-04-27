package clothingapp.objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Product.java
 * Group: Group 8
 * COMP 3350, A03
 *
 * Defines the Product object,
 */
public class Product {
    private UUID docId;                 //Assigning docID's to UUIDs allows client to have functionally infinite products on store.
    private String name;
    private String description;
    private String category;
    private String photoURL;            //Absolute path to a photo, used to generate images on store.
    private String[] baseVariants;
    private Variant[] variants;

    /**
     * @param name Product's Name
     * @param description Description of the Product that appears on the store
     * @param category Product Category (Shoes/Hats/Shirts), used for sorting/filtering in shop.
     * @param photoURL Absolute path to the header photo, appears on the store front.
     * @param baseVariants Defines the required variant Types for variants.
     * @param variants An array of the possible variants a product may have.
     */
    public Product(String docID, String name, String description, String category, String photoURL, String[] baseVariants, Variant[] variants){
        this.docId = UUID.fromString(docID);
        this.name = name;
        this.description = description;
        this.category = category;
        this.photoURL = photoURL;
        this.baseVariants = baseVariants;
        this.variants = variants;
    }

    //DEFINE GETTERS/SETTERS
    public UUID getDocId(){return docId;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public String getCategory(){return category;}
    public String getPhotoURL(){return photoURL;}
    public String[] getBaseVariants(){return baseVariants;}
    public Variant[] getVariants(){return variants;}

    public void print(){
        System.out.println("Product " + docId.toString());
        System.out.println("-------------------------");
        System.out.println("docID: " + docId.toString());
        System.out.println("name: " + name);
        System.out.println("category: " + category);
        System.out.println("description: " + description);
        System.out.println("photoURL:  " + photoURL);

        System.out.print("baseVariants:  [");
        if(baseVariants != null){
            for (String i : baseVariants) {
                System.out.print(i + ", ");
            }
        }
        System.out.println("]");

        System.out.println("variants:  {");
        if(variants != null){
            for (Variant i : variants) {
                i.print();
                System.out.println("   ,");
            }
        }
        System.out.println("}");
    }
}
