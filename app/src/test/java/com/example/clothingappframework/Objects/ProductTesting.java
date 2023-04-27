package com.example.clothingappframework.Objects;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import clothingapp.business.Validity;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;

import static org.junit.Assert.*;

public class ProductTesting {
    private String[] baseVariant;
    private Map<String, Boolean> shipOptions;
    private Map<String, String> variantTypes;
    private Variant var;
    
    @Before
    public void setup() {
        baseVariant = new String[10];
        shipOptions = new HashMap<>();
        variantTypes = new HashMap<>();
        var = new Variant(true, "www", 000.000, shipOptions, variantTypes);
        System.out.println("Initiating test for ProductTesting");
    }

    @Test
    public void testproductiem() {
        String[] baseVariants = new String[10];
        Map<String, Boolean> shipOptions = new HashMap<>();
        Map<String, String> variantTypes = new HashMap<>();
        Variant var = new Variant(true, "www", 000.000, shipOptions, variantTypes);
        Variant[] variants = new Variant[1];
        variants[0]=var;
        Product pro = new Product("Hello", "good", "Z", "www", baseVariants, variants);
        assertFalse(Validity.productValid(pro));
        System.out.println("Finished test for ProductTesting");
    }

}

