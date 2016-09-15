package com.aaronoe.android.spacelaunchmanifest;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Fragment to display additional info on the launch
 * Created by aaron on 9/14/2016.
 */
public class DetailpageDetailFragment extends Fragment {

    LaunchItem currentLaunch;

    public DetailpageDetailFragment(){

    }

    /**
     * Helper method to convert UNIX epoch time to date
     * @param dateObject of the launch
     * @return a formatted date
     */
    private String formatNetLaunchDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE,  MMMM dd,  HH:mm", Locale.ENGLISH);
        return dateFormat.format(dateObject);
    }

    private String formatWindowTime(long launchWindowTime){

        if (launchWindowTime == 0) {
            return "n/a";
        } else {

            launchWindowTime *= 1000;
            Date dateObject = new Date(launchWindowTime);

            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
            return dateFormat.format(dateObject);

        }
    }

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detailpage_detailtab, container, false);

       currentLaunch = (LaunchItem) getArguments().getSerializable("thisLaunch");

       // ----------------------------------------------------------
       // Launch Status
       int launchStatus = currentLaunch.getmLaunchStatus();
       TextView launchStatusTextView = (TextView) rootView.findViewById(R.id.launch_status);

       switch(launchStatus){
           case 1:
               launchStatusTextView.setText(R.string.launch_is_go);
               launchStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.successful));
               break;
           case 2:
               launchStatusTextView.setText(R.string.launch_is_no_go);
               launchStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.unsuccessful));
               break;
           case 3:
               launchStatusTextView.setText(R.string.launch_success);
               launchStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.successful));
               break;
           case 4:
               launchStatusTextView.setText(R.string.launch_failed);
               launchStatusTextView.setTextColor(ContextCompat.getColor(getContext(), R.color.unsuccessful));
               break;
       }


       // ----------------------------------------------------------
       // Map Image View
       ImageView mDetailMapImageView = (ImageView) rootView.findViewById(R.id.detail_map_picture_view);

       final double latitude = currentLaunch.getmLaunchPadLatitude();
       final double longitude = currentLaunch.getmLaunchPadLongitude();

       String locationUrl =
               "http://maps.google.com/maps/api/staticmap?center=" +latitude + "," + longitude +
                       "&zoom=8" +
                       "&scale=1" +
                       "&size=500x320" +
                       "&sensor=false" +
                       "&maptype=hybrid" +
                       "&markers=color:red%7Clabel:%7C" +latitude +"," +longitude;

       Picasso.with(getContext()).load(locationUrl).into(mDetailMapImageView);

       // ----------------------------------------------------------
       // Open GMaps on click FAB

       FloatingActionButton mapsButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
       final String launchPadName = currentLaunch.getmLaunchPadName();

       mapsButton.setOnClickListener(new View.OnClickListener(){
           public void onClick(View v){

               Intent intent = new Intent(Intent.ACTION_VIEW,
                       Uri.parse("geo:" + latitude + "," + longitude + "?q=" + latitude + "," + longitude + "("+ launchPadName +")"));
               // Make the Intent explicit by setting the Google Maps package
               intent.setPackage("com.google.android.apps.maps");

               // Attempt to start an activity that can handle the Intent
               getContext().startActivity(intent);
           }
       });

       // ----------------------------------------------------------
       // Get Date Object for projected launch date

       long netLaunchLong = currentLaunch.getmNetLaunchDate();
       String timeToDisplay = "n/a";
       if (netLaunchLong != 0){

           netLaunchLong *= 1000;
           Date dateObject = new Date(netLaunchLong);

           timeToDisplay = formatNetLaunchDate(dateObject);
       }

       TextView detailLaunchDate = (TextView) rootView.findViewById(R.id.full_launch_date);
       detailLaunchDate.setText(timeToDisplay);

       // ----------------------------------------------------------
       // Get Date Objects for window start and close

       long launchWindowStart = currentLaunch.getmLaunchWindowOpen();
       long launchWindowClose = currentLaunch.getmLaunchWindowClose();

       TextView detailLaunchWindowOpen = (TextView) rootView.findViewById(R.id.detail_window_open);
       TextView detailLaunchWindowClose = (TextView) rootView.findViewById(R.id.detail_window_close);

       detailLaunchWindowOpen.setText("Window Start: " + formatWindowTime(launchWindowStart));
       detailLaunchWindowClose.setText("Window Close: " + formatWindowTime(launchWindowClose));

       // ----------------------------------------------------------
       // Watch Live Button

       Button watchLiveButton = (Button) rootView.findViewById(R.id.watch_live_button);
       final String liveVideoUrl = currentLaunch.getmMediaUrl();

       if(liveVideoUrl != null){
           watchLiveButton.setVisibility(View.VISIBLE);
           watchLiveButton.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Uri liveMediaUri = Uri.parse(liveVideoUrl);
                   // Create a new intent to view the earthquake URI
                   Intent websiteIntent = new Intent(Intent.ACTION_VIEW, liveMediaUri);

                   // Send the intent to launch a new activity
                   v.getContext().startActivity(websiteIntent);
               }
           });
       } else {
           watchLiveButton.setVisibility(View.GONE);
       }


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
