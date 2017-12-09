package market.android.cryptocurrency.com.cryptocurrencymarket;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import market.android.cryptocurrency.com.cryptocurrencymarket.activities.ActivitySettings;
import market.android.cryptocurrency.com.cryptocurrencymarket.activities.CryptoCurrencyDetails;
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
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.UtilSettings;

public class MainActivity extends BaseActivity implements CryptoAdapterInteractionsListener, GetCryptoDatasListener, CryptoMainMVP.View, SearchView.OnQueryTextListener, SwipeRefreshLayout.OnRefreshListener {

    private static String TAG = "MainActivity";
    private List<CryptoData> cryptoDataList = new ArrayList<>();
    private CryptoRecyclerAdapter mAdapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefresh;

    //MVP
    CryptoMainPresenter cryptoMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initiateMVP();
        initialiseActivity();
        initialiseAdapter();
        initialiseRecyclerView();
        getCryptoDatasFromApi(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideProgress();
        cryptoMainPresenter.getCryptoDatasIfSettingsChanged(UtilSettings.getInstance().getCurrentconvertVal(), UtilSettings.getInstance().getCurrentconvertLimit(), new GetCryptoDatasCallback(MainActivity.this));
    }

    @Override
    public void rowClicked(CryptoData cryptoData) {
        cryptoMainPresenter.rowCryptoDataClicked(cryptoData);
    }

    @Override
    public void getCryptoDatasSuccessful(List<CryptoData> getCryptoDatasResponse) {
        if (!isVisible)
            return;
        hideProgress();
        swipeRefresh.setRefreshing(false);
        prepareCryptoDataData(getCryptoDatasResponse);
    }

    @Override
    public void getCryptoDatasUnsuccessful(Throwable t) {
        if (!isVisible)
            return;
        hideProgress();
        swipeRefresh.setRefreshing(false);
        Log.d(TAG, "getCryptoDatasUnsuccessful");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager != null ? searchManager.getSearchableInfo(MainActivity.this.getComponentName()) : null);
            searchView.setOnQueryTextListener(this);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.nav_settings:
                openSettingsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        cryptoMainPresenter.filter(newText, cryptoDataList);
        return false;
    }

    @Override
    public void onRefresh() {
        getCryptoDatasFromApi(false);
    }

    @Override
    public void updateFiteredAdapterItems(List<CryptoData> filteredCryptoDataList) {
        mAdapter.changeList(filteredCryptoDataList);
    }

    @Override
    public void openCryptoDataDetails(CryptoData cryptoData) {
        startActivity(new Intent(MainActivity.this, CryptoCurrencyDetails.class).putExtra(UtilApiConstants.CRYPTO_DATA_EXTRA_ID, cryptoData.id));
    }

    public void openSettingsActivity() {
        startActivity(new Intent(MainActivity.this, ActivitySettings.class));
    }

    private void prepareCryptoDataData(List<CryptoData> getCountriesResponse) {
        cryptoDataList = getCountriesResponse;
        mAdapter.currentconvertVal = UtilSettings.getInstance().getCurrentconvertVal();
        mAdapter.changeList(getCountriesResponse);
    }

    private void initiateMVP() {
        cryptoMainPresenter = new CryptoMainPresenter(CryptoMainDataManager.getInstance(this));
    }

    private void getCryptoDatasFromApi(boolean showProgress) {
        if (showProgress)
            showProgress();
        cryptoMainPresenter.getCryptoDatas(UtilSettings.getInstance().getCurrentconvertVal(), UtilSettings.getInstance().getCurrentconvertLimit(), new GetCryptoDatasCallback(MainActivity.this));
    }

    private void initialiseActivity() {
        setContentView(R.layout.activity_main);
        cryptoMainPresenter.attachView(this);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(null);
        }
        swipeRefresh.setOnRefreshListener(this);
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
