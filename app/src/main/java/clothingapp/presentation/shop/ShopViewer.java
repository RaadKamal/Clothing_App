package clothingapp.presentation.shop;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import clothingapp.R;
import clothingapp.objects.Cart;

import clothingapp.objects.CartItem;
import clothingapp.objects.Product;
import clothingapp.objects.Shop;
import clothingapp.objects.Variant;
import java.util.ArrayList;
import java.util.UUID;
import clothingapp.business.QueryProducts;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public class ShopViewer extends RecyclerView.Adapter<ShopViewer.ViewHolder>{

    public static Cart cart;
    private ArrayList<UUID> pDocIDs = new ArrayList<>();
    private ArrayList<String> pPhotoURLs = new ArrayList<>();
    private ArrayList<String> pNames = new ArrayList<>();
    private ArrayList<Double> pPrices = new ArrayList<>();
    private QueryProducts queryProducts;
    private NavController navController;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ShopViewer(ArrayList<UUID> pDocIDs, ArrayList<String> pPhotoURLs, ArrayList<String> pNames, ArrayList<Double> pPrices, NavController navController) {
        this.pDocIDs = pDocIDs;
        this.pNames = pNames;
        this.pPrices = pPrices;
        this.pPhotoURLs = pPhotoURLs;
        this.navController = navController;
        queryProducts = new QueryProducts();
    }

    @NonNull
    @Override
    public ShopViewer.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_productcard, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewer.ViewHolder holder, int position) {
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shop.setSelectedProduct(queryProducts.getProduct(pDocIDs.get(position)));
                Shop.setSelectedVariant(queryProducts.getProduct(pDocIDs.get(position)).getVariants()[0]);
                Shop.setQty(1);

                navController.navigate(R.id.action_nav_shop_to_nav_product);
            }
        });

        holder.img.setImageResource(R.drawable.ic_action_name);
        holder.name.setText(pNames.get(position));
        holder.price.setText("$"+pPrices.get(position));
    }

    @Override
    public int getItemCount() {
        return pNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout card;
        TextView name;
        TextView price;
        ImageView img;

        public ViewHolder(@NonNull View productView){
            super(productView);

            name = productView.findViewById(R.id.product_name);
            price = productView.findViewById(R.id.product_price);
            img = productView.findViewById(R.id.product_img);
            card = productView.findViewById(R.id.product_card);
        }
    }
}