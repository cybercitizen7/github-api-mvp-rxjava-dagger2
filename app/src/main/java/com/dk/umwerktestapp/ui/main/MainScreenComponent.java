package com.dk.umwerktestapp.ui.main;

import android.content.Context;

import com.dk.umwerktestapp.data.NetworkComponent;
import com.dk.umwerktestapp.utils.scope.CustomScope;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialDialogListener;

import dagger.Component;

/**
 * Created by David on 20-Apr-17.
 */

@CustomScope
@Component(dependencies = NetworkComponent.class, modules = MainScreenModule.class)
public interface MainScreenComponent {

    Context context();

    MaterialDialogListener listener();

    void inject(MainScreenActivity activity);
}
