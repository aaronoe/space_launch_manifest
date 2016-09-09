package com.example.android.spacelaunchmanifest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LaunchArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ArrayList<LaunchItem> launchList = new ArrayList<>();
        launchList.add(new LaunchItem("SpaceX CRS-5", 1473401034, "https://google.de", "Falcon 9",
                "CRS-5 Resupply Mission", "blablablarandomdescription"));
        launchList.add(new LaunchItem("SpaceX Chicken", 1473121034, "https://google.de", "Delta V",
                "CRS-5 Resupply Chicken Mission", "blablablarandomdescription"));
        launchList.add(new LaunchItem("SpaceX Amos", 1473404334, "https://google.de", "Falcon Heavy",
                "CRS-5 Resupply Mission", "blablablarandomdescription"));
        launchList.add(new LaunchItem("SpaceX CRS-5", 1263401034, "https://google.de", "Falcon 9",
                "CRS-5 Bacon Mission", "blablablarandomdescription"));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new LaunchArrayAdapter(this, launchList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);


    }
}
