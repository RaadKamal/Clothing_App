package clothingapp.presentation.checkout;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.wallet.PaymentsClient;

import sqip.CardEntry;
import sqip.InAppPaymentsSdk;

import androidx.appcompat.app.AlertDialog;
import clothingapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import clothingapp.business.square.SquareCall;
import clothingapp.business.square.SquareCardHandler;
import clothingapp.business.square.SquareConfig;
import clothingapp.objects.Cart;
import clothingapp.objects.Checkout;
import retrofit2.Retrofit;

public class CheckoutFragment extends Fragment {

    private SquareCall.Factory squareCallFactory;
    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 1;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private PaymentsClient paymentsClient;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Retrofit retrofit = SquareConfig.createRetrofitInstance();
        squareCallFactory = new SquareCall.Factory(retrofit);

        SquareCardHandler cardHandler = new SquareCardHandler(squareCallFactory, getResources());
        CardEntry.setCardNonceBackgroundHandler(cardHandler);

        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        TextView total = view.findViewById(R.id.checkout_total_price);
        TextView firstName = view.findViewById(R.id.edit_FirstName);
        TextView lastName = view.findViewById(R.id.edit_LastName);
        TextView address = view.findViewById(R.id.edit_Address);
        TextView city = view.findViewById(R.id.edit_City);
        TextView province = view.findViewById(R.id.edit_Province);
        TextView country = view.findViewById(R.id.edit_Country);
        TextView phone = view.findViewById(R.id.edit_Phone);
        TextView email = view.findViewById(R.id.edit_Email);

        Button pay = view.findViewById(R.id.checkout_payBtn);

        total.setText("Total: $"+Cart.getTotal());
        firstName.setText(Checkout.getFirstName());
        lastName.setText(Checkout.getLastName());
        address.setText(Checkout.getAddress());
        city.setText(Checkout.getCity());
        province.setText(Checkout.getProvince());
        country.setText(Checkout.getCountry());
        phone.setText(Checkout.getPhone());
        email.setText(Checkout.getEmail());

        firstName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setFirstName(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        lastName.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setLastName(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        address.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setAddress(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        city.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setCity(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        province.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setProvince(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        country.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setCountry(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        phone.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setPhone(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });
        email.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) { Checkout.setEmail(charSequence.toString());}
            public void afterTextChanged(Editable editable) { }
        });

        OrderSheet orderSheet = new OrderSheet();
        orderSheet = new OrderSheet();
        orderSheet.setOnPayWithCardClickListener(this::startCardEntryActivity);

        pay.setOnClickListener(v -> {
            if (InAppPaymentsSdk.INSTANCE.getSquareApplicationId().equals("REPLACE_ME")) {
                showMissingSquareApplicationIdDialog();
            } else {
                showOrderSheet();
            }
        });
    }

    private void startCardEntryActivity() {
        CardEntry.startCardEntryActivity(getActivity());
    }

    private void showMissingSquareApplicationIdDialog() {
        new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
            .setTitle(R.string.missing_application_id_title)
            .setMessage(Html.fromHtml(getString(R.string.missing_application_id_message)))
            .setPositiveButton(android.R.string.ok, (dialog, which) -> showOrderSheet())
            .show();
    }

    private void showOrderSheet() {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setOnPayWithCardClickListener(this::startCardEntryActivity);
        orderSheet.show(getActivity());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CardEntry.handleActivityResult(data, cardEntryActivityResult -> {
            if (cardEntryActivityResult.isSuccess()) {
                showSuccessCharge();
            } else if (cardEntryActivityResult.isCanceled()) {
                Resources res = getResources();
                int delayMs = res.getInteger(R.integer.card_entry_activity_animation_duration_ms);
                handler.postDelayed(this::showOrderSheet, delayMs);
            }
        });
    }

    public void showError(String message) {
        showOkDialog(R.string.unsuccessful_order, message);
    }

    public void showSuccessCharge() {
        showOkDialog(R.string.successful_order_title, getString(R.string.successful_order_message));
    }

    public void showServerHostNotSet() {
        showOkDialog(R.string.server_host_not_set_title, Html.fromHtml(getString(R.string.server_host_not_set_message)));
    }

    private void showOkDialog(int titleResId, CharSequence message) {
        new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(titleResId)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .show();
    }

    public void showNetworkErrorRetryPayment(Runnable retry) {
        new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setTitle(R.string.network_failure_title)
                .setMessage(getString(R.string.network_failure))
                .setPositiveButton(R.string.retry, (dialog, which) -> retry.run())
                .show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setOnPayWithCardClickListener(this::startCardEntryActivity);
        orderSheet.onSaveInstanceState(outState);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        OrderSheet orderSheet = new OrderSheet();
        orderSheet.setOnPayWithCardClickListener(this::startCardEntryActivity);
        orderSheet.onRestoreInstanceState(getActivity(), savedInstanceState);
    }
}