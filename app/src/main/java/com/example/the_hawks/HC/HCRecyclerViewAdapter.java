package com.example.the_hawks.HC;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.the_hawks.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HCRecyclerViewAdapter extends RecyclerView.Adapter<HCRecyclerViewAdapter.ExampleViewHolder> {

    private ArrayList<HCItem> mHCList;
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_hc, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        HCItem currentItem = mHCList.get(position);

        holder.mHCNameTextView.setText(currentItem.getHCName());
        holder.mHCAddressTextView.setText(currentItem.getHCAddress());
        holder.mHCCleanlinessView.setText(currentItem.getHCCleanliness());

    }

    @Override
    public int getItemCount() {
        return mHCList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mHCNameTextView;
        public TextView mHCAddressTextView;
        public TextView mHCCleanlinessView;

        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mHCNameTextView = itemView.findViewById(R.id.hcName);
            mHCAddressTextView = itemView.findViewById(R.id.hcAddress);
            mHCCleanlinessView = itemView.findViewById(R.id.hcCleanliness);

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

    public HCRecyclerViewAdapter(ArrayList<HCItem> exampleList) {
        mHCList = exampleList;
    }

}
