package com.example.clothingappframework.Objects;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import clothingapp.objects.Cart;
import clothingapp.objects.CartItem;
import clothingapp.objects.Product;
import clothingapp.persistence.DBController;
import clothingapp.persistence.DBInterface;

public class CartTesting {

    private CartItem cartItem;
    private Cart cart;

    @Before
    public void setup() {
        DBController.createController(true);
        DBInterface access = DBController.getController();
        cart = new Cart();

        ArrayList<Product> product=access.getProducts();
        cartItem = new CartItem(product.get(0),product.get(0).getVariants()[0],1);

        System.out.println("Initiating test for Cart");
    }

    @Test
    public void testCart()
    {
        assert(cart.getCart().size() == 0);
        cart.addToCart(null);
        assert(cart.getCart().size() == 1);
        cart.addToCart(cartItem);
        assert(cart.getCart().size() == 3);
        Product cartProduct = cart.getCart().get(1).getProduct();
        assert(cartProduct.getDocId() == cartItem.getProduct().getDocId());
        cart.removeCartItem(cartItem);
        cart.removeCartItem(null);
        assert(cart.getCart().size()==0);
        assert(cart.isEmpty());
    }
}