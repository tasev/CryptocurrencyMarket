package market.android.cryptocurrency.com.cryptocurrencymarket.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import market.android.cryptocurrency.com.cryptocurrencymarket.R;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.ErrorResponse;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.IBaseCallbackListener;
import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.BaseMVPView;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.ProgressDialogHandler;
import market.android.cryptocurrency.com.cryptocurrencymarket.utils.UtilUI;

/**
 * Created by tasev on 12/8/17.
 */

public class BaseActivity extends AppCompatActivity implements IBaseCallbackListener, BaseMVPView {

    protected boolean isVisible = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isVisible = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isVisible = false;
    }

    @Override
    public void handleNetworkFailure(Throwable t) {
        hideProgress();
    }

    @Override
    public void handleCommonError(ErrorResponse error) {
        hideProgress();
        if (error != null) {
            showAlertDialogWithOneButton("", error.message, null);
            return;
        }
        showAlertDialogWithOneButton("", getString(R.string.error_message), null);
    }

    @Override
    public void showProgress() {
        showProgress(null);
    }

    @Override
    public void showProgress(String title) {
        ProgressDialogHandler.getInstance().showProgressDialog(title, this);
    }

    @Override
    public void hideProgress() {
        ProgressDialogHandler.getInstance().hideProgressDialog();
    }

    @Override
    public void showAlertDialogWithOneButton(String title, String description, DialogInterface.OnClickListener onClickListener) {
        UtilUI.showAlertDialogWithOneButton(this, title, description, onClickListener);
    }

}
