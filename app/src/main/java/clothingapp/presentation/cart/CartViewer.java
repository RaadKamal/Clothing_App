package clothingapp.presentation.cart;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import clothingapp.business.CartHandler;
import clothingapp.objects.Cart;
import clothingapp.objects.CartItem;
import clothingapp.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import clothingapp.presentation.shop.ShopViewer;

import static java.lang.Math.round;

public class CartViewer extends RecyclerView.Adapter<CartViewer.ViewHolder> {

    private Context context;

    public CartViewer(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartViewer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cartcard, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewer.ViewHolder holder, int position) {
        holder.delItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartHandler.removeFromCart(position);
                notifyItemRemoved(position);
                TextView total = ((Activity)context).findViewById(R.id.cart_total_price);
                total.setText("$"+Cart.getTotal());
            }
        });

        holder.decQty.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CartHandler.updateQty(position, Cart.getCartItem(position).getQty()-1);
                notifyItemChanged(position);
                TextView total = ((Activity)context).findViewById(R.id.cart_total_price);
                total.setText("$"+Cart.getTotal());
            }
        });

        holder.incQty.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                CartHandler.updateQty(position, Cart.getCartItem(position).getQty()+1);
                notifyItemChanged(position);
                TextView total = ((Activity)context).findViewById(R.id.cart_total_price);
                total.setText("$"+Cart.getTotal());
            }
        });

        holder.img.setImageResource(R.drawable.ic_action_name);
        holder.name.setText(Cart.getCartItem(position).getProduct().getName());
        holder.price.setText("$"+Math.round(Cart.getCartItem(position).getSelectedVariant().getPrice()*Cart.getCartItem(position).getQty()*100.0)/100.0);
        holder.quantity.setText(String.valueOf(Cart.getCartItem(position).getQty()));
    }

    @Override
    public int getItemCount() {
        return Cart.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout card;
        TextView name;
        TextView price;
        ImageView img;
        Button delItem;

        TextView quantity;
        Button decQty;
        Button incQty;

        public ViewHolder(@NonNull View cartView) {
            super(cartView);
            name = cartView.findViewById(R.id.cart_product_name);
            price = cartView.findViewById(R.id.cart_product_price);
            img = cartView.findViewById(R.id.cart_card_product_img);
            card = cartView.findViewById(R.id.cart_card);
            delItem = cartView.findViewById(R.id.delete_item);

            quantity = cartView.findViewById(R.id.cart_qty);
            decQty = cartView.findViewById(R.id.cart_dec_btn);
            incQty = cartView.findViewById(R.id.cart_inc_btn);
        }
    }
}
