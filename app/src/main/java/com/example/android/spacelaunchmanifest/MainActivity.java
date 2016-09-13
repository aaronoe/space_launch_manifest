package com.example.android.spacelaunchmanifest;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<LaunchItem>> {

    private LaunchArrayAdapter mAdapter;

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int LAUNCH_LOADER_ID = 1;

    private static final String LL_REQUEST_URL = "https://launchlibrary.net/1.2/launch/next/10";

    private TextView mEmptyStateTextView;

    SwipeRefreshLayout mSwipeRefreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find a reference to the {@link ListView} in the layout
        ListView launchListView = (ListView) findViewById(R.id.list);


        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new LaunchArrayAdapter(this, new ArrayList<LaunchItem>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        launchListView.setAdapter(mAdapter);

        // find empty TextView and set it to List Empty State
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_state_textview);
        launchListView.setEmptyView(mEmptyStateTextView);


        // Get a reference to the LoaderManager, in order to interact with loaders.
        final LoaderManager loaderManager = getLoaderManager();

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();


        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            Log.e(LOG_TAG, "Init loader");
            loaderManager.initLoader(LAUNCH_LOADER_ID, null, this);

        } else {
            // display error
            mEmptyStateTextView.setText(R.string.no_internet_connection);

            ProgressBar mLoadingBar = (ProgressBar) findViewById(R.id.loading_spinner);
            mLoadingBar.setVisibility(View.GONE);
        }

        final LoaderManager.LoaderCallbacks lC = this;

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loaderManager.restartLoader(LAUNCH_LOADER_ID, null, lC);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public Loader<List<LaunchItem>> onCreateLoader(int i, Bundle bundle) {

        // Create a new loader for the given URL
        Log.e(LOG_TAG, "OnCreate Loader");
        return new LaunchAsyncTaskLoader(this, LL_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<LaunchItem>> loader, List<LaunchItem> launches) {
        Log.e(LOG_TAG, "OnLoadFinished");


        mEmptyStateTextView.setText(R.string.no_launches_found);

        ProgressBar mLoadingBar = (ProgressBar) findViewById(R.id.loading_spinner);
        mLoadingBar.setVisibility(View.GONE);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
        // data set. This will trigger the ListView to update.
        if (launches != null && !launches.isEmpty()) {
            mAdapter.addAll(launches);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LaunchItem>> loader) {
        // Loader reset, so we can clear out our existing data.
        mAdapter.clear();
    }

}
