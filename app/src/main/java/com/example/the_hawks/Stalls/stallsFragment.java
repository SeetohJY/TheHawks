package com.example.the_hawks.Stalls;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.the_hawks.HawkerStall;
import com.example.the_hawks.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link stallsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link stallsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class stallsFragment extends Fragment {

    ArrayList<StallsItem> exampleList = new ArrayList<>();
    RecyclerView mRecyclerView;
    StallsRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public stallsFragment() {
        // Required empty public constructor
    }


    //this factory method creates a new instance of this fragment using the provided parameters.
    public static stallsFragment newInstance(String param1, String param2) {
        stallsFragment fragment = new stallsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the layout for this fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.stalls_fragment, container, false);
        ArrayList<HawkerStall> hawkerStall = getArguments().getParcelableArrayList("HawkerStalls");
        buildRecyclerView(view);
        loopingTextViewCreate(view,hawkerStall);

        return view;

    }

    private void buildRecyclerView(View v){
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new StallsRecyclerViewAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void loopingTextViewCreate(View v, ArrayList<HawkerStall> hawkerStalls){

        Log.e("Test", "LoopingText running");
        for (int i = 0; i < hawkerStalls.size(); i++) {
            HawkerStall tempHS = hawkerStalls.get(i);
            exampleList.add(new StallsItem(tempHS.getStallName(), tempHS.getHygieneRating()));
        }
    }

}
