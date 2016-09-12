package com.example.android.spacelaunchmanifest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LaunchArrayAdapter mAdapter;

    private static final String SAMPLE_JSON = "{\"total\":5,\"launches\":[{\"id\":862,\"name\":\"Long March 2F\\/G | Tiangong-2\",\"windowstart\":\"September 15, 2016 00:00:00 UTC\",\"windowend\":\"September 20, 2016 23:59:59 UTC\",\"net\":\"September 15, 2016 00:00:00 UTC\",\"wsstamp\":0,\"westamp\":0,\"netstamp\":0,\"isostart\":\"20160915T000000Z\",\"isoend\":\"20160920T235959Z\",\"isonet\":\"20160915T000000Z\",\"status\":2,\"inhold\":0,\"tbdtime\":1,\"vidURLs\":[],\"vidURL\":null,\"infoURLs\":[],\"infoURL\":null,\"holdreason\":null,\"failreason\":null,\"tbddate\":0,\"probability\":-1,\"hashtag\":null,\"location\":{\"pads\":[{\"id\":8,\"name\":\"Launch Area 4 (SLS-1 \\/ 921), Jiuquan\",\"infoURL\":\"\",\"wikiURL\":\"https:\\/\\/en.wikipedia.org\\/wiki\\/Jiuquan_Launch_Area_4\",\"mapURL\":\"https:\\/\\/www.google.com\\/maps\\/place\\/40%C2%B057'29.1%22N+100%C2%B017'28.3%22E\\/@40.958093,100.288994,676m\\/data=!3m2!1e3!4b1!4m5!3m4!1s0x0:0x0!8m2!3d40.958093!4d100.291188?hl=en\",\"latitude\":\"40.958093000000000\",\"longitude\":\"100.291188000000000\",\"agencies\":[{\"id\":17,\"name\":\"China National Space Administration\",\"abbrev\":\"CNSA\",\"countryCode\":\"CHN\",\"type\":1,\"infoURL\":\"http:\\/\\/www.cnsa.gov.cn\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/China_National_Space_Administration\",\"infoURLs\":[]},{\"id\":88,\"name\":\"China Academy of Space Technology\",\"abbrev\":\"CASC\",\"countryCode\":\"CHN\",\"type\":3,\"infoURL\":\"http:\\/\\/www.cast.cn\\/CastEn\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/China_Academy_of_Space_Technology\",\"infoURLs\":[]}]}],\"id\":1,\"name\":\"Jiuquan\",\"infoURL\":\"\",\"wikiURL\":\"\",\"countryCode\":\"CHN\"},\"rocket\":{\"id\":111,\"name\":\"Long March 2F\\/G\",\"configuration\":\"F\\/G\",\"familyname\":\"Long March 2\",\"agencies\":[],\"imageURL\":\"https:\\/\\/s3.amazonaws.com\\/launchlibrary\\/RocketImages\\/placeholder_1920.png\",\"imageSizes\":[320,480,640,720,768,800,960,1024,1080,1280,1440,1920]},\"missions\":[{\"id\":340,\"name\":\"Tiangong-2\",\"description\":\"China's second space station, Tiangong-2 is tasked to verify key technologies such as cargo transportation, on-orbit propellant resupply, and a medium-term stay of astronauts. It will also carry an array of scientific experiments, and will be equipped with a robotic arm to help the assembly and maintenance of the station.\",\"type\":5,\"typeName\":\"Human Exploration\"}]},{\"id\":1063,\"name\":\"Vega | Per\\u00faSAT-1 \\/ SkySat x 4\",\"windowstart\":\"September 16, 2016 01:43:35 UTC\",\"windowend\":\"September 16, 2016 01:43:35 UTC\",\"net\":\"September 16, 2016 01:43:35 UTC\",\"wsstamp\":1473990215,\"westamp\":1473990215,\"netstamp\":1473990215,\"isostart\":\"20160916T014335Z\",\"isoend\":\"20160916T014335Z\",\"isonet\":\"20160916T014335Z\",\"status\":1,\"inhold\":0,\"tbdtime\":0,\"vidURLs\":[\"http:\\/\\/www.arianespace.com\\/mission\\/vega-flight-vv07\\/\"],\"vidURL\":null,\"infoURLs\":[\"http:\\/\\/www.arianespace.com\\/wp-content\\/uploads\\/2016\\/09\\/VV07-launchkit-GB-2a.pdf\"],\"infoURL\":null,\"holdreason\":null,\"failreason\":null,\"tbddate\":0,\"probability\":-1,\"hashtag\":null,\"location\":{\"pads\":[{\"id\":18,\"name\":\"Ariane Launch Area 1, Kourou\",\"infoURL\":\"http:\\/\\/www.esa.int\\/Our_Activities\\/Launchers\\/Europe_s_Spaceport\\/Europe_s_Spaceport2\",\"wikiURL\":\"https:\\/\\/en.wikipedia.org\\/wiki\\/ELA-1\",\"mapURL\":\"https:\\/\\/www.google.com\\/maps\\/?q=5.239000000000000,-52.775000000000000\",\"latitude\":\"5.236000000000000\",\"longitude\":\"-52.775000000000000\",\"agencies\":[{\"id\":115,\"name\":\"Arianespace\",\"abbrev\":\"ASA\",\"countryCode\":\"FRA,DEU,ITA,BEL,CHE,SWE,ESP,NLD,NOR,DEN,IND\",\"type\":3,\"infoURL\":\"http:\\/\\/www.arianespace.com\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Arianespace\",\"infoURLs\":[]}]}],\"id\":3,\"name\":\"Kourou, French Guiana\",\"infoURL\":\"\",\"wikiURL\":\"\",\"countryCode\":\"FRA\"},\"rocket\":{\"id\":18,\"name\":\"Vega\",\"configuration\":\"\",\"familyname\":\"Vega\",\"agencies\":[{\"id\":159,\"name\":\"Avio S.p.A\",\"abbrev\":\"Avio\",\"countryCode\":\"ITA\",\"type\":3,\"infoURL\":\"http:\\/\\/www.aviogroup.com\\/en\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Avio\",\"infoURLs\":[]}],\"imageURL\":\"https:\\/\\/s3.amazonaws.com\\/launchlibrary\\/RocketImages\\/placeholder_1920.png\",\"imageSizes\":[320,480,640,720,768,800,960,1024,1080,1280,1440,1920]},\"missions\":[{\"id\":341,\"name\":\"Per\\u00faSAT-1 \\/ SkySat x 4\",\"description\":\"Launch VV07 from Arianespace will launch 5 earth imaging satellites into a sun-synchronous orbit. Per\\u00faSAT-1 weighs 450kg and will be able to capture images with a resolution of 70cm. The smaller Skybox satellites, each weighing 120kg, will be able to achieve a resolution of approximately 1 meter.\",\"type\":1,\"typeName\":\"Earth Science\"}]},{\"id\":763,\"name\":\"Atlas V 401 | WorldView-4\",\"windowstart\":\"September 16, 2016 18:30:00 UTC\",\"windowend\":\"September 16, 2016 18:44:00 UTC\",\"net\":\"September 16, 2016 18:30:00 UTC\",\"wsstamp\":1474050600,\"westamp\":1474051440,\"netstamp\":1474050600,\"isostart\":\"20160916T183000Z\",\"isoend\":\"20160916T184400Z\",\"isonet\":\"20160916T183000Z\",\"status\":1,\"inhold\":0,\"tbdtime\":0,\"vidURLs\":[\"http:\\/\\/www.ulalaunch.com\\/webcast\"],\"vidURL\":null,\"infoURLs\":[],\"infoURL\":null,\"holdreason\":null,\"failreason\":null,\"tbddate\":0,\"probability\":-1,\"hashtag\":null,\"location\":{\"pads\":[{\"id\":90,\"name\":\"Space Launch Complex 3E, Vandenberg AFB, CA\",\"infoURL\":\"\",\"wikiURL\":\"\",\"mapURL\":\"http:\\/\\/maps.google.com\\/maps?f=q&amp;hl=en&amp;q=34.640+N,+120.5895+W&amp;ie=UTF8&amp;z=17&amp;ll=34.640905,-120.590401&amp;spn=0.003946,0.010815&amp;t=k&amp;om=0&amp;iwloc=addr\",\"latitude\":\"34.640000000000000\",\"longitude\":\"-120.589500000000000\",\"agencies\":[{\"id\":161,\"name\":\"United States Air Force\",\"abbrev\":\"USAF\",\"countryCode\":\"USA\",\"type\":1,\"infoURL\":\"http:\\/\\/www.af.mil\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/United_States_Air_Force\",\"infoURLs\":[]}]}],\"id\":18,\"name\":\"Vandenberg AFB, CA, USA\",\"infoURL\":\"\",\"wikiURL\":\"\",\"countryCode\":\"USA\"},\"rocket\":{\"id\":26,\"name\":\"Atlas V 401\",\"configuration\":\"401\",\"familyname\":\"Atlas\",\"agencies\":[{\"id\":82,\"name\":\"Lockheed Martin\",\"abbrev\":\"LMT\",\"countryCode\":\"USA\",\"type\":3,\"infoURL\":\"http:\\/\\/www.lockheedmartin.com\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Lockheed_Martin\",\"infoURLs\":[]},{\"id\":124,\"name\":\"United Launch Alliance\",\"abbrev\":\"ULA\",\"countryCode\":\"USA\",\"type\":3,\"infoURL\":\"http:\\/\\/www.ulalaunch.com\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/United_Launch_Alliance\",\"infoURLs\":[]},{\"id\":106,\"name\":\"General Dynamics\",\"abbrev\":\"GD\",\"countryCode\":\"USA\",\"type\":3,\"infoURL\":\"http:\\/\\/www.gd.com\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/General_Dynamics\",\"infoURLs\":[]}],\"imageSizes\":[320,480,640,720,768,800,960,1024,1080,1280,1440,1920,2560],\"imageURL\":\"https:\\/\\/s3.amazonaws.com\\/launchlibrary\\/RocketImages\\/Atlas+V+401_2560.jpg\"},\"missions\":[{\"id\":314,\"name\":\"WorldView-4\",\"description\":\"WorldView-4 is a commercial Earth observation satellite to be launched into sun-synchronious orbit. Satellite will provide high-resolution imagery in panchromatic and multispectral modes.\",\"type\":1,\"typeName\":\"Earth Science\"}]},{\"id\":775,\"name\":\"Antares 230 | Cygnus CRS OA-5 (S.S. Alan Poindexter)\",\"windowstart\":\"September 20, 2016 00:00:00 UTC\",\"windowend\":\"September 20, 2016 00:00:00 UTC\",\"net\":\"September 20, 2016 00:00:00 UTC\",\"wsstamp\":0,\"westamp\":0,\"netstamp\":0,\"isostart\":\"20160920T000000Z\",\"isoend\":\"20160920T000000Z\",\"isonet\":\"20160920T000000Z\",\"status\":2,\"inhold\":0,\"tbdtime\":1,\"vidURLs\":[],\"vidURL\":null,\"infoURLs\":[\"http:\\/\\/www.orbitalatk.com\\/news-room\\/feature-stories\\/OA5-Mission-Page\\/default.aspx\"],\"infoURL\":null,\"holdreason\":null,\"failreason\":null,\"tbddate\":1,\"probability\":-1,\"hashtag\":null,\"location\":{\"pads\":[{\"id\":109,\"name\":\"Launch Area 0 A, Wallops Island, Virginia\",\"infoURL\":\"\",\"wikiURL\":\"\",\"mapURL\":\"http:\\/\\/maps.google.com\\/maps?f=q&amp;source=s_q&amp;hl=en&amp;geocode=&amp;q=37.8337+N,+75.4881+W&amp;sll=37.837705,-75.48475&amp;sspn=0.010863,0.022724&amp;g=37.8337+N,+75.489+W&amp;ie=UTF8&amp;ll=37.833791,-75.488251&amp;spn=0.001358,0.00284&amp;t=h&amp;z=190\",\"latitude\":\"37.833700000000000\",\"longitude\":\"-75.488100000000000\",\"agencies\":[{\"id\":44,\"name\":\"National Aeronautics and Space Administration\",\"abbrev\":\"NASA\",\"countryCode\":\"USA\",\"type\":1,\"infoURL\":\"http:\\/\\/www.nasa.gov\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/National_Aeronautics_and_Space_Administration\",\"infoURLs\":[]}]}],\"id\":19,\"name\":\"Wallops Island, Virginia, USA\",\"infoURL\":\"\",\"wikiURL\":\"\",\"countryCode\":\"USA\"},\"rocket\":{\"id\":117,\"name\":\"Antares 230\",\"configuration\":\"230\",\"familyname\":\"Antares\",\"agencies\":[{\"id\":100,\"name\":\"Orbital Sciences Corporation\",\"abbrev\":\"OSC\",\"countryCode\":\"USA\",\"type\":3,\"infoURL\":\"http:\\/\\/www.orbital.com\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Orbital_Sciences_Corporation\",\"infoURLs\":[]}],\"imageURL\":\"https:\\/\\/s3.amazonaws.com\\/launchlibrary\\/RocketImages\\/placeholder_1920.png\",\"imageSizes\":[320,480,640,720,768,800,960,1024,1080,1280,1440,1920]},\"missions\":[{\"id\":175,\"name\":\"Cygnus CRS OA-5 (S.S. Alan Poindexter)\",\"description\":\"This is the seventh planned flight of the Orbital Sciences' unmanned resupply spacecraft Cygnus and its fifth flight to the International Space Station under the Commercial Resupply Services contract with NASA.\",\"type\":11,\"typeName\":\"Resupply\"}]},{\"id\":1054,\"name\":\"Soyuz-FG | Soyuz MS-02\",\"windowstart\":\"September 23, 2016 18:16:53 UTC\",\"windowend\":\"September 23, 2016 18:16:53 UTC\",\"net\":\"September 23, 2016 18:16:53 UTC\",\"wsstamp\":1474654613,\"westamp\":1474654613,\"netstamp\":1474654613,\"isostart\":\"20160923T181653Z\",\"isoend\":\"20160923T181653Z\",\"isonet\":\"20160923T181653Z\",\"status\":1,\"inhold\":0,\"tbdtime\":0,\"vidURLs\":[],\"vidURL\":null,\"infoURLs\":[],\"infoURL\":null,\"holdreason\":null,\"failreason\":null,\"tbddate\":0,\"probability\":-1,\"hashtag\":null,\"location\":{\"pads\":[{\"id\":29,\"name\":\"1\\/5, Baikonur Cosmodrome, Kazakhstan\",\"infoURL\":\"\",\"wikiURL\":\"\",\"mapURL\":\"http:\\/\\/maps.google.com\\/maps?f=q&amp;hl=en&amp;q=45.920+N,+63.342+E&amp;ie=UTF8&amp;z=16&amp;ll=45.921155,63.338628&amp;spn=0.006672,0.021629&amp;t=k&amp;om=0&amp;iwloc=addr\",\"latitude\":\"45.920000000000000\",\"longitude\":\"63.342000000000000\",\"agencies\":[{\"id\":163,\"name\":\"Russian Aerospace Defence Forces\",\"abbrev\":\"VKO\",\"countryCode\":\"RUS\",\"type\":1,\"infoURL\":\"http:\\/\\/www.eng.mil.ru\\/en\\/structure\\/forces\\/cosmic.htm\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Russian_Aerospace_Defence_Forces\",\"infoURLs\":[]},{\"id\":63,\"name\":\"Russian Federal Space Agency (ROSCOSMOS)\",\"abbrev\":\"FKA\",\"countryCode\":\"RUS\",\"type\":1,\"infoURL\":\"http:\\/\\/www.roscosmos.ru\\/\",\"wikiURL\":\"http:\\/\\/en.wikipedia.org\\/wiki\\/Russian_Federal_Space_Agency\",\"infoURLs\":[]}]}],\"id\":10,\"name\":\"Baikonur Cosmodrome, Kazakhstan, Russia\",\"infoURL\":\"\",\"wikiURL\":\"\",\"countryCode\":\"KAZ\"},\"rocket\":{\"id\":36,\"name\":\"Soyuz-FG\",\"configuration\":\"FG\",\"familyname\":\"Soyuz-U\",\"agencies\":[],\"imageSizes\":[320,480,640,720,768,800,960,1024,1080,1280,1440,1920,2560],\"imageURL\":\"https:\\/\\/s3.amazonaws.com\\/launchlibrary\\/RocketImages\\/Soyuz-FG_2560.jpg\"},\"missions\":[{\"id\":316,\"name\":\"Soyuz MS-02\",\"description\":\"Soyuz MS-02 begins expedition 49 by carrying Roscosmos cosmonauts Sergei Ryzhikov, Andrei Borisenko and NASA astronaut Robert Kimbrough to the International Space Station. After launching from the Baikonur Cosmodrome in Kazakhstan, they will rendezvous to the station where they will remain for their 5 month stay.\",\"type\":5,\"typeName\":\"Human Exploration\"}]}],\"offset\":0,\"count\":5}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        List<LaunchItem> testLaunches = extractFeatureFromJson(SAMPLE_JSON);

        // Find a reference to the {@link ListView} in the layout
        ListView launchListView = (ListView) findViewById(R.id.list);

        // Create a new adapter that takes an empty list of earthquakes as input
        mAdapter = new LaunchArrayAdapter(this, testLaunches);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        launchListView.setAdapter(mAdapter);


    }

    /**
     * Return a list of {@link LaunchItem} objects that has been built up from
     * parsing a JSON response.
     */
    private static List<LaunchItem> extractFeatureFromJson(String launchesJson) {
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


            // For each earthquake in the earthquakeArray, create an {@link Earthquake} object
            for (int i = 0; i < baseLaunchList.length(); i++) {

                // Get a single earthquake at position i within the list of launches
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


                // Create a new {@link LaunchItem} object with the launch-name, timestamp, location
                // mission name and description, and media url from the JSON response.
                LaunchItem launch = new LaunchItem(launchName, netTimeStamp, textTimeStamp, locationName,
                        missionName, missionDescription, firstMediaLink);

                // Add the new {@link LaunchItem} to the list of launches.
                launches.add(launch);
            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of launches
        return launches;
    }
}
