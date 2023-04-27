package clothingapp.business;

import java.util.ArrayList;

import clothingapp.business.utility.Utility;
import clothingapp.objects.Cart;
import clothingapp.objects.CartItem;
import clothingapp.objects.Product;
import clothingapp.objects.Variant;

public class CartHandler {
    public static boolean updateQty(int index, int qty){
        try{
            CartItem item = Cart.getCartItem(index);
            item.setQty(Utility.clamp(qty, 1, Integer.MAX_VALUE));
        }
        catch(IndexOutOfBoundsException e){
            throw e;
        }

        updateTotal();
        return true;
    }

    public static void addToCart(Product p, Variant v, int qty){
        boolean itemExistsInCart = false;
        int position = 0;

        CartItem item = new CartItem(p, v, qty);

        for(int i = 0; i < Cart.size() && !itemExistsInCart; i++){
            if(Cart.getCartItem(i).getProduct().getDocId().equals(p.getDocId())){
                itemExistsInCart = true;
                position = i;
            }
        }

        if(!itemExistsInCart)
            Cart.addToCart(item);
        else
            Cart.getCartItem(position).setQty(Cart.getCartItem(position).getQty()+qty);

        updateTotal();
    }

    public static void removeFromCart(int index){
        Cart.removeFromCart(index);
        updateTotal();
    }

    public static void updateTotal(){
        ArrayList<CartItem> cart = Cart.getCart();
        double subtotal = 0;
        double total = 0;

        for(int i = 0; i < cart.size(); i++){
            CartItem item = cart.get(i);

            double price = item.getSelectedVariant().getPrice();
            int qty = item.getQty();

            subtotal += price * qty;
        }

        total = subtotal + (subtotal*Cart.getTax());
        total = Math.round(total*100.0)/100.0;
        Cart.setSubTotal(subtotal);
        Cart.setTotal(total);
    }
}
