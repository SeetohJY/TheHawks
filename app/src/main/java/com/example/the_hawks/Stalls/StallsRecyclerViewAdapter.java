package com.example.the_hawks.Stalls;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.the_hawks.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StallsRecyclerViewAdapter extends RecyclerView.Adapter<StallsRecyclerViewAdapter.ExampleViewHolder> {

    private ArrayList<StallsItem> mStallsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        StallsItem currentItem = mStallsList.get(position);

        holder.mStallNameTextView.setText(currentItem.getStallName());
        holder.mStallCleanlinessTextView.setText(currentItem.getStallCleanliness());

    }

    @Override
    public int getItemCount() {
        return mStallsList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mStallNameTextView;
        public TextView mStallCleanlinessTextView;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mStallNameTextView = itemView.findViewById(R.id.stallsName);
            mStallCleanlinessTextView = itemView.findViewById(R.id.stallsCleanliness);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public StallsRecyclerViewAdapter(ArrayList<StallsItem> exampleList) {
        mStallsList = exampleList;
    }

}
