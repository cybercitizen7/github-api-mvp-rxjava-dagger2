package com.dk.umwerktestapp.ui.main.list.layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.dk.umwerktestapp.R;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by David on 21-Apr-17.
 */
public class UserListLayout extends RelativeLayout {

    private final Context context;

    @BindView(R.id.tv_username) TextView tvUsername;
    @BindView(R.id.tv_id) TextView tvId;
    @BindView(R.id.iw_avatar) ImageView iwAvatar;

    public UserListLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bind(UiBaseUser item) {
        tvUsername.setText(item.getLogin());
        if (tvId.getText().length() > 3) {
            tvId.setText(R.string.user_detail_id);
        }
        tvId.append(String.valueOf(item.getId()));

        Glide.with(context).load(Uri.parse(item.getAvatarUrl())).asBitmap().centerCrop().into(new BitmapImageViewTarget(iwAvatar) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                iwAvatar.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    public ImageView getIwAvatar() {
        return iwAvatar;
    }
}
