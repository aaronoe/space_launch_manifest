package com.example.android.spacelaunchmanifest;

/**
 * Public class to hold the information of the launches
 * Created by aaron on 9/8/2016.
 */
public class LaunchItem {

    // Display name of the launch
    private String mLaunchName;

    // Net Date for the launch
    private long mNetLaunchDate;

    // URL for the launch'S broadcast
    private String mMediaUrl;

    // Name of the Vehicle
    private String mTextLaunchDate;

    // probability for launch
    private String mLaunchLocation;

    // Mission Name
    private String mMissionName;

    // Mission description
    private String mMissionDescription;

    // rocket image url
    private String mRocketImageUrl;

    // latitude of the launch pad
    private double mLaunchPadLatitude;

    // longitude of the launch pad
    private double mLaunchPadLongitude;

    /**
     * Public constructor method of the LaunchItem Class
      * @param launchName Title of the launch
     * @param netLaunchDate Estimated date of the launch
     * @param mediaUrl Url of the broadcast
     * @param launchLocation Location of the launch
     * @param missionDescription Description of the mission
     */
    public LaunchItem(String launchName, long netLaunchDate, String textLaunchDate, String launchLocation,
                      String missionTitle, String missionDescription, String mediaUrl, String rocketImageUrl,
                      double padLatitude, double padLongitude){
        mLaunchName = launchName;
        mNetLaunchDate = netLaunchDate;
        mTextLaunchDate = textLaunchDate;
        mLaunchLocation = launchLocation;
        mMissionName = missionTitle;
        mMissionDescription = missionDescription;
        mMediaUrl = mediaUrl;
        mRocketImageUrl = rocketImageUrl;
        mLaunchPadLatitude = padLatitude;
        mLaunchPadLongitude = padLongitude;
    }

    /**
     *
     * @return the Title of the launch
     */
    public String getmLaunchName() {
        return mLaunchName;
    }

    /**
     *
     * @return the estimated launch date
     */
    public long getmNetLaunchDate() {
        return mNetLaunchDate;
    }

    /**
     *
     * @return the Url for the broadcast
     */
    public String getmMediaUrl() {
        return mMediaUrl;
    }

    /**
     *
     * @return name of the launch vehicle
     */
    public String getmTextLaunchDate() {
        return mTextLaunchDate;
    }

    /**
     *
     * @return Mission Name
     */
    public String getmMissionName() {
        return mMissionName;
    }

    /**
     *
     * @return the mission description text-string
     */
    public String getmMissionDescription() {
        return mMissionDescription;
    }

    /**
     *
     * @return the location of the launch as a string
     */
    public String getmLaunchLocation() {
        return mLaunchLocation;
    }

    /**
     *
     * @return the image url for this rocket
     */
    public String getmRocketImageUrl() {
        return mRocketImageUrl;
    }

    /**
     *
     * @return the latitude of the launch pad
     */
    public double getmLaunchPadLatitude() {
        return mLaunchPadLatitude;
    }

    /**
     *
     * @return the longitude of the launchpad
     */
    public double getmLaunchPadLongitude() {
        return mLaunchPadLongitude;
    }
}
