package com.example.the_hawks.NearbyHC;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.the_hawks.NearbyHC.NearbyHCItem;
import com.example.the_hawks.NearbyHC.NearbyHCRecyclerViewAdapter;
import com.example.the_hawks.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NearbyHCRecyclerViewAdapter extends RecyclerView.Adapter<NearbyHCRecyclerViewAdapter.ExampleViewHolder>  {


    private ArrayList<NearbyHCItem> mNearbyHCList;
    private NearbyHCRecyclerViewAdapter.OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(NearbyHCRecyclerViewAdapter.OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public NearbyHCRecyclerViewAdapter.ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_nearby_hc, parent, false);
        NearbyHCRecyclerViewAdapter.ExampleViewHolder evh = new NearbyHCRecyclerViewAdapter.ExampleViewHolder(v, mListener);

        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull NearbyHCRecyclerViewAdapter.ExampleViewHolder holder, int position) {
        NearbyHCItem currentItem = mNearbyHCList.get(position);

        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());
        holder.mTextView4.setText(currentItem.getText2());

    }

    @Override
    public int getItemCount() {
        return mNearbyHCList.size();
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder{
        public TextView mTextView1;
        public TextView mTextView2;
        public TextView mTextView4;

        public ExampleViewHolder(@NonNull View itemView, final NearbyHCRecyclerViewAdapter.OnItemClickListener listener) {
            super(itemView);
            mTextView1 = itemView.findViewById(R.id.Text1);
            mTextView2 = itemView.findViewById(R.id.Text2);
            mTextView4 = itemView.findViewById(R.id.text4);

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

    public NearbyHCRecyclerViewAdapter(ArrayList<NearbyHCItem> exampleList) {
        mNearbyHCList = exampleList;
    }
}
