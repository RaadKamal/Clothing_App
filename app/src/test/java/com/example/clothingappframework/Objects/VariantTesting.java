package com.example.clothingappframework.Objects;


import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import clothingapp.business.Validity;
import clothingapp.objects.Variant;

import static org.junit.Assert.*;

public class VariantTesting {
    private Variant variant;

    @Before
    public void setup() {
        System.out.println("Initiating test for Product");

        variant = new Variant(true, "www", 000.000,
            new HashMap<String, Boolean>(){{
                put("Delivery", true);
                put("Pickup", false);
            }},
            new HashMap<String, String>(){{
                put("Colour", "Red");
                put("Size", "Medium");
            }});
    }

    @Test
    public void testproduct(){
        assert(variant.getDocId() instanceof UUID);
        assert(variant.isInStock() == true);
        assert(variant.getPhotoURL() != null);
        assert(variant.getPrice() == 0);
        assert(variant.getShipOptions().get("Delivery") == true);
        assert(variant.getShipOptions().get("Pickup") == false);
        assert(variant.getVariantTypes().get("Colour") == "Red");
        assert(variant.getVariantTypes().get("Size") == "Medium");
    }
}
