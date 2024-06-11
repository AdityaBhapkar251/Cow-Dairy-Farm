package com.bhapkar.dairyfarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bhapkar.dairyfarm.data.model.Cow;

import java.util.List;

public class CowAdapter extends RecyclerView.Adapter<CowAdapter.CowViewHolder> {

    private List<Cow> cowList;
    private OnCowClickListener onCowClickListener;

    public interface OnCowClickListener {
        void onCowClick(Cow cow);
    }

    public CowAdapter(List<Cow> cowList, OnCowClickListener onCowClickListener) {
        this.cowList = cowList;
        this.onCowClickListener = onCowClickListener;
    }

    @NonNull
    @Override
    public CowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cow, parent, false);
        return new CowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CowViewHolder holder, int position) {
        Cow cow = cowList.get(position);
        holder.cowName.setText(cow.getName());
        holder.itemView.setOnClickListener(v -> onCowClickListener.onCowClick(cow));
    }


    @Override
    public int getItemCount() {
        return cowList.size();
    }

    public static class CowViewHolder extends RecyclerView.ViewHolder {
        TextView cowName;

        public CowViewHolder(@NonNull View itemView) {
            super(itemView);
            cowName = itemView.findViewById(R.id.cow_name);
        }
    }
}
