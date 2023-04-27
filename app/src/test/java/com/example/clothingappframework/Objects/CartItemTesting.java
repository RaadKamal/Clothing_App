package com.example.clothingappframework.Objects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import clothingapp.objects.Cart;
import clothingapp.objects.CartItem;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;
import clothingapp.persistence.DBController;
import clothingapp.persistence.DBInterface;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CartItemTesting {

    private CartItem cartItem;
    ArrayList<Product> product;

    @Before
    public void setup() {
        DBController.createController(true);
        DBInterface access = DBController.getController();

        product=access.getProducts();
        cartItem = new CartItem(product.get(0),product.get(0).getVariants()[0],1);

        System.out.println("Initiating test for CartItem");
    }

    @Test
    public void testCartItem()
    {
        assert(cartItem.getProduct() instanceof Product);
        assert(cartItem.getSelectedVariant() instanceof Variant);
        assertNotNull(cartItem.getProduct());
        assertNotNull(cartItem.getSelectedVariant());

        assert(cartItem.getProduct() == product.get(0));
        assert(cartItem.getSelectedVariant() == product.get(0).getVariants()[0]);
        assert(cartItem.getQty() == 1);

        cartItem.setQty(1);
        assert(cartItem.getQty() == 1);

        CartItem nullItem = new CartItem(null, null, -1);

        assertNull(nullItem.getProduct());
        assertNull(nullItem.getSelectedVariant());
    }
}