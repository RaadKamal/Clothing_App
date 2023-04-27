package clothingapp.objects;

import clothingapp.business.utility.Utility;

public class Shop {
    private static Product selectedProduct;
    private static Variant selectedVariant;
    private static int qty;

    public static Product getSelectedProduct() {
        return selectedProduct;
    }
    public static Variant getSelectedVariant() {
        return selectedVariant;
    }
    public static int getQty() { return qty; }

    public static void setSelectedProduct(Product selectedProduct) { Shop.selectedProduct = selectedProduct; }
    public static void setSelectedVariant(Variant selectedVariant) { Shop.selectedVariant = selectedVariant; }
    public static void setQty(int qty) { Shop.qty = Utility.clamp(qty, 1, Integer.MAX_VALUE);}
}
