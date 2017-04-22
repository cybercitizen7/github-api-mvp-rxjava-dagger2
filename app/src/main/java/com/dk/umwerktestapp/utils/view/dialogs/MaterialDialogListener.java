package com.dk.umwerktestapp.utils.view.dialogs;

import android.support.annotation.NonNull;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

/**
 * Created by David on 22-Apr-17.
 */

public interface MaterialDialogListener {
    void onInput(MaterialDialog dialog, CharSequence input);

    void onCloseApp(@NonNull MaterialDialog dialog, @NonNull DialogAction which);
}
