package market.android.cryptocurrency.com.cryptocurrencymarket;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import market.android.cryptocurrency.com.cryptocurrencymarket.adapters.CryptoRecyclerAdapter;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas.GetCryptoDatasCallback;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.cryptodatas.GetCryptoDatasListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.base.BaseActivity;
import market.android.cryptocurrency.com.cryptocurrencymarket.datas.CryptoData;
import market.android.cryptocurrency.com.cryptocurrencymarket.listeners.CryptoAdapterInteractionsListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.CryptoMainMVP;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.CryptoMainDataManager;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters.CryptoMainPresenter;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.UtilApiConstants;

public class MainActivity extends BaseActivity implements CryptoAdapterInteractionsListener, GetCryptoDatasListener, CryptoMainMVP.View {

    private static String TAG = "MainActivity";
    private List<CryptoData> cryptoDataList = new ArrayList<>();
    private CryptoRecyclerAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    //MVP
    CryptoMainPresenter cryptoMainPresenter;

    String currentconvertVal = UtilApiConstants.USD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateMVP();
        initialiseActivity();
        initialiseAdapter();
        initialiseRecyclerView();
        getCryptoDatasFromApi(100);
    }

    @Override
    public void rowClicked(CryptoData cryptoData) {
        Toast.makeText(this, "" + cryptoData.name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getCryptoDatasSuccessful(List<CryptoData> getCountriesResponse) {
        if (!isVisible)
            return;
        hideProgress();
        prepareCryptoDataData(getCountriesResponse);
    }

    @Override
    public void getCryptoDatasUnsuccessful(Throwable t) {
        if (!isVisible)
            return;
        hideProgress();
        Log.d(TAG, "getCryptoDatasUnsuccessful");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_usd:
                currentconvertVal =UtilApiConstants.USD;
                getCryptoDatasFromApi(100);
                return true;
            case R.id.nav_eur:
                currentconvertVal =UtilApiConstants.EUR;
                getCryptoDatasFromApi(100);
                return true;
            case R.id.nav_cud:
                currentconvertVal =UtilApiConstants.CNY;
                getCryptoDatasFromApi(100);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void prepareCryptoDataData(List<CryptoData> getCountriesResponse) {
        mAdapter.currentconvertVal = currentconvertVal;
        mAdapter.changeList(getCountriesResponse);
    }

    private void initiateMVP() {
        cryptoMainPresenter = new CryptoMainPresenter(CryptoMainDataManager.getInstance(this));
    }

    private void getCryptoDatasFromApi(int limit) {
        showProgress();
        cryptoMainPresenter.getCryptoDatas(currentconvertVal, limit, new GetCryptoDatasCallback(MainActivity.this));
    }

    private void initialiseActivity() {
        setContentView(R.layout.activity_main);
        cryptoMainPresenter.attachView(this);
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
}
