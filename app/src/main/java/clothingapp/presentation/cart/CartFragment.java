package clothingapp.presentation.cart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.navigation.Navigation;
import clothingapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import clothingapp.objects.Cart;

public class CartFragment extends Fragment implements View.OnClickListener {
    TextView totalPrice;
    Button checkout;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (Cart.isEmpty()) {
           Log.i("empty cart!","Nothing is in the cart!");
        }

        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            RecyclerView cv = view.findViewById(R.id.cart_list);
            CartViewer cViewer = new CartViewer(this.getContext());
            cv.setAdapter(cViewer);
            cv.setLayoutManager(new LinearLayoutManager(getContext()));

            totalPrice = view.findViewById(R.id.cart_total_price);
            checkout = view.findViewById(R.id.checkout_btn);
            checkout.setOnClickListener(this);

            totalPrice.setText("Total Price = $" + Cart.getTotal());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout_btn:
                replaceFragment(R.id.action_nav_cart_to_nav_checkout);
                break;
        }
    }

    public void replaceFragment(int id) {
        Navigation.findNavController(this.getView()).navigate(id);
    }
}