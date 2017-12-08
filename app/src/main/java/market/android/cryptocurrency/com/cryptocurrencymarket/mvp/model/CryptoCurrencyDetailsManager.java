package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model;

import android.content.Context;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.api.ApiRequestFunctions;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoCurrencyDetailsManager extends BaseDataManager {
    private static CryptoCurrencyDetailsManager sInstance;
    private Context context;

    public CryptoCurrencyDetailsManager(Context context) {
        super(context);
    }

    public static CryptoCurrencyDetailsManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CryptoCurrencyDetailsManager(context);
        }
        return sInstance;
    }

    public void getCryptoCurrencyDetails(String cryptoId, String convertVal, Callback<List<CryptoData>> callback) {
        ApiRequestFunctions.getCryptoCurrencyDetails(cryptoId, convertVal, callback);
    }

}
