package market.android.cryptocurrency.com.cryptocurrencymarket.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import market.android.cryptocurrency.com.cryptocurrencymarket.api.ErrorResponse;
import market.android.cryptocurrency.com.cryptocurrencymarket.api.base.IBaseCallbackListener;

/**
 * Created by tasev on 12/8/17.
 */

public class BaseActivity extends AppCompatActivity implements IBaseCallbackListener {

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
        Toast.makeText(this, "Please check your network connection!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void handleCommonError(ErrorResponse error) {
        if (error != null) {
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show();
        }
    }

}
