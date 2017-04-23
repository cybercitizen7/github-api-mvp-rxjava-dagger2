package com.dk.umwerktestapp.utils.view.recycler;

import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

/**
 * Created by David on 22-Apr-17.
 */

public interface RecyclerStateListener {
    void onRowClick(ImageView imageView, int position);

    void onScroll(RecyclerView view, int dx, int dy);

    void onScrollStateChanged(RecyclerView view, int newState);
}
