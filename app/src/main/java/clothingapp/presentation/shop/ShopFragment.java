package clothingapp.presentation.shop;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.Navigation;
import clothingapp.R;
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

public class ShopFragment extends Fragment {

    private ArrayList<Product> products;

    private boolean loadSuccess;

    private ArrayList<UUID> pDocIDs;
    private ArrayList<String> pPhotoURLs;
    private ArrayList<String> pNames;
    private ArrayList<Double> pPrices;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //Attempt load products from DBModel
        try{
            QueryProducts qp = new QueryProducts();
            products = qp.getProducts();
            loadSuccess = true;

            pDocIDs = new ArrayList<>();
            pPhotoURLs = new ArrayList<>();
            pNames = new ArrayList<>();
            pPrices = new ArrayList<>();

            //Load product data into separate arrayLists
            for (int i = 0; i < products.size(); i++) {
                pDocIDs.add(products.get(i).getDocId());
                pPhotoURLs.add("@tools:sample/avatars[0]");
                pNames.add(products.get(i).getName());
                pPrices.add(products.get(i).getVariants()[0].getPrice());
            }
        }
        catch(Exception e){
            Log.d("ERROR", e.getMessage());
            loadSuccess = false;
        }



        return inflater.inflate(R.layout.fragment_shop, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        RecyclerView pv = getView().findViewById(R.id.shop_recycler_view);

        if(loadSuccess){
            //Attach product data to viewer.
            ShopViewer pViewer = new ShopViewer(pDocIDs, pPhotoURLs, pNames, pPrices, Navigation.findNavController(this.getView()));
            pv.setAdapter(pViewer);
            pv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
    }
}