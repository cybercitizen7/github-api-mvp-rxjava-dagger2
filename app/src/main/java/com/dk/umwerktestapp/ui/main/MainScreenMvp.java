package com.dk.umwerktestapp.ui.main;

import android.widget.ImageView;

import com.dk.umwerktestapp.ui.BaseView;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;

import java.util.List;

/**
 * Created by David on 20-Apr-17.
 */

public interface MainScreenMvp {

    interface View extends BaseView {
        void showUsers(List<UiBaseUser> items);

        void openUserDetails(UiBaseUser data, ImageView imageView);

        void showBlockingLoading();

        void hideBlockingLoading();

        void onIncorrectEntry();
    }

    interface Presenter {
        void loadUsers(String searchQuery);

        void onDestroy();
    }
}
