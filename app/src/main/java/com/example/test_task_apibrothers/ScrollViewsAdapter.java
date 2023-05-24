package com.example.test_task_apibrothers;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_task_apibrothers.databinding.ScrollItemBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ScrollViewsAdapter extends RecyclerView.Adapter<ScrollViewsAdapter.ScrollViewHolder>{

    private final List<DataBox> scrollImagesList = new ArrayList<>();

    public void setItem(List<DataBox> scrollImagesList) {
        this.scrollImagesList.addAll(scrollImagesList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ScrollViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        final ScrollItemBinding binding = ScrollItemBinding.inflate(inflater, parent, false);
        int dataPosition = viewType %   scrollImagesList.size();
        return new ScrollViewHolder(binding, scrollImagesList);
    }

    @Override
    public void onBindViewHolder(@NonNull ScrollViewHolder holder, int position) {
        int dataPosition = position % scrollImagesList.size();
        DataBox dataBox = scrollImagesList.get(dataPosition);
        holder.bind(dataBox, dataPosition);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    public static class ScrollViewHolder extends RecyclerView.ViewHolder {
        private final ScrollItemBinding binding;
        private final List<DataBox> dataBoxList;
        int index;

        private ScrollViewHolder(ScrollItemBinding binding, List<DataBox> scrollImagesList) {
            super(binding.getRoot());
            this.binding = binding;
            dataBoxList = new ArrayList<>(scrollImagesList);
        }

        public void bind(DataBox image, int position) {
            binding.imageView.setImageResource(image.image);
            index = dataBoxList.get(position).index;
        }

        int getIndex() {
            return index;
        }
    }
}
