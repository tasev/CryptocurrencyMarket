package market.android.cryptocurrency.com.cryptocurrencymarket.mvp;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public interface CryptoMainMVP {

    interface View extends BaseMVPView {

        void updateFiteredAdapterItems(List<CryptoData> filteredCryptoDataList);

        void openCryptoDataDetails(CryptoData cryptoData);
    }

    interface Presenter {

        void getCryptoDatas(String convertVal, int limit, Callback<List<CryptoData>> callback);

        void getCryptoDatasIfSettingsChanged(String convertVal, int limit, Callback<List<CryptoData>> callback);

        void filter(String text, List<CryptoData> cryptoDataList);

        void rowCryptoDataClicked(CryptoData cryptoData);
    }
}
