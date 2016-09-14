package com.example.android.spacelaunchmanifest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aaron on 9/14/2016.
 */
public class DetailpageMissionFragment extends Fragment {

    public DetailpageMissionFragment(){
        // required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detailpage_mission, container, false);

        return rootView;
    }

    public static Fragment newInstance() {
        return new DetailpageMissionFragment();
    }
}
