package market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodetails;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.IBaseCallbackListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;


/**
 * Created by tasev on 12/8/17.
 */

public interface GetCryptoCurrencyDetailsListener extends IBaseCallbackListener {

    void getCryptoCurrencyDetailsSuccessful(List<CryptoData> getCryptoDataResponse);

    void getCryptoCurrencyDetailsUnsuccessful(Throwable t);
}
