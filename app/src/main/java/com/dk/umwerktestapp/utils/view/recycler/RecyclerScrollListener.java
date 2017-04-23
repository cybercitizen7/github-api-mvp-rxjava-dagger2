package com.dk.umwerktestapp.utils.view.recycler;

import android.support.v7.widget.RecyclerView;

/**
 * Created by David on 23-Apr-17.
 */

public class RecyclerScrollListener extends RecyclerView.OnScrollListener {

    private final RecyclerStateListener listener;

    public RecyclerScrollListener(RecyclerStateListener listener) {
        this.listener =listener;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            listener.onScroll(recyclerView, dx, dy);
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        listener.onScrollStateChanged(recyclerView, newState);

        super.onScrollStateChanged(recyclerView, newState);
    }
}
