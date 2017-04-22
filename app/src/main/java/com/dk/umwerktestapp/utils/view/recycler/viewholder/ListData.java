package com.dk.umwerktestapp.utils.view.recycler.viewholder;

import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import java.util.List;

/**
 * Created by David on 22-Apr-17.
 */

public class ListData<T> {

    @Nullable
    private final DiffUtil.DiffResult diffResult;
    private final List<T> items;

    public ListData(@Nullable DiffUtil.DiffResult diffResult, List<T> items) {
        this.diffResult = diffResult;
        this.items = items;
    }

    @Nullable public DiffUtil.DiffResult getDiffResult() {
        return diffResult;
    }

    public List<T> getItems() {
        return items;
    }
}