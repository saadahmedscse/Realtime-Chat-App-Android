package com.saadahmedsoft.sparkconvo.util;

import android.content.Context;

import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;

public class ProgressDialog {

    private final PopupDialog popupDialog;

    private ProgressDialog(Context context) {
        popupDialog = PopupDialog.getInstance(context);
    }

    public static ProgressDialog getInstance(Context context) {
        return new ProgressDialog(context);
    }

    public void show() {
        popupDialog.setStyle(Styles.PROGRESS)
                .showDialog();
    }

    public void dismiss() {
        popupDialog.dismissDialog();
    }
}
