package com.example.the_hawks.HC;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.the_hawks.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HCFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HCFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HCFragment extends Fragment {
    ArrayList<HCItem> exampleList = new ArrayList<>();
    RecyclerView mRecyclerView;
    HCRecyclerViewAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;


//    private OnFragmentInteractionListener mListener;

    public HCFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HCFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HCFragment newInstance(String param1, String param2) {
        HCFragment fragment = new HCFragment();
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
        View view = inflater.inflate(R.layout.fragment_hc, container, false);

        String datatext = getArguments().getString("hctext");

        buildRecyclerView(view);
        createHCList(view, datatext);


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
        mAdapter = new HCRecyclerViewAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new HCRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startHCItems(position);
            }
        });
    }

    public void createHCList(View view, String hcData){

        JSONArray hcdata = new JSONArray();

        try {
            hcdata = new JSONArray(hcData);
        } catch(JSONException err){
            Log.e("Error", err.toString());
        }


//        exampleList.add(new ExampleItem("Line 1", "Line 2"));
//        exampleList.add(new ExampleItem("Line 3", "Line 4"));
//        exampleList.add(new ExampleItem("Line 5", "Line 6"));
        loopingTextViewCreate(view, hcdata);
    }

    public void loopingTextViewCreate(View v, JSONArray hcdata){

        Log.e("Test", "LoopingText running");

//        LinearLayout HC_vertical = (LinearLayout) v.findViewById(R.id.HC_layout);
        for (int i = 0; i < hcdata.length(); i++) {
            String name = new String();
            String rating = new String();
            try {
                JSONObject temp = hcdata.getJSONObject(i);
                name = temp.getString("name");
                rating = temp.getString("rating");
            } catch(JSONException err){
                Log.e("Error", err.toString());
            }
            Log.e("name", name);
            Log.e("rating", rating);
            exampleList.add(new HCItem(name, rating));
//            CardView cardView = new CardView(getActivity());
//            cardView.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
//
//
//            TextView text = new TextView(getActivity());
//            text.setLayoutParams(new LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
//            text.setText(name);
//            HC_vertical.addView(text);

        }
    }

    public void startHCItems (int position) {
        HCItem temp = exampleList.get(position);

        Intent intent = new Intent(getActivity(), HCDesc.class);
        intent.putExtra("Name", temp.getText1());
        startActivity(intent);
    }
}
