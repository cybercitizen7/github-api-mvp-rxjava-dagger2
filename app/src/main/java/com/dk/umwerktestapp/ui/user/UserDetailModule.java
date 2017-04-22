package com.dk.umwerktestapp.ui.user;

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
public class UserDetailModule {

    private final UserDetailMvp.View view;
    private final Context context;
    private final MaterialDialogListener listener;

    public UserDetailModule(UserDetailMvp.View view, Context context,
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

    @Provides @CustomScope UserDetailMvp.View providesMainScreenContractView() {
        return view;
    }

    @Provides @CustomScope UserDetailMvp.Presenter providePresenter(UserDetailMvp.View view,
                UserApiService userApiService) {
        return new UserDetailPresenter(view, userApiService);
    }

    @Provides @CustomScope MaterialProgressDialog provideProgressDialog(Context context,
                MaterialDialogListener listener) {
        return new MaterialProgressDialog(context, listener);
    }
}
