package clothingapp.objects;

import java.util.ArrayList;

public class Cart {
    private static ArrayList<CartItem> cart = new ArrayList<CartItem>();
    private static double subTotal = 0; // Total cart price before tax
    private static double total = 0; // Total cart price after tax
    final private static double tax = 0.12;

    public static ArrayList<CartItem>  getCart() {
        return cart;
    }
    public static double getSubTotal() { return subTotal; }
    public static double getTotal(){return total;}
    public static double getTax(){return tax;}
    public static CartItem getCartItem(int index) { return cart.get(index); }

    public static void setSubTotal(double newSubTotal){subTotal = newSubTotal;}
    public static void setTotal(double newTotal){total = newTotal;}

    public static void addToCart(CartItem selectedItem) { cart.add(selectedItem); }
    public static void removeFromCart(int index) { cart.remove(index); }

    public static boolean isEmpty() {
        return cart.isEmpty();
    }
    public static int size() {
        return cart.size();
    }
}