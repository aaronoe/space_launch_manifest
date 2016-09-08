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
    private String mVehicleName;

    // probability for launch
    private int mLaunchProbability;

    // Mission Name
    private String mMissionName;

    // Mission description
    private String mMissionDescription;

    /**
     * Public constructor method of the LaunchItem Class
      * @param launchName Title of the launch
     * @param netLaunchDate Estimated date of the launch
     * @param mediaUrl Url of the broadcast
     * @param vehicleName Name of the launch vehicle
     * @param missionDescription Description of the mission
     */
    public LaunchItem(String launchName, long netLaunchDate, String mediaUrl, String vehicleName,String missionTitle, String missionDescription){
        mLaunchName = launchName;
        mNetLaunchDate = netLaunchDate;
        mMediaUrl = mediaUrl;
        mVehicleName = vehicleName;
        mMissionName = missionTitle;
        mMissionDescription = missionDescription;
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
    public String getmVehicleName() {
        return mVehicleName;
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
}
