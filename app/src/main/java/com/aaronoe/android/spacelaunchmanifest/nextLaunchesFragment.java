package com.aaronoe.android.spacelaunchmanifest;


import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class nextLaunchesFragment extends android.app.Fragment implements LoaderManager.LoaderCallbacks<List<LaunchItem>> {

    private LaunchArrayAdapter mAdapter;

    public static final String LOG_TAG = MainActivity.class.getName();

    private static final int LAUNCH_LOADER_ID = 1;

    private static final String LL_REQUEST_URL = "https://launchlibrary.net/1.2/launch/next/10";

    private TextView mEmptyStateTextView;

    SwipeRefreshLayout mSwipeRefreshLayout;

    View rootView;


    public nextLaunchesFragment() {
        // Required empty public constructor
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
            loaderManager.initLoader(LAUNCH_LOADER_ID, null, this); // maybe getContext
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
        return new LaunchAsyncTaskLoader(getActivity(), LL_REQUEST_URL);
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
