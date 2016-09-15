package com.example.android.spacelaunchmanifest;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by aaron on 9/14/2016.
 */
public class DetailpageDetailFragment extends Fragment {

    LaunchItem currentLaunch;

    public DetailpageDetailFragment(){

    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detailpage_detailtab, container, false);

       currentLaunch = (LaunchItem) getArguments().getSerializable("thisLaunch");

       // ----------------------------------------------------------
       // Map Image View
       ImageView mDetailMapImageView = (ImageView) rootView.findViewById(R.id.detail_map_picture_view);

       final double latitude = currentLaunch.getmLaunchPadLatitude();
       final double longitude = currentLaunch.getmLaunchPadLongitude();

       String locationUrl =
               "http://maps.google.com/maps/api/staticmap?center=" +latitude + "," + longitude +
                       "&zoom=8" +
                       "&scale=2" +
                       "&size=375x195" +
                       "&sensor=false" +
                       "&maptype=hybrid" +
                       "&markers=color:red%7Clabel:%7C" +latitude +"," +longitude;

       Picasso.with(getContext()).load(locationUrl).into(mDetailMapImageView);

       // ----------------------------------------------------------

       return rootView;
    }


    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance(LaunchItem mCurrentLaunch) {
        DetailpageDetailFragment myDetailFragment = new DetailpageDetailFragment();

        Bundle launch = new Bundle();
        launch.putSerializable("thisLaunch", mCurrentLaunch);
        myDetailFragment.setArguments(launch);

        return myDetailFragment;
    }

}
