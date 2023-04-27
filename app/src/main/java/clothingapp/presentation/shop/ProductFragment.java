package clothingapp.presentation.shop;

import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import clothingapp.R;
import clothingapp.business.CartHandler;
import clothingapp.objects.Product;
import java.util.ArrayList;
import java.util.UUID;
import clothingapp.business.QueryProducts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import clothingapp.objects.Shop;

public class ProductFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView name = (TextView)view.findViewById(R.id.productpage_name);
        TextView price = (TextView)view.findViewById(R.id.productpage_price);
        TextView description = (TextView)view.findViewById(R.id.productpage_description);
        TextView qty = (TextView)view.findViewById(R.id.productpage_qty) ;
        ImageView photo = (ImageView)view.findViewById(R.id.productpage_img);
        Button decrQty = (Button)view.findViewById(R.id.productpage_dec_btn);
        Button incrQty = (Button)view.findViewById(R.id.productpage_inc_btn);
        Button addToCart = (Button)view.findViewById(R.id.addToCart_btn);

        name.setText(Shop.getSelectedProduct().getName());
        price.setText("$"+Shop.getSelectedVariant().getPrice());
        description.setText(Shop.getSelectedProduct().getDescription());
        qty.setText(String.valueOf(Shop.getQty()));
        photo.setImageResource(R.drawable.ic_action_name);

        decrQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shop.setQty(Shop.getQty()-1);
                qty.setText(String.valueOf(Shop.getQty()));
            }
        });

        incrQty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Shop.setQty(Shop.getQty()+1);
                qty.setText(String.valueOf(Shop.getQty()));
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartHandler.addToCart(Shop.getSelectedProduct(), Shop.getSelectedVariant(), Shop.getQty());
                Toast.makeText(getActivity(), Shop.getSelectedProduct().getName() +" is Added to Cart",Toast.LENGTH_SHORT).show();
            }
        });
    }
}