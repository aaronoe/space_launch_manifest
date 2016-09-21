package com.aaronoe.android.spacelaunchmanifest;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by aaron on 9/12/2016.
 */
public class LaunchAsyncTaskLoader extends AsyncTaskLoader<List<LaunchItem>> {

    /** Tag for log messages */
    private static final String LOG_TAG = LaunchAsyncTaskLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public LaunchAsyncTaskLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        Log.e(LOG_TAG, "Loader starts loading");
        forceLoad();
    }

    @Override
    public List<LaunchItem> loadInBackground() {
        Log.e(LOG_TAG, "Loader starts background loading");
        if (mUrl == null) {
            return null;
        }

        List<LaunchItem> launches;
        // Perform the network request, parse the response, and extract a list of launches.
        if (mUrl.contains("next")) {
            launches = QueryTools.fetchLaunchData(mUrl);
        } else {
            launches = QueryToolsPrevious.fetchLaunchData(mUrl);
        }
        return launches;
    }
}
