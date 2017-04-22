package com.dk.umwerktestapp.ui.user;

import android.content.Context;

import com.dk.umwerktestapp.data.NetworkComponent;
import com.dk.umwerktestapp.utils.scope.CustomScope;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialDialogListener;

import dagger.Component;

/**
 * Created by David on 22-Apr-17.
 */

@CustomScope
@Component(dependencies = NetworkComponent.class, modules = UserDetailModule.class)
public interface UserDetailComponent {

    Context context();

    MaterialDialogListener listener();

    void inject(UserDetailActivity activity);
}