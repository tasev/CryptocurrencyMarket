package market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas;


import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.BaseCallback;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.IBaseCallbackListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tasev on 12/8/17.
 */

public class GetCryptoDatasCallback extends BaseCallback implements Callback<List<CryptoData>> {

    public GetCryptoDatasCallback(GetCryptoDatasListener listener) {
        super(listener);
    }

    @Override
    public void onResponse(Call<List<CryptoData>> call, Response<List<CryptoData>> response) {
        IBaseCallbackListener listener = weakReference.get();
        if (listener == null) return;
        if (!responseIsOK(response)) {
            return;
        }
        ((GetCryptoDatasListener) listener).getCryptoDatasSuccessful(response.body());
    }


    @Override
    public void onFailure(Call<List<CryptoData>> call, Throwable t) {
        IBaseCallbackListener listener = weakReference.get();
        if (listener == null) return;
        ((GetCryptoDatasListener) listener).getCryptoDatasUnsuccessful(t);
        onFailure(t);
    }
}
