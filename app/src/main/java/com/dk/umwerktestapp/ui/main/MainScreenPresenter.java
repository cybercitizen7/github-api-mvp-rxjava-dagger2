package com.dk.umwerktestapp.ui.main;

import com.dk.umwerktestapp.data.api.response.user.UserSearchResponse;
import com.dk.umwerktestapp.data.api.response.user.UserSearchResponses;
import com.dk.umwerktestapp.data.api.service.UserApiService;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;

import java.util.List;

import rx.Observable;
import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Created by David on 20-Apr-17.
 */

public class MainScreenPresenter implements MainScreenMvp.Presenter {

    private final UserApiService userApiService;
    private final MainScreenMvp.View view;
    private Subscription userSub;

    MainScreenPresenter(MainScreenMvp.View view, UserApiService userApiService) {
        this.view = view;
        this.userApiService = userApiService;
    }

    @Override
    public void loadUsers(String searchQuery) {
        userSub = userApiService.getJavaUsers("language:" + searchQuery,"10")
                .map(UserSearchResponses::getOwners)
                .toObservable()
                .flatMap(new Func1<UserSearchResponse[], Observable<UiBaseUser>>() {
                    @Override
                    public Observable<UiBaseUser> call(UserSearchResponse[] userResponses) {
                        return Observable.from(userResponses)
                                .map(userResponse -> new UiBaseUser(
                                        userResponse.getLogin(),
                                        userResponse.getAvatarUrl(),
                                        userResponse.getId()
                                ));
                    }
                })
                .toList()
                .toSingle()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleSubscriber<List<UiBaseUser>>() {
                    @Override
                    public void onSuccess(List<UiBaseUser> value) {
                        view.showUsers(value);
                        view.hideBlockingLoading();
                    }

                    @Override
                    public void onError(Throwable error) {
                        if (error.getMessage().contains("Unprocessable Entity")) {
                            view.onIncorrectEntry();
                        } else {
                            view.showErrorMessage(error.getMessage());
                        }
                        view.hideBlockingLoading();
                    }
                });
    }

    @Override
    public void onDestroy() {
        if (userSub != null) {
            userSub.unsubscribe();
        }
    }
}
