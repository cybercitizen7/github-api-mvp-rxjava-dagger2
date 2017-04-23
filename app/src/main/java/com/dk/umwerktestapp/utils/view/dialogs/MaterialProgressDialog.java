package com.dk.umwerktestapp.utils.view.dialogs;

import android.content.Context;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.dk.umwerktestapp.R;

/**
 * Created by David on 22-Apr-17.
 */

public class MaterialProgressDialog {

    private final MaterialDialog materialDialog;
    private final Context context;
    private final MaterialDialogListener listener;

    public MaterialProgressDialog(Context context, MaterialDialogListener listener) {
        this.context = context;
        this.listener = listener;
        materialDialog = buildProgressDialog(context).build();
    }

    public void showBlockingLoading() {
        materialDialog.show();
    }

    public void hideBlockingLoading() {
        if (materialDialog != null) {
            materialDialog.dismiss();
        }
    }

    private static MaterialDialog.Builder buildProgressDialog(Context context) {
        return new MaterialDialog.Builder(context)
                .title(R.string.progress_dialog_title)
                .progress(true, 0)
                .cancelable(false);
    }


    public void showChooserDialog() {
        new MaterialDialog.Builder(context)
                .cancelable(false)
                .title(R.string.chooser_dialog_title)
                .inputType(InputType.TYPE_CLASS_TEXT)
                .inputRangeRes(1, 10, R.color.colorOrangeRed)
                .input(context.getString(R.string.chooser_dialog_hint), null,
                        listener::onInput).show();
    }


    public void showErrorDialog() {
        new MaterialDialog.Builder(context)
                .title(R.string.error_message_error)
                .content(R.string.error_message_no_internet)
                .positiveText(R.string.dialog_ok)
                .cancelable(false)
                .onPositive(listener::onCloseApp)
                .show();
    }
}
