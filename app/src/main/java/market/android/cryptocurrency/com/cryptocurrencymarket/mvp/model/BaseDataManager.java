package market.android.cryptocurrency.com.cryptocurrencymarket.mvp.model;

import android.content.Context;

/**
 * Created by tasev on 12/8/17.
 */

public class BaseDataManager {
    private static BaseDataManager sInstance;
    private Context context;

    //globalModel
    public BaseDataManager(Context context) {
        this.context = context;
    }

    public static BaseDataManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new BaseDataManager(context);
        }
        return sInstance;
    }

}
