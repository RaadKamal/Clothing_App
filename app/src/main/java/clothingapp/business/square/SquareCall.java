package clothingapp.business.square;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import okhttp3.ResponseBody;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import sqip.Call;

public class SquareCall implements Call<SquareResult> {

    public static class Factory {
        private final SquareService service;
        private final Converter<ResponseBody, SquareService.ChargeErrorResponse> errorConverter;

        public Factory(Retrofit retrofit) {
            service = retrofit.create(SquareService.class);
            Annotation[] noAnnotations = {};
            Type errorResponseType = SquareService.ChargeErrorResponse.class;
            errorConverter = retrofit.responseBodyConverter(errorResponseType, noAnnotations);
        }

        public Call<SquareResult> create(String nonce) {
            return new SquareCall(this, nonce);
        }
    }

    private final SquareCall.Factory factory;
    private final String nonce;
    private final retrofit2.Call<Void> call;

    public SquareCall(SquareCall.Factory factory,
                      String nonce) {
        this.factory = factory;
        this.nonce = nonce;
        call = factory.service.charge(new SquareService.ChargeRequest(nonce));
    }

    @Override
    public SquareResult execute() {
        Response<Void> response;
        try {
            response = call.execute();
        } catch (IOException e) {
            return SquareResult.networkError();
        }
        return responseToResult(response);
    }

    @Override
    public void enqueue(sqip.Callback<SquareResult> callback) {
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<Void> call, @NonNull Response<Void> response) {
                callback.onResult(responseToResult(response));
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<Void> call, Throwable throwable) {
                if (throwable instanceof IOException) {
                    callback.onResult(SquareResult.networkError());
                } else {
                    throw new RuntimeException("Unexpected exception", throwable);
                }
            }
        });
    }

    private SquareResult responseToResult(Response<Void> response) {
        if (response.isSuccessful()) {
            return SquareResult.success();
        }
        try {
            //noinspection ConstantConditions
            ResponseBody errorBody = response.errorBody();
            SquareService.ChargeErrorResponse errorResponse = factory.errorConverter.convert(errorBody);
            return SquareResult.error(errorResponse.errorMessage);
        } catch (IOException exception) {
            if (BuildConfig.DEBUG) {
                Log.d("ChargeCall", "Error while parsing error response: " + response.toString(),
                        exception);
            }
            return SquareResult.networkError();
        }
    }

    @Override
    public boolean isExecuted() {
        return call.isExecuted();
    }

    @Override
    public void cancel() {
        call.cancel();
    }

    @Override
    public boolean isCanceled() {
        return call.isCanceled();
    }

    @NonNull
    @Override
    public Call<SquareResult> clone() {
        return factory.create(nonce);
    }
}