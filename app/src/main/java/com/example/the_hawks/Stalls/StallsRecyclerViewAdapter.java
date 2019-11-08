package com.example.the_hawks.Stalls;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_hawks.R;

import java.util.ArrayList;

public class StallsRecyclerViewAdapter extends RecyclerView.Adapter<StallsRecyclerViewAdapter.ExampleViewHolder> {

    private ArrayList<StallsItem> mStallsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
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

        String name = currentItem.getStallName();
        String cleanliness = currentItem.getStallCleanliness();
        Log.d("e", cleanliness);

        holder.mStallNameTextView.setText(name);
        holder.mStallCleanlinessTextView.setText(cleanliness);
        switch(cleanliness) {
            case "A":
                // green
                holder.mStallCleanlinessTextView.setTextColor(Color.parseColor("#417F0C"));
                break;
            case "B":
                // orange
                holder.mStallCleanlinessTextView.setTextColor(Color.parseColor("#D29900"));
                break;
            case "C":
                // orangier
                holder.mStallCleanlinessTextView.setTextColor(Color.parseColor("#DE8B0C"));
                break;
            case "D":
                // red
                holder.mStallCleanlinessTextView.setTextColor(Color.parseColor("#D4370A"));
                break;
            default:
                holder.mStallCleanlinessTextView.setTextColor(Color.parseColor("#FFFFFF"));
                Log.d("e", "oi why is it default switch ah");
                break;

        }

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
