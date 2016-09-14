package com.example.android.spacelaunchmanifest;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by aaron on 9/14/2016.
 */
public class DetailpageDetailFragment extends Fragment {

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.detailpage_detailtab, container, false);

        return rootView;
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public static Fragment newInstance() {
        return new DetailpageDetailFragment();
    }

}
