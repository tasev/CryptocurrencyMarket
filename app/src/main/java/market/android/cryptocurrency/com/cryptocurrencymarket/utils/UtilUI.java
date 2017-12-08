package market.android.cryptocurrency.com.cryptocurrencymarket.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.widget.TextView;

/**
 * Created by tasev on 12/8/17.
 */

public class UtilUI {
    public static Dialog imageDialog;

    public static void showAlertDialogWithOneButton(Context context, String title, String description, DialogInterface.OnClickListener onClickListener) {
        try {
            final AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
            builder1.setTitle(title);
            builder1.setMessage(description);
            builder1.setNeutralButton("OK", onClickListener);
            builder1.setCancelable(false);
            builder1.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
