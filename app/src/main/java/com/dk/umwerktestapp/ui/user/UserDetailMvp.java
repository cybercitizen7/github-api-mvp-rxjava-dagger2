package com.dk.umwerktestapp.ui.user;

import com.dk.umwerktestapp.ui.BaseView;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;
import com.dk.umwerktestapp.ui.user.model.UiUserData;

/**
 * Created by David on 20-Apr-17.
 */

public interface UserDetailMvp {

    interface View extends BaseView {
        void showUserDetails(UiUserData items);

        void hideBlockingLoading();
    }

    interface Presenter {
        void onCreate(UiBaseUser data);

        void onDestroy();
    }
}
