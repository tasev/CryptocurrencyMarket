package market.android.cryptocurrency.com.cryptocurrencymarket.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by tasev on 12/8/17.
 */

public class CommonStringUtils {

    public static String getNonNullString(String text) {
        return getNonNullString(text, "");
    }

    public static String getNonNullString(String text, String replacement) {
        return text != null ? text : replacement;
    }
}
