package com.example.android.spacelaunchmanifest;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 9/12/2016.
 */
public final class QueryTools {

    /** Tag for the log messages */
    public static final String LOG_TAG = QueryTools.class.getSimpleName();


    /**
     * Create a private constructor because no one should ever create a {@link QueryTools} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    public QueryTools(){
    }


    /**
     * Query the launchlibrary dataset and return a list of {@link LaunchItem} object to represent the launches.
     */
    public static List<LaunchItem> fetchLaunchData(String requestUrl) {
        Log.e(LOG_TAG, "Fetching launch data");

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        // Extract relevant fields from the JSON response and create a list of {@link LaunchItem}s
        List<LaunchItem> launches = extractFeatureFromJson(jsonResponse);

        // Return the list of {@link LaunchItem}s
        return launches;
    }


    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the launch JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link LaunchItem} objects that has been built up from
     * parsing a JSON response.
     */
    public static List<LaunchItem> extractFeatureFromJson(String launchesJson) {
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(launchesJson)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding launches to
        List<LaunchItem> launches = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // Create a JSONObject from the JSON response string
            JSONObject baseJsonResponse = new JSONObject(launchesJson);

            // Extract the JSONArray associated with the key called "launches",
            // which represents a list of launches
            JSONArray baseLaunchList = baseJsonResponse.getJSONArray("launches");


            // For each launchitem in the launchArray, create an {@link LaunchItem} object
            for (int i = 0; i < baseLaunchList.length(); i++) {

                // Get a single launch at position i within the list of launches
                JSONObject currentLaunch = baseLaunchList.getJSONObject(i);

                // get the name of the current launch, e.g.:
                // "Atlas V 401 | WorldView-4"
                String launchName = currentLaunch.getString("name");

                // get the unix epoch time stamp in seconds for the launch, e.g.:
                // 1474050600
                long netTimeStamp = currentLaunch.getLong("netstamp");

                // Time Stamp as a predefined string
                String textTimeStamp = currentLaunch.getString("net");

                // get object which contains information about the location
                JSONObject locationObject = currentLaunch.getJSONObject("location");
                // get string which contains the location name, e.g.:
                // "Vandenberg AFB, CA, USA"
                String locationName = locationObject.getString("name");

                // get the array which contains information about the mission
                JSONArray missionsArray = currentLaunch.getJSONArray("missions");
                // get the first mission object in the array
                JSONObject firstMission = missionsArray.getJSONObject(0);
                // get the first mission's name, e.g.:
                // "WorldView-4"
                String missionName = firstMission.getString("name");
                // get the first mission's description, e.g.:
                // "WorldView-4 is a commercial Earth observation satellite to be launched into sun-synchronious orbit.
                // Satellite will provide high-resolution imagery in panchromatic and multispectral modes."
                String missionDescription = firstMission.getString("description");

                // get the JSONArray containing media URLs
                JSONArray mediaArray = currentLaunch.getJSONArray("vidURLs");
                // get the first media link as a string, e.g.:
                // "http:\/\/www.ulalaunch.com\/webcast"
                String firstMediaLink = "";
                if (mediaArray.length() > 0) {
                    firstMediaLink = mediaArray.getString(0);
                }

                // get the json object containing information about the vehicle
                JSONObject rocketObject = currentLaunch.getJSONObject("rocket");
                // get the image url contained in the rocket object
                String rocketImageUrl = rocketObject.getString("imageURL");


                // Create a new {@link LaunchItem} object with the launch-name, timestamp, location
                // mission name and description, and media url from the JSON response.
                LaunchItem launch = new LaunchItem(launchName, netTimeStamp, textTimeStamp, locationName,
                        missionName, missionDescription, firstMediaLink, rocketImageUrl);

                // Add the new {@link LaunchItem} to the list of launches.
                launches.add(launch);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the launches JSON results", e);
        }

        // Return the list of launches
        return launches;
    }


}
