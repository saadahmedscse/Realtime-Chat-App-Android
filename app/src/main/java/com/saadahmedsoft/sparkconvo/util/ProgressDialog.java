package com.saadahmedsoft.sparkconvo.util;

import android.content.Context;

import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.sparkconvo.R;

public class ProgressDialog {

    private final PopupDialog popupDialog;

    private ProgressDialog(Context context) {
        popupDialog = PopupDialog.getInstance(context);
    }

    public static ProgressDialog getInstance(Context context) {
        return new ProgressDialog(context);
    }

    public void show() {
        popupDialog.setStyle(Styles.LOTTIE_ANIMATION)
                .setLottieRawRes(R.raw.loading)
                .showDialog();
    }

    public void dismiss() {
        popupDialog.dismissDialog();
    }
}
