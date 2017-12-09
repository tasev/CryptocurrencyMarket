package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoCurrencyDetailsMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoCurrencyDetailsManager;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoCurrencyDetailsPresenter extends BasePresenter<CryptoCurrencyDetailsMVP.View> implements CryptoCurrencyDetailsMVP.Presenter {

    private String lastConvertValApiCall = "";
    private String lastcryptoIdApiCall = "";

    public CryptoCurrencyDetailsPresenter(CryptoCurrencyDetailsManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCryptoCurrencyDetails(String cryptoId, String convertVal, Callback<List<CryptoData>> callback) {
        lastConvertValApiCall = convertVal;
        lastcryptoIdApiCall = cryptoId;
        ((CryptoCurrencyDetailsManager) mDataManager).getCryptoCurrencyDetails(cryptoId, convertVal, callback);
    }

    @Override
    public void getCryptoDatasIfSettingsChanged(String cryptoId, String convertVal, Callback<List<CryptoData>> callback) {
        if (lastConvertValApiCall.equals(convertVal) && lastcryptoIdApiCall.equals(cryptoId))
            return;
        getCryptoCurrencyDetails(cryptoId, convertVal, callback);
    }
}
