package clothingapp.business;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import clothingapp.business.exceptions.InvalidProductException;
import clothingapp.business.exceptions.InvalidShippingException;
import clothingapp.business.exceptions.InvalidVariantException;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;

public class Validity {
    //Check for invalid products
    public static boolean productValid(Product p) {
        Response r = new Response();

        try{
            validProductValues(p);
        }catch (InvalidProductException e){
            r.pushError("Invalid Product", e);
        }

        try {
            hasUniqueBaseVariants(p);
            variantsMatchProductBase(p);
            hasUniqueVariants(p);
            hasValidVariants(p);
        } catch(InvalidVariantException | InvalidShippingException e){
            r.pushError("Invalid Variant", e);
        }

        if (r.hasError()) {
            if (p.getDocId().toString() != null)
                System.err.println("Error on Product " + p.getDocId());

            r.generateErrors();
        }

        return !r.hasError();
    }

    private static void validProductValues(Product p) throws InvalidProductException {
        if (
            p.getDocId().toString() == null ||
            p.getName() == null ||
            p.getDescription() == null ||
            p.getCategory() == null ||
            p.getPhotoURL() == null)
        { throw  new InvalidProductException("Product Base Values Contains Null Element"); }

        if(
            p.getName().length() <= 0 ||
            p.getDescription().length() <= 0 ||
            p.getCategory().length() <= 0 ||
            p.getPhotoURL().length() <= 0)
        { throw  new InvalidProductException("Product Base Values Contains Empty String"); }
    }

    //Checks that all items in baseVariants are unique. i.e ["Colour","Colour] should return false
    private static void hasUniqueBaseVariants(Product p) throws InvalidVariantException {
        String[] baseVariants = p.getBaseVariants();
        Set<String> set = null;

        if (baseVariants != null) {
            set = new HashSet<>(Arrays.asList(baseVariants));

            if (set.size() != baseVariants.length) {
                throw new InvalidVariantException("Invalid Variant");
            }
        } else {
            throw new InvalidVariantException("Invalid Variant");
        }
    }

    //Checks that all variants match enforced baseVariants in Product
    private static void variantsMatchProductBase(Product p) throws InvalidVariantException {
        String[] baseVariants = p.getBaseVariants();

        //Check enforced baseVariants
        if (p.getVariants() != null) {
            for (Variant v : p.getVariants()) {

                //Make array of all distinct variantTypes
                Map variantsTypes = v.getVariantTypes();
                String[] keys = new String[variantsTypes.size()];
                keys = (String[]) v.getVariantTypes().keySet().toArray(keys);

                //Make temp array to prevent manipulation of baseVariants
                String[] tempBaseVariants = Arrays.copyOf(baseVariants, baseVariants.length);

                //Check for null Strings
                boolean baseHasNull = false;

                for (String baseVariant : tempBaseVariants) {
                    if (baseVariant == null)
                        baseHasNull = true;
                }

                if (!baseHasNull) {
                    //Sort arrays to ensure Arrays.equals works on arrays with matching items but mismatched sorting.
                    Arrays.sort(tempBaseVariants);
                    Arrays.sort(keys);

                    //Compare Variant's types to Products baseVariants
                    if (!Arrays.equals(baseVariants, keys)) {
                        throw new InvalidVariantException("Invalid Variants");
                    }
                } else {
                    throw new InvalidVariantException("Invalid Variants");
                }
            }
        } else
            throw new InvalidVariantException("Invalid Variants");
    }

    //Checks that each variant has distinct variantTypes
    public static void hasUniqueVariants(Product p) throws InvalidVariantException {
        Variant[] variants = p.getVariants();

        //Loop through variants, comparing each one to every other variant
        //If identical variants exist then fail

        if (variants != null) {
            for (int i = 0; i < variants.length - 1; i++) {
                for (int j = i + 1; j < variants.length; j++) {
                    if (variants[i].getVariantTypes().equals(variants[j].getVariantTypes())) {
                        throw new InvalidVariantException("Duplicate variantTypes");
                    }
                }
            }
        } else
            throw new InvalidVariantException("Null variantTypes");
    }

    //Checks for validity on the Variants.
    public static void hasValidVariants(Product p) throws InvalidVariantException, InvalidShippingException {
        Variant[] variants = p.getVariants();

        if (variants != null) {
            for (Variant variant : variants) {
                if (
                    variant.getDocId() == null ||
                    variant.getPhotoURL().length() <= 0 ||
                    variant.getPrice() < 0 ||
                    variant.getVariantTypes().size() <= 0
                )
                    throw new InvalidVariantException("Invalid Variants");

                try{
                    validShipOptions(variant);
                }catch(InvalidShippingException e){
                    throw new InvalidShippingException("Invalid Shipping");
                }
            }
        } else
            throw new InvalidVariantException("Invalid Variants");
    }

    //Checks for appropriate shippingOptions
    public static void validShipOptions(Variant v) throws InvalidShippingException{
        if (v != null) {
            Map<String, Boolean> shipOptions = v.getShipOptions();

            if (
                shipOptions == null ||
                shipOptions.size() != 2 ||
                !shipOptions.containsKey("Pickup") ||
                !shipOptions.containsKey("Delivery")
            )
                throw new InvalidShippingException("Invalid Shipping Options");
        } else
            throw new InvalidShippingException("Invalid Shipping Options");
    }
}

