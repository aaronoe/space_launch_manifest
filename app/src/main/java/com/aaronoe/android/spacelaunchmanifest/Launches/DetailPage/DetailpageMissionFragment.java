package com.aaronoe.android.spacelaunchmanifest.Launches.DetailPage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aaronoe.android.spacelaunchmanifest.Launches.MainLaunches.LaunchItem;
import com.aaronoe.android.spacelaunchmanifest.R;

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

        // ----------------------------------------------------------
        // Vehicle Name
        String rocketName = mCurrentLaunch.getmRocketName();
        TextView rocketNameTextView = (TextView) rootView.findViewById(R.id.rocketNameTextView);
        rocketNameTextView.setText(rocketName);

        // ----------------------------------------------------------
        // Vehicle Configuration
        String rocketConfiguration = getResources().getString(R.string.vehicleConfiguration);
        String rocketConfigVal = mCurrentLaunch.getmRocketConfiguration();

        if (rocketConfigVal.equals("")){
            rocketConfiguration += " n/a";
        } else {
            rocketConfiguration += " " + rocketConfigVal;
        }

        TextView rocketConfigTextView = (TextView) rootView.findViewById(R.id.rocketConfigTextView);
        rocketConfigTextView.setText(rocketConfiguration);

        // ----------------------------------------------------------
        // Vehicle Configuration
        String vehicleFamily = getResources().getString(R.string.vehicleFamily);
        String rocketFamilyVal = mCurrentLaunch.getmRocketFamily();

        if (rocketFamilyVal.equals("")){
            vehicleFamily += " n/a";
        } else {
            vehicleFamily += " " + rocketFamilyVal;
        }

        TextView rocketFamilyTextView = (TextView) rootView.findViewById(R.id.rocketFamilyTextView);
        rocketFamilyTextView.setText(vehicleFamily);



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
