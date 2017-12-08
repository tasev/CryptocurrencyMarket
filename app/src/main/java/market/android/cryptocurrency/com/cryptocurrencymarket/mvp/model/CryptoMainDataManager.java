package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model;

import android.content.Context;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.MainActivity;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.ApiRequestFunctions;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas.GetCryptoDatasCallback;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoMainDataManager extends BaseDataManager {
    private static CryptoMainDataManager sInstance;
    private Context context;

    public CryptoMainDataManager(Context context) {
        super(context);
    }

    public static CryptoMainDataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CryptoMainDataManager(context);
        }
        return sInstance;
    }

    public void getCryptoDatas(String convertVal, int limit, Callback<List<CryptoData>> callback) {
        ApiRequestFunctions.getCryptoDatas(convertVal, limit, callback);
    }

}
