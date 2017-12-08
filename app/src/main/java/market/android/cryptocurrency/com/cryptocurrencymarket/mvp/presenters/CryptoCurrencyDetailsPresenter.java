package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters;

import java.util.ArrayList;
import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.details.CryptoCurrencyDetails;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoCurrencyDetailsMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoMainMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoCurrencyDetailsManager;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoMainDataManager;
import retrofit2.Callback;

import static market.android.cryptocurrency.com.cryptocurrencymarket.utils.CommonStringUtils.getNonNullString;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoCurrencyDetailsPresenter extends BasePresenter<CryptoCurrencyDetailsMVP.View> implements CryptoCurrencyDetailsMVP.Presenter {

    public CryptoCurrencyDetailsPresenter(CryptoCurrencyDetailsManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCryptoCurrencyDetails(String cryptoId, String convertVal, Callback<List<CryptoData>> callback) {
        ((CryptoCurrencyDetailsManager) mDataManager).getCryptoCurrencyDetails(cryptoId, convertVal, callback);
    }
}
