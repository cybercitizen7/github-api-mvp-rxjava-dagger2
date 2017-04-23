package com.dk.umwerktestapp.ui.main.list;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dk.umwerktestapp.R;
import com.dk.umwerktestapp.ui.main.list.layout.UserListLayout;
import com.dk.umwerktestapp.ui.main.model.UiBaseUser;
import com.dk.umwerktestapp.utils.view.recycler.RecyclerStateListener;
import com.dk.umwerktestapp.utils.view.recycler.adapter.BaseAdapter;

/**
 * Created by David on 20-Apr-17.
 */

public class UsersAdapter extends BaseAdapter<UiBaseUser, UsersAdapter.UserVH> {

    private final LayoutInflater layoutInflater;
    private RecyclerStateListener listener;

    public UsersAdapter(Context context, RecyclerStateListener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public UserVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserVH(layoutInflater.inflate(R.layout.item_users, parent, false));
    }

    @Override
    public void onBindViewHolder(UsersAdapter.UserVH holder, int position) {
        ViewCompat.setTransitionName(holder.iWAvatar, "image_view_" + position);
        holder.onBind(getItem(position));
    }

    public class UserVH extends RecyclerView.ViewHolder implements View.OnClickListener{

        private UserListLayout layout;
        private ImageView iWAvatar;

        public UserVH(View itemView) {
            super(itemView);
            layout = (UserListLayout) itemView;
            iWAvatar = (ImageView) itemView.findViewById(R.id.iw_avatar);

            layout.setOnClickListener(this);
        }

        public void onBind(UiBaseUser item) {
            layout.bind(item);
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                ImageView iw = ((UserListLayout) itemView).getIwAvatar();
                listener.onRowClick(iw, getAdapterPosition());
            }
        }
    }
}
