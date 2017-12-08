package market.android.cryptocurrency.com.cryptocurrencymarket.details;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import market.android.cryptocurrency.com.cryptocurrencymarket.R;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodetails.GetCryptoCurrencyDetailsCallback;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodetails.GetCryptoCurrencyDetailsListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.base.BaseActivity;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoCurrencyDetailsMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoCurrencyDetailsManager;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters.CryptoCurrencyDetailsPresenter;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.CommonStringUtils;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.UtilApiConstants;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.UtilCryptoData;

/**
 * Created by tasev on 12/8/17.
 */

public class CryptoCurrencyDetails extends BaseActivity implements CryptoCurrencyDetailsMVP.View, GetCryptoCurrencyDetailsListener, SwipeRefreshLayout.OnRefreshListener {

    private static String TAG = "CryptoCurrencyDetails";

    @BindView(R.id.txtRank)
    TextView txtRank;

    @BindView(R.id.txtName)
    TextView txtName;

    @BindView(R.id.txtSymbol)
    TextView txtSymbol;

    @BindView(R.id.txtPrice)
    TextView txtPrice;

    @BindView(R.id.txtHVolume)
    TextView txtHVolume;

    @BindView(R.id.txtMarketCap)
    TextView txtMarketCap;

    @BindView(R.id.txtPriceInBitCoin)
    TextView txtPriceInBitCoin;

    @BindView(R.id.txtOneHChange)
    TextView txtOneHChange;

    @BindView(R.id.txttwentyFourHChange)
    TextView txttwentyFourHChange;

    @BindView(R.id.txtSevenDChange)
    TextView txtSevenDChange;

    @BindView(R.id.txtTotalSupply)
    TextView txtTotalSupply;

    @BindView(R.id.txtAvailableSupply)
    TextView txtAvailableSupply;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefresh;

    //MVP
    CryptoCurrencyDetailsPresenter cryptoCurrencyDetailsPresenter;

    CryptoData cryptoData = new CryptoData();
    String currentconvertVal = UtilApiConstants.USD;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateMVP();
        initialiseActivity();
        if (cryptoData == null)
            return;
        getCurrencyDetailsFromApi(true);
    }

    private void getCurrencyDetailsFromApi(boolean showProgress) {
        if (showProgress)
            showProgress();
        cryptoCurrencyDetailsPresenter.getCryptoCurrencyDetails(cryptoData.id, currentconvertVal, new GetCryptoCurrencyDetailsCallback(CryptoCurrencyDetails.this));
    }

    private void initiateMVP() {
        cryptoCurrencyDetailsPresenter = new CryptoCurrencyDetailsPresenter(CryptoCurrencyDetailsManager.getInstance(this));
    }

    private void initialiseViews() {
        txtRank.setText(String.valueOf(cryptoData.rank));
        txtName.setText(CommonStringUtils.getNonNullString(cryptoData.name));
        txtSymbol.setText(cryptoData.symbol);
        txtPrice.setText(String.valueOf(UtilCryptoData.getPriceInVal(cryptoData, currentconvertVal)));
        txtHVolume.setText(String.valueOf(UtilCryptoData.getHVolumeInVal(cryptoData, currentconvertVal)));
        txtMarketCap.setText(String.valueOf(UtilCryptoData.getMarketCapInVal(cryptoData, currentconvertVal)));
        txtPriceInBitCoin.setText(String.valueOf(cryptoData.price_btc));
        txtOneHChange.setText(String.valueOf(cryptoData.percent_change_1h));
        txttwentyFourHChange.setText(String.valueOf(cryptoData.percent_change_24h));
        txtSevenDChange.setText(String.valueOf(cryptoData.percent_change_7d));
        txtTotalSupply.setText(String.valueOf(cryptoData.total_supply));
        txtAvailableSupply.setText(String.valueOf(cryptoData.available_supply));
        swipeRefresh.setOnRefreshListener(this);
    }

    private void initialiseActivity() {
        setContentView(R.layout.crypto_currency_details);
        cryptoCurrencyDetailsPresenter.attachView(this);
        cryptoData.id = getIntent().getStringExtra(UtilApiConstants.CRYPTO_DATA_EXTRA_ID);
        currentconvertVal = getIntent().getStringExtra(UtilApiConstants.CRYPTO_DATA_EXTRA_VAL);
        ButterKnife.bind(this);

    }

    @Override
    public void getCryptoCurrencyDetailsSuccessful(List<CryptoData> getCryptoDataResponse) {
        if (!isVisible)
            return;
        hideProgress();
        swipeRefresh.setRefreshing(false);
        if (getCryptoDataResponse != null && getCryptoDataResponse.size() > 0)
            cryptoData = getCryptoDataResponse.get(0);
        initialiseViews();
    }

    @Override
    public void getCryptoCurrencyDetailsUnsuccessful(Throwable t) {
        if (!isVisible)
            return;
        hideProgress();
        swipeRefresh.setRefreshing(false);
        Log.d(TAG, "getCryptoCurrencyDetailsUnsuccessful");
    }

    @Override
    public void onRefresh() {
        getCurrencyDetailsFromApi(false);
    }
}
