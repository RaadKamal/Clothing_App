package clothingapp.business.square;

import android.util.Log;

import com.squareup.moshi.Moshi;

import java.util.UUID;

import clothingapp.objects.Cart;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class SquareConfig {

    private static final String CHARGE_SERVER_HOST = "group8-cadclothingstore.herokuapp.com/chargeForCookie";
    private static final String CHARGE_SERVER_URL = "https://" + CHARGE_SERVER_HOST +" /";

    public static Retrofit createRetrofitInstance() {
        Moshi moshi = new Moshi.Builder().build();
        return new Retrofit
                .Builder()
                .baseUrl(SquareConfig.CHARGE_SERVER_URL)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build();
    }
}