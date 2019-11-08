package com.example.the_hawks.Stalls;

import android.content.Intent;
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

import org.json.JSONArray;
import org.json.JSONException;

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


//    private OnFragmentInteractionListener mListener;

    public stallsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment stallsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static stallsFragment newInstance(String param1, String param2) {
        stallsFragment fragment = new stallsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stalls_fragment, container, false);

//        String datatext = getArguments().getString("stallstext");
        ArrayList<HawkerStall> hawkerStall = getArguments().getParcelableArrayList("HawkerStalls");

        buildRecyclerView(view);
        loopingTextViewCreate(view,hawkerStall);


        return view;

    }

//     TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }



    public void buildRecyclerView(View v){
        mRecyclerView = v.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new StallsRecyclerViewAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void createStallList(View view, String stallsData){

        JSONArray stallsdata = new JSONArray();

        try {
            stallsdata = new JSONArray(stallsData);
        } catch(JSONException err){
            Log.e("Error", err.toString());
        }


//        exampleList.add(new ExampleItem("Line 1", "Line 2"));
//        exampleList.add(new ExampleItem("Line 3", "Line 4"));
//        exampleList.add(new ExampleItem("Line 5", "Line 6"));
//        loopingTextViewCreate(view, stallsdata);
    }

    public void loopingTextViewCreate(View v, ArrayList<HawkerStall> hawkerStalls){

        Log.e("Test", "LoopingText running");

//        LinearLayout stall_vertical = (LinearLayout) v.findViewById(R.id.stall_layout);
//        for (int i = 0; i < stallsdata.length(); i++) {
//            String name = new String();
//            String rating = new String();
//            try {
//                JSONObject temp = stallsdata.getJSONObject(i);
//                name = temp.getString("name");
//                rating = temp.getString("rating");
//            } catch(JSONException err){
//                Log.e("Error", err.toString());
//            }
//            Log.e("name", name);
//            Log.e("rating", rating);
//            exampleList.add(new StallsItem(name, rating));
//
//        }

        for (int i = 0; i < hawkerStalls.size(); i++) {
            HawkerStall tempHS = hawkerStalls.get(i);
            exampleList.add(new StallsItem(tempHS.getStallName(), tempHS.getHygieneRating()));

        }
    }

    public void startStallItems (int position) {
        StallsItem temp = exampleList.get(position);

        Intent intent = new Intent(getActivity(), StallDesc.class);
        intent.putExtra("Name", temp.getText1());
        startActivity(intent);
    }
}
