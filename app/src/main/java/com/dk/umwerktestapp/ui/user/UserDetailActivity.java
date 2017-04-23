package com.dk.umwerktestapp.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dk.umwerktestapp.App;
import com.dk.umwerktestapp.R;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;
import com.dk.umwerktestapp.ui.user.model.UiUserData;
import com.dk.umwerktestapp.utils.format.DateFormatter;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialDialogListener;
import com.dk.umwerktestapp.utils.view.dialogs.MaterialProgressDialog;
import com.dk.umwerktestapp.utils.view.dialogs.Toaster;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by David on 20-Apr-17.
 */

public class UserDetailActivity extends Activity implements UserDetailMvp.View, MaterialDialogListener{

    public static final String EXTRA_USER_NAME = UserDetailActivity.class.getCanonicalName() + ".userName";
    public static final String EXTRA_TRANSITION_NAME = UserDetailActivity.class.getCanonicalName() + ".transitionName";

    @BindView(R.id.tv_followers)TextView tvFollowers;
    @BindView(R.id.tv_registration_date) TextView tvCreatedAt;
    @BindView(R.id.tv_company) TextView tvCompany;
    @BindView(R.id.tv_location) TextView tvLocation;
    @BindView(R.id.iw_avatar) ImageView iwAvatar;
    @BindView(R.id.tv_username) TextView tvUsername;
    @BindView(R.id.iw_email) ImageView iwEmail;
    @BindView(R.id.iw_website) ImageView iwWebsite;

    @Inject UserDetailMvp.Presenter presenter;
    @Inject MaterialProgressDialog progressDialog;

    private UiUserData userData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        ButterKnife.bind(this);
        initializeDagger();
        progressDialog.showBlockingLoading();
        AndroidThreeTen.init(this);

        Bundle bundle = getIntent().getExtras();

        UiBaseUser baseData;
        String transitionName;
        if (bundle != null) {
            baseData = Parcels.unwrap(bundle.getParcelable(EXTRA_USER_NAME));
            transitionName = bundle.getString(EXTRA_TRANSITION_NAME);
            presenter.onCreate(baseData);
        } else {
            throw new IllegalStateException("Initial bundle in UserDetail Activity is null");
        }

        iwAvatar.setTransitionName(transitionName);

        tvUsername.setText(baseData.getLogin());
        setAvatarImage(baseData.getAvatarUrl());
    }

    @OnClick(R.id.iw_email) void emailClicked() {
        if (userData.getEmail() != null) {
            openEmailIntent(userData.getEmail());
        }
    }

    @OnClick(R.id.iw_website) void websiteClicked() {
        if(userData.getHtmlUrl() != null) {
            openProfileUrl(userData.getHtmlUrl());
        }
    }

    @OnClick(R.id.location) void locationClicked() {
        if (userData.getLocation() != null) {
            openGoogleMapsLocation(userData.getLocation());
        }
    }

    private void openGoogleMapsLocation(String location) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + location);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void onBackPressed() {
        onDestroy();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        finishAfterTransition();
        super.onDestroy();
    }

    @Override
    public void showUserDetails(UiUserData items) {
        this.userData = items;
        tvFollowers.setText(items.getFollowers());

        String localDateTime = DateFormatter.toFriendlyLongDateTimeString(items.getCreatedAt());
        tvCreatedAt.append(localDateTime);

        if (userData.getHtmlUrl() == null) {
            iwWebsite.setClickable(false);
            iwWebsite.setImageAlpha(50);
        }

        if (userData.getEmail() == null) {
            iwEmail.setClickable(false);
            iwEmail.setImageAlpha(50);
        }

        if (userData.getCompany() == null) {
            tvCompany.setText(R.string.user_detail_no_company);
        } else {
            tvCompany.setText(userData.getCompany());
        }

        if (userData.getLocation() == null){
            tvLocation.setText(R.string.user_detail_no_company);
        } else {
            tvLocation.setText(userData.getLocation());
        }
    }

    @Override
    public void hideBlockingLoading() {
        progressDialog.hideBlockingLoading();
    }

    private void setAvatarImage(String avatarUrl) {
        Glide.with(this).load(Uri.parse(avatarUrl)).asBitmap().centerCrop()
                .into(new BitmapImageViewTarget(iwAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(getApplicationContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iwAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    private void openEmailIntent(String email) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType(getString(R.string.user_detail_email_type));
        i.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
        try {
            startActivity(i);
        } catch (android.content.ActivityNotFoundException ex) {
            Toaster.showErrorToast(this, getString(R.string.error_message_no_email));
        }
    }

    private void openProfileUrl(String url) {
        Intent i= new Intent(Intent.ACTION_VIEW,Uri.parse(url));
        startActivity(i);
    }

    private void initializeDagger() {
        DaggerUserDetailComponent.builder()
                .networkComponent(((App) getApplicationContext()).getNetworkComponent())
                .userDetailModule(new UserDetailModule(this, this, this))
                .build().inject(this);
    }

    @NonNull
    public static Intent getIntent(Context context, UiBaseUser data, String transitionName) {
        return new Intent(context, UserDetailActivity.class)
                .putExtra(EXTRA_USER_NAME, Parcels.wrap(data))
                .putExtra(EXTRA_TRANSITION_NAME, transitionName);
    }

    @Override
    public void onInput(MaterialDialog dialog, CharSequence input) {
        // DO NOTHING
    }

    @Override
    public void onCloseApp(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        // DO NOTHING
    }

    @Override
    public void showErrorMessage(String message) {
        Toaster.showErrorToast(this, message);
    }
}
