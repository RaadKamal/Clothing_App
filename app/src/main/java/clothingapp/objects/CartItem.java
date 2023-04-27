package clothingapp.objects;

public class CartItem{
    private Product product;
    private Variant selectedVariant;
    private int qty;

    public CartItem(Product product, Variant selectedVariant, int qty) {
        this.product = product;
        this.selectedVariant = selectedVariant;
        this.qty = qty;
    }

    public Product getProduct() {
        return product;
    }
    public Variant getSelectedVariant() { return selectedVariant; }
    public int getQty() { return qty; }
    public void setQty(int qty) {
        this.qty = qty;
    }
}