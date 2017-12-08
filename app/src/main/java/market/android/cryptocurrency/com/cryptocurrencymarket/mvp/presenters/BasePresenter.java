package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.presenters;

import android.support.annotation.NonNull;

import market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model.BaseDataManager;

/**
 * Created by tasev on 12/8/17.
 */

public class BasePresenter<BaseMVPView> {

protected BaseMVPView mView;

protected BaseDataManager mDataManager;

public final void attachView(@NonNull BaseMVPView view) {
        mView = view;
        }

public final void detachView() {
        mView = null;
        }

/**
 * Check if the view is attached.
 * This checking is only necessary when returning from an asynchronous call
 */
protected final boolean isViewAttached() {
        return mView != null;
        }

public BasePresenter(BaseDataManager dataManager) {
        mDataManager = dataManager;
        }

        }
