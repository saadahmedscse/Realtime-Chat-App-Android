package com.saadahmedsoft.sparkconvo.util;

import android.content.Context;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.sparkconvo.R;

public class ProgressDialog {

    private final BottomSheetDialog bottomSheetDialog;

    private ProgressDialog(Context context) {
        bottomSheetDialog = new BottomSheetDialog(context, R.style.BottomSheetStyle);
    }

    public static ProgressDialog getInstance(Context context) {
        return new ProgressDialog(context);
    }

    public void show(String message) {
        bottomSheetDialog.setContentView(R.layout.item_layout_bottom_sheet);
        TextView progressText = bottomSheetDialog.findViewById(R.id.tv_progress_message);
        assert progressText != null;
        progressText.setText(message);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.show();
    }

    public void dismiss() {
        if (bottomSheetDialog.isShowing()) bottomSheetDialog.dismiss();
    }
}
