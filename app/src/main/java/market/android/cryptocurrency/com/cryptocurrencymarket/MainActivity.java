package market.android.cryptocurrency.com.cryptocurrencymarket;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import market.android.cryptocurrency.com.cryptocurrencymarket.adapters.CryptoRecyclerAdapter;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.ApiRequestFunctions;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas.GetCryptoDatasCallback;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas.GetCryptoDatasListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.base.BaseActivity;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.listeners.CryptoAdapterInteractionsListener;

public class MainActivity extends BaseActivity implements CryptoAdapterInteractionsListener, GetCryptoDatasListener {

    private static String TAG = "MainActivity";
    private List<CryptoData> cryptoDataList = new ArrayList<>();
    private CryptoRecyclerAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialiseActivity();
        initialiseAdapter();
        initialiseRecyclerView();
        getCryptoDatasFromApi();
    }

    private void getCryptoDatasFromApi() {
        ApiRequestFunctions.getCryptoDatas("cny", 100, new GetCryptoDatasCallback(MainActivity.this));

    }

    private void initialiseActivity() {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
    }

    private void initialiseAdapter() {
        mAdapter = new CryptoRecyclerAdapter(cryptoDataList, this);
    }

    private void initialiseRecyclerView() {
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void rowClicked(CryptoData cryptoData) {

    }

    @Override
    public void getCryptoDatasSuccessful(List<CryptoData> getCountriesResponse) {
        if (!isVisible)
            return;
        prepareCryptoDataData(getCountriesResponse);
    }

    @Override
    public void getCryptoDatasUnsuccessful(Throwable t) {
        if (!isVisible)
            return;
        Log.d(TAG, "getCryptoDatasUnsuccessful");
    }

    private void prepareCryptoDataData(List<CryptoData> getCountriesResponse) {
        mAdapter.changeList(getCountriesResponse);
    }
}
