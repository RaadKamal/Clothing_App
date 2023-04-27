package com.example.clothingappframework.objects;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import clothingapp.objects.Variant;

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
    public Product(String name, String description, String category, String photoURL, String[] baseVariants, Variant[] variants){
        this.docId = UUID.randomUUID();
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


    //Check for invalid products
    public boolean isValid(){
        boolean validProduct = true;

        if(
            name.length() <= 0          ||
            description.length() <= 0   ||
            category.length() <= 0      ||
            photoURL.length() <= 0      ||
            !hasUniqueBaseVariants()    ||
            !variantsMatchProductBase() ||
            !hasUniqueVariants()
        )
            validProduct = false;

        return validProduct;
    }

    //Checks that all items in baseVariants are unique. i.e ["Colour","Colour] should return false
    public boolean hasUniqueBaseVariants(){
        boolean hasUnique = true;
        Set<String> set = new HashSet<>(Arrays.asList(baseVariants));

        if(set.size() != baseVariants.length){
            hasUnique = false;
            System.out.println("[ERROR]: Product does not have unique baseVariants");
            print();
        }

        return hasUnique;
    }

    //Checks that all variants match enforced baseVariants in Product
    public boolean variantsMatchProductBase(){
        boolean validVariants = true;

        //Check enforced baseVariants
        for(Variant v: variants){

            //Make array of all distinct variantTypes
            Map variantsTypes = v.getVariantTypes();
            String[] keys = new String[variantsTypes.size()];
            keys = (String[])v.getVariantTypes().keySet().toArray(keys);

            //Make temp array to prevent manipulation of baseVariants
            String[] tempBaseVariants = Arrays.copyOf(baseVariants, baseVariants.length);

            //Sort arrays to ensure Arrays.equals works on arrays with matching items but mismatched sorting.
            Arrays.sort(tempBaseVariants);
            Arrays.sort(keys);

            //Compare Variant's types to Products baseVariants
            if(!Arrays.equals(baseVariants, keys)){
                System.out.println("[ERROR]: Invalid variantType on:");
                v.print();
                System.out.println("keys: " + Arrays.toString(keys));
                System.out.println("baseVariants: " + Arrays.toString(baseVariants));
                validVariants = false;
            }
        }

        return validVariants;
    }

    //Checks that each variant has distinct variantTypes
    public boolean hasUniqueVariants(){
        boolean validVariants = true;

        //Loop through variants, comparing each one to every other variant
        //If identical variants exist then fail
        for(int i = 0; i < variants.length-1; i++){
            for(int j = i+1; j < variants.length; j++){
                if(variants[i].getVariantTypes().equals(variants[j].getVariantTypes())){
                    System.out.println("[ERROR]: Product has duplicate variantTypes on");
                    variants[i].print();
                    variants[j].print();
                    validVariants = false;
                }
            }
        }

        return validVariants;
    }

    public void print(){
        System.out.println("Product " + docId.toString());
        System.out.println("-------------------------");
        System.out.println("docID: " + docId.toString());
        System.out.println("name: " + name);
        System.out.println("category: " + category);
        System.out.println("description: " + description);
        System.out.println("photoURL:  " + photoURL);

        System.out.print("baseVariants:  [");
        for (String i : baseVariants) {
            System.out.print(i + ", ");
        }
        System.out.println("]");

        System.out.println("variants:  {");
        for (Variant i : variants) {
            i.print();
            System.out.println("   ,");
        }
        System.out.println("}");
    }
}
