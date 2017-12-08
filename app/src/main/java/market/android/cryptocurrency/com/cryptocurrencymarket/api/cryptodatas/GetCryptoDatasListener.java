package market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas;

import java.util.List;

import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.IBaseCallbackListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;


/**
 * Created by tasev on 12/8/17.
 */

public interface GetCryptoDatasListener extends IBaseCallbackListener {

    void getCryptoDatasSuccessful(List<CryptoData> getCountriesResponse);

    void getCryptoDatasUnsuccessful(Throwable t);
}
