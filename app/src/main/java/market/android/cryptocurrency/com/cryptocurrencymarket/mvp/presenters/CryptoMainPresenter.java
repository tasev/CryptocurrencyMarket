package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoMainMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoMainDataManager;
import retrofit2.Callback;

import static market.android.cryptocurrency.com.cryptocurrencymarket.utils.CommonStringUtils.getNonNullString;

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

    @Override
    public void filter(String text, List<CryptoData> cryptoDataList) {
        if (getNonNullString(text).equals("")) {
            mView.updateFiteredAdapterItems(cryptoDataList);
            return;
        }
        List<CryptoData> temp = new ArrayList();
        for (CryptoData d : cryptoDataList) {
            if (d != null && (d.symbol.toLowerCase()).contains(text.toLowerCase())) {
                temp.add(d);
            }
        }
        mView.updateFiteredAdapterItems(temp);
    }

    @Override
    public void rowCryptoDataClicked(CryptoData cryptoData) {
        mView.openCryptoDataDetails(cryptoData);
    }
}
