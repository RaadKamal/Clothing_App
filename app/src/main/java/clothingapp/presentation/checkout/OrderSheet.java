package clothingapp.presentation.checkout;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import clothingapp.R;
import clothingapp.objects.Cart;
import clothingapp.objects.Checkout;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class OrderSheet {

  private static final String SHOWING_KEY = "showing";
  private Runnable onPayWithCardClickListener;
  private boolean showing;

  public void setOnPayWithCardClickListener(Runnable listener) {
    onPayWithCardClickListener = listener;
  }

  public void show(Activity activity) {
    showing = true;
    BottomSheetDialog dialog = new BottomSheetDialog(activity);
    View sheetView = LayoutInflater.from(activity).inflate(R.layout.sheet_order, null);

    TextView name = sheetView.findViewById(R.id.square_name);
    TextView address = sheetView.findViewById(R.id.square_address);
    TextView price = sheetView.findViewById(R.id.square_price);

    name.setText(Checkout.getFirstName() + " " + Checkout.getLastName());
    address.setText(Checkout.getAddress() + "\n" + Checkout.getCity() + " " + Checkout.getProvince() + ", " + Checkout.getCountry());
    price.setText("$"+Cart.getTotal());

    View closeButton = sheetView.findViewById(R.id.close_sheet_button);
    View payWithCardButton = sheetView.findViewById(R.id.pay_with_card_button);
    payWithCardButton.setOnClickListener(v -> {
      dialog.dismiss();
      showing = false;
      onPayWithCardClickListener.run();
    });

    closeButton.setOnClickListener(v -> dialog.dismiss());

    dialog.setContentView(sheetView);
    BottomSheetBehavior behavior = BottomSheetBehavior.from((View) sheetView.getParent());
    dialog.setOnShowListener(dialogInterface -> behavior.setPeekHeight(sheetView.getHeight()));
    dialog.setOnCancelListener(dialog1 -> showing = false);
    dialog.setCancelable(true);

    dialog.show();
  }

  public void onSaveInstanceState(Bundle outState) {
    outState.putBoolean(SHOWING_KEY, showing);
  }

  public void onRestoreInstanceState(Activity activity, Bundle savedInstanceState) {
    boolean showing = savedInstanceState.getBoolean(SHOWING_KEY);
    if (showing) {
      show(activity);
    }
  }
}
