package com.aaronoe.android.spacelaunchmanifest.Launches.MainLaunches;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.aaronoe.android.spacelaunchmanifest.Launches.NetworkTools.LaunchAsyncTaskLoader;
import com.aaronoe.android.spacelaunchmanifest.MainActivity;
import com.aaronoe.android.spacelaunchmanifest.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class previousLaunchesFragment extends android.app.Fragment implements LoaderManager.LoaderCallbacks<List<LaunchItem>>{

    private TextView mEmptyStateTextView;

    SwipeRefreshLayout mSwipeRefreshLayout;

    View rootView;

    private LaunchArrayAdapter mAdapter;

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int LAUNCH_LOADER_ID = 1;

    private String ll_request_url;



    public previousLaunchesFragment(){
        // necessary public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.upcoming_launches_list, container, false);

        // Find a reference to the {@link ListView} in the layout
        ListView launchListView = (ListView) rootView.findViewById(R.id.list);

        mAdapter = new LaunchArrayAdapter(getActivity(), new ArrayList<LaunchItem>());

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        launchListView.setAdapter(mAdapter);

        // find empty TextView and set it to List Empty State
        mEmptyStateTextView = (TextView) rootView.findViewById(R.id.empty_state_textview);
        launchListView.setEmptyView(mEmptyStateTextView);

        final LoaderManager loaderManager = getLoaderManager();

        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
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

            ProgressBar mLoadingBar = (ProgressBar) rootView.findViewById(R.id.loading_spinner);
            mLoadingBar.setVisibility(View.GONE);
        }

        final LoaderManager.LoaderCallbacks lc = this;

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_main_swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loaderManager.restartLoader(LAUNCH_LOADER_ID, null, lc);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        return rootView;
    }

    @Override
    public Loader<List<LaunchItem>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        Log.e(LOG_TAG, "OnCreate Loader");

        Long currentTime = System.currentTimeMillis();

        Long twoMonthsInMilliseconds = 5259492000L;
        Long twoMonthsAgoTime = currentTime - twoMonthsInMilliseconds ;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String oldDate = dateFormatter.format(twoMonthsAgoTime);
        String newDate = dateFormatter.format(currentTime);

        ll_request_url = "https://launchlibrary.net/1.2/launch/" + oldDate + "/" + newDate + "/?limit=30";

        return new LaunchAsyncTaskLoader(getActivity(), ll_request_url);
    }

    @Override
    public void onLoadFinished(Loader<List<LaunchItem>> loader, List<LaunchItem> launches) {
        Log.e(LOG_TAG, "OnLoadFinished");

        mEmptyStateTextView.setText(R.string.no_launches_found);

        ProgressBar mLoadingBar = (ProgressBar) rootView.findViewById(R.id.loading_spinner);
        mLoadingBar.setVisibility(View.GONE);

        // Clear the adapter of previous earthquake data
        mAdapter.clear();

        if (launches != null && !launches.isEmpty()) {
            mAdapter.addAll(launches);
            getLoaderManager().destroyLoader(loader.getId());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<LaunchItem>> loader) {
        mAdapter.clear();
    }
}