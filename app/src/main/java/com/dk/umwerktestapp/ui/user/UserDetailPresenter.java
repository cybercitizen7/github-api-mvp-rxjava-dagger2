package com.dk.umwerktestapp.ui.user;

import com.dk.umwerktestapp.data.api.service.UserApiService;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;
import com.dk.umwerktestapp.ui.user.model.UiUserData;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by David on 20-Apr-17.
 */

public class UserDetailPresenter implements UserDetailMvp.Presenter{
    private final UserDetailMvp.View view;
    private final UserApiService userApiService;
    private Subscription userSubscription;

    UserDetailPresenter(UserDetailMvp.View view, UserApiService userApiService) {
        this.view = view;
        this.userApiService = userApiService;
    }

    @Override
    public void onCreate(UiBaseUser data) {
        if (data != null) {
            loadUserData(data.getLogin());
        } else {
            throw new IllegalStateException("UiBaseUser data is null!");
        }
    }

    @Override
    public void onDestroy() {
        if (userSubscription != null) {
            userSubscription.unsubscribe();
        }
    }

    private void loadUserData(String username) {
        userSubscription = userApiService.getUserData(username)
                .map(userResponse -> new UiUserData(
                        userResponse.getCreatedAt(),
                        userResponse.getEmail(),
                        userResponse.getFollowers(),
                        userResponse.getHtmlUrl(),
                        userResponse.getLocation(),
                        userResponse.getCompany()
                ))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<UiUserData>() {
                    @Override
                    public void onSuccess(UiUserData value) {
                        view.showUserDetails(value);
                        view.hideBlockingLoading();
                    }

                    @Override
                    public void onError(Throwable error) {
                        view.showErrorMessage(error.getMessage());
                        view.hideBlockingLoading();
                    }
                });
    }
}
