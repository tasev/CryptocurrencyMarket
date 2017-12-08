package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoMainMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoMainDataManager;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoMainPresenter extends BasePresenter<CryptoMainMVP.View> implements CryptoMainMVP.Presenter {


    public CryptoMainPresenter(CryptoMainDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public void getCryptoDatas(String convertVal, int limit, Callback<List<CryptoData>> callback) {
        ((CryptoMainDataManager) mDataManager).getCryptoDatas(convertVal, limit, callback);
    }
}
