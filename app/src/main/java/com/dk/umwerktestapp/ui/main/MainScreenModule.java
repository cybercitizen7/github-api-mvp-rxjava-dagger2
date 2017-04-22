package com.dk.umwerktestapp.ui.main;

import android.content.Context;

import com.dk.umwerktestapp.data.api.service.UserApiService;
import com.dk.umwerktestapp.utils.scope.CustomScope;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialDialogListener;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialProgressDialog;

import dagger.Module;
import dagger.Provides;

/**
 * Created by David on 20-Apr-17.
 */
@Module
public class MainScreenModule {

    private final MainScreenMvp.View view;
    private final Context context;
    private final MaterialDialogListener listener;

    public MainScreenModule(MainScreenMvp.View view, Context context,
                            MaterialDialogListener listener) {
        this.view = view;
        this.context = context;
        this.listener = listener;
    }

    @Provides @CustomScope Context provideContext() {
        return context;
    }

    @Provides @CustomScope MaterialDialogListener provideListener() {
        return listener;
    }

    @Provides @CustomScope MaterialProgressDialog provideProgressDialog(Context context, MaterialDialogListener listener) {
        return new MaterialProgressDialog(context, listener);
    }

    @Provides @CustomScope MainScreenMvp.View providesMainScreenContractView() {
        return view;
    }

    @Provides @CustomScope MainScreenMvp.Presenter providePresenter(MainScreenMvp.View view,
                    UserApiService userApiService) {
        return new MainScreenPresenter(view, userApiService);
    }
}
