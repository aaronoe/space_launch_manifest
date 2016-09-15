package com.example.android.spacelaunchmanifest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Contains more details about the mission
 * Created by aaron on 9/14/2016.
 */
public class DetailpageMissionFragment extends Fragment {

    LaunchItem mCurrentLaunch;

    public DetailpageMissionFragment(){
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detailpage_mission, container, false);

        mCurrentLaunch = (LaunchItem) getArguments().getSerializable("thisLaunch");

        // ----------------------------------------------------------
        // Mission Name
        String missionName = mCurrentLaunch.getmMissionName();
        TextView missionNameTextView = (TextView) rootView.findViewById(R.id.mission_name);
        missionNameTextView.setText(missionName);

        // ----------------------------------------------------------
        // Mission Type
        String missionType = mCurrentLaunch.getmMissionType();
        TextView missionTypeTextView = (TextView) rootView.findViewById(R.id.mission_type);
        missionTypeTextView.setText(missionType);

        // ----------------------------------------------------------
        // Mission Description
        String missionDescription = mCurrentLaunch.getmMissionDescription();
        TextView missionDescriptionTextView = (TextView) rootView.findViewById(R.id.mission_description);
        missionDescriptionTextView.setText(missionDescription);



        return rootView;
    }

    public static Fragment newInstance(LaunchItem mCurrentLaunch) {

        DetailpageMissionFragment myDetailMissionFragment = new DetailpageMissionFragment();

        Bundle launch = new Bundle();
        launch.putSerializable("thisLaunch", mCurrentLaunch);
        myDetailMissionFragment.setArguments(launch);

        return myDetailMissionFragment;
    }
}
