package com.example.android.spacelaunchmanifest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a fake list of earthquake locations.
        ArrayList<String> launchList = new ArrayList<>();
        launchList.add("Falcon 9");
        launchList.add("Atlas V");
        launchList.add("Falcon Heavy");
        launchList.add("CRS-5");


        // Find a reference to the {@link ListView} in the layout
        ListView launchListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of launchList
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, launchList);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        launchListView.setAdapter(adapter);
    }
}
