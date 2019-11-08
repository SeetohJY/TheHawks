package com.example.the_hawks.HC;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_hawks.HawkerCentre;
import com.example.the_hawks.HawkerCentreActivity;
import com.example.the_hawks.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link /*HCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * {@link HCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HCFragment extends Fragment {
    ArrayList<HCItem> exampleList = new ArrayList<>();
    RecyclerView mRecyclerView;
    HCRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    ArrayList<HawkerCentre> hawkerCentreArrayList = new ArrayList<>();

    private static WeakReference<HC> mActivityRef;
    public static void updateActivity(HC activity) {
        mActivityRef = new WeakReference<>(activity);
    }

    public HCFragment() {
        // Required empty public constructor
    }

    //Factory method to create a new instance of this fragment using the provided parameters.
    // @return A new instance of fragment HCFragment.
    public static HCFragment newInstance() {
        HCFragment fragment = new HCFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HC activity = mActivityRef.get();
        hawkerCentreArrayList = activity.getData();
    }

    //Inflate layout for fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hc, container, false);
        buildRecyclerView(view);
        loopingTextViewCreate(view, hawkerCentreArrayList);

        return view;

    }

    private void buildRecyclerView(View v){
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new HCRecyclerViewAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HCRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startHawkerCentreActivity(position);
            }
        });
    }


    private void loopingTextViewCreate(View v, ArrayList<HawkerCentre> hcList){

        Log.e("Test", "LoopingText running");

        for (int i = 0; i < hcList.size(); i++) {
            HawkerCentre hc = hcList.get(i);
            exampleList.add(new HCItem( hc.getName(), hc.getAddress(), Double.toString(hc.getAggregate())));
        }
    }

    private void startHawkerCentreActivity (int position) {
        HCItem temp = exampleList.get(position);

        Intent intent = new Intent(getActivity(), HawkerCentreActivity.class);
        intent.putExtra("HawkerCentreActivity",hawkerCentreArrayList.get(position));

        Log.e("Start", "StartHawkerCentreActivity");
        startActivity(intent);
    }
}
