package market.android.cryptocurrency.com.cryptocurrencymarket.mvp;

import android.content.DialogInterface;

/**
 * Created by tasev on 12/8/17.
 */

public interface BaseMVPView {

    void showProgress();

    void showProgress(String title);

    void hideProgress();

    void showAlertDialogWithOneButton(String title, String description, DialogInterface.OnClickListener onClickListener);

}