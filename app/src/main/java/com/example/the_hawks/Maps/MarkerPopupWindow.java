package com.example.the_hawks.Maps;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.the_hawks.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MarkerPopupWindow implements GoogleMap.InfoWindowAdapter {

    private Context context;

    public MarkerPopupWindow(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.maps_marker_popup, null);

        TextView name_tv = view.findViewById(R.id.centrename);
        TextView details_tv = view.findViewById(R.id.details);
        TextView food_tv = view.findViewById(R.id.food);


        name_tv.setText(marker.getTitle());
        //details_tv.setText(marker.getSnippet());

        PopupInfo infoWindowData = (PopupInfo) marker.getTag();


        //food_tv.setText(infoWindowData.getFood());


        return view;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.maps_marker_popup, null);

        TextView name_tv = view.findViewById(R.id.centrename);
        TextView details_tv = view.findViewById(R.id.details);
        TextView food_tv = view.findViewById(R.id.food);


        name_tv.setText(marker.getTitle());
        //details_tv.setText(marker.getSnippet());

        PopupInfo infoWindowData = (PopupInfo) marker.getTag();


        //food_tv.setText(infoWindowData.getFood());


        return view;
    }
}

