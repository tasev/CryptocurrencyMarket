package market.android.cryptocurrency.com.cryptocurrencymarket.mvp;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import retrofit2.Callback;

/**
 * Created by tasev on 12/8/17.
 */

public interface CryptoCurrencyDetailsMVP {

    interface View extends BaseMVPView {

    }

    interface Presenter {

        void getCryptoCurrencyDetails(String cryptoId, String convertVal, Callback<List<CryptoData>> callback);
    }
}
