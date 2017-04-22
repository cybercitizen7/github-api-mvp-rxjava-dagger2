package com.dk.umwerktestapp.utils.view.recycler.adapter;

import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import com.dk.umwerktestapp.utils.view.recycler.viewholder.ListData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 22-Apr-17.
 */

public abstract class BaseAdapter<TData, TViewHolder extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<TViewHolder> {

    protected final List<TData> data = new ArrayList<>();

    public void setData(List<TData> list) {
        data.clear();

        if (list != null) {
            data.addAll(list);
        }

        notifyDataSetChanged();
    }

    public void setData(@NonNull ListData<TData> listData) {
        if (listData.getDiffResult() != null) {
            dispatchDiffUpdates(listData.getDiffResult());
        } else {
            if (data.size() > 0) {
                notifyItemRangeRemoved(0, data.size());
            }

            notifyItemRangeInserted(0, listData.getItems().size());
        }

        setItemQuiet(listData.getItems());
    }

    public void setItemQuiet(@NonNull List<TData> listData) {
        data.clear();
        data.addAll(listData);
    }

    protected void dispatchDiffUpdates(@NonNull DiffUtil.DiffResult diffResult) {
        diffResult.dispatchUpdatesTo(this);
    }

    public void appendItem(TData item) {
        int preSize = data.size();
        data.add(item);
        notifyItemInserted(preSize);
    }

    public void prependItems(List<TData> items) {
        data.addAll(0, items);
        notifyItemRangeInserted(0, items.size());
    }

    public void setItem(int index, TData item) {
        data.set(index, item);
        notifyItemChanged(index);
    }

    public void removeItem(int index) {
        data.remove(index);
        notifyItemRemoved(index);
    }

    protected TData getItem(int position) {
        if (position < data.size()) {
            return data.get(position);
        } else {
            return null;
        }
    }

    @Override public int getItemCount() {
        return data.size();
    }
}
