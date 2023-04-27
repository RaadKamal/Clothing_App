package clothingapp.business.square;

import android.content.res.Resources;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import clothingapp.R;
import clothingapp.objects.Cart;
import sqip.Call;
import sqip.CardDetails;
import sqip.CardEntryActivityCommand;
import sqip.CardNonceBackgroundHandler;

public class SquareCardHandler implements CardNonceBackgroundHandler {

    private final SquareCall.Factory chargeCallFactory;
    private final Resources resources;

    public SquareCardHandler(SquareCall.Factory chargeCallFactory,
                                      Resources resources) {
        this.chargeCallFactory = chargeCallFactory;
        this.resources = resources;
    }

    @Override
    public CardEntryActivityCommand handleEnteredCardInBackground(CardDetails cardDetails) {
        Call<SquareResult> chargeCall = chargeCallFactory.create(cardDetails.getNonce());

        try{
            //Create POST
            HttpURLConnection connection = (HttpURLConnection) new URL ("https://connect.squareupsandbox.com/v2/payments").openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.setRequestMethod("POST");
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("Authorization", "Bearer EAAAEAVjCdYibuvRX-x4Ryy3zmlPCBX1lAfuNXDltl9nNnRopDhsvJvb2-LhuPh5");
            connection.addRequestProperty("Square-Version", "2021-03-17");

            JSONObject payload = new JSONObject();
            JSONObject amount = new JSONObject();

            //Create body in accordance with https://developer.squareup.com/reference/square/payments-api/create-payment
            amount.accumulate("amount",  (int)(Cart.getTotal()*100));
            amount.accumulate("currency", "CAD");
            payload.accumulate("idempotency_key", UUID.randomUUID());
            payload.accumulate("source_id", cardDetails.getNonce());
            payload.accumulate("amount_money", amount);
            payload.accumulate("location_id", "LGX8EJ3KH8YWG");

            //Write POST
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
            out.write(payload.toString());
            out.close();

            //Get Response
            BufferedReader reader = new BufferedReader (new InputStreamReader(connection.getInputStream()));
            int count = 0;
            for (String line; (line = reader.readLine()) != null;) {
                if(line != null){
                    //I'm not keen on giving you access to my Square account, so here's the payload to show that it actually generates the order.
                    System.out.println(line);
                }
            }

            //Cleanup
            reader.close();
            connection.disconnect();

            return new CardEntryActivityCommand.Finish();
        }
         catch (MalformedURLException ioException) {
            ioException.printStackTrace();
             return new CardEntryActivityCommand.ShowError(resources.getString(R.string.network_failure));
        } catch (JSONException jsonException) {
            jsonException.printStackTrace();
            return new CardEntryActivityCommand.ShowError(resources.getString(R.string.network_failure));
        } catch (IOException ioException){
            ioException.printStackTrace();
            return new CardEntryActivityCommand.ShowError(resources.getString(R.string.network_failure));
        }
    }
}
