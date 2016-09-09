package com.example.android.spacelaunchmanifest;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Custom Array Adapter to inflate the list layout view with LaunchItems
 * Created by aaron on 9/8/2016.
 */
public class LaunchArrayAdapter extends ArrayAdapter<LaunchItem> {


    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context        The current context. Used to inflate the layout file.
     * @param adapter A List of Words objects to display in a list
     */
    public LaunchArrayAdapter(Activity context, ArrayList<LaunchItem> adapter) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, adapter);
    }


    /**
     * Helper method to convert UNIX epoch time to date
     * @param dateObject
     * @return a formatted date like "Tue, Feb 17, 2015"
     */
    private String formatDate(Date dateObject){
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Helper method to convert UNIX epoch time to time in user's current timezone
     * @param timeObject
     * @return a formatted time like "8:30 pm"
     */
    private String formatTime(Date timeObject){
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(timeObject);
    }


    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.launch_list_item, parent, false);
        }

        // get current list item
        LaunchItem currentLaunchItem = getItem(position);


        // Set launch title to corresponding TextView
        TextView launchTitleView = (TextView) listItemView.findViewById(R.id.launch_title);
        launchTitleView.setText(currentLaunchItem.getmLaunchName());

        // Set mission description to corresponding TextView
        TextView missionDescView = (TextView) listItemView.findViewById(R.id.mission_description);
        missionDescView.setText(currentLaunchItem.getmMissionDescription());



        // --------- Implement Date and Time ----------

        // get strings for the date and time
        long currentDate = currentLaunchItem.getmNetLaunchDate();
        Date dateObject = new Date(currentDate);


        String dateToDisplay = formatDate(dateObject);

        String timeToDisplay = formatTime(dateObject);


        // find correct TextView for location
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        dateTextView.setText(dateToDisplay);

        // find correct TextView for location
        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        timeTextView.setText(timeToDisplay);



        return listItemView;
    }




}
