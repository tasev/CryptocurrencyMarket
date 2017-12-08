package market.android.cryptocurrency.com.cryptocurrencymarket.api.base;


import market.android.cryptocurrency.com.cryptocurrencymarket.api.ErrorResponse;

/**
 * Created by tasev on 12/8/17.
 */

public interface IBaseCallbackListener {

    void handleNetworkFailure(Throwable t);

    void handleCommonError(ErrorResponse error);
}
