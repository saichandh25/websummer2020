package com.example.vijaya.earthquakeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class QueryUtils {
    /**
     * Tag for the log messages
     */
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Query the USGS dataset and return a list of {@link Earthquake} objects.
     */
    public static List<Earthquake> fetchEarthquakeData2(String requestUrl) {
        // An empty ArrayList that we can start adding earthquakes to
        List<Earthquake> earthquakes = new ArrayList<>();
        //  URL object to store the url for a given string
        URL url = null;
        // A string to store the response obtained from rest call in the form of string
        String jsonResponse = "";
        StringBuilder result = new StringBuilder();
        BufferedReader bufferReader= null;
        InputStream inputStream = null;
        String context = null;
        try {
            //TODO: 1. Create a URL from the requestUrl string and make a GET request to it
            url = new URL(requestUrl);
            // created the url from requesturl
            //TODO: 2. Read from the Url Connection and store it as a string(jsonResponse
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //in the bufferreader i just connected the cntUrl

            inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            bufferReader = new BufferedReader(new InputStreamReader(inputStream));

            while ((context = bufferReader.readLine()) != null) {
                // appended the cnxt to result stringbuilder variable
                result.append(context);
            }
            // here to use it as a Json response converting the data in result to string
            jsonResponse = result.toString();
            /*Log.i("myTag",jsonResponse);
            Log.i("myTag","Earth quake");*/


                /*TODO: 3. Parse the jsonResponse string obtained in step 2 above into JSONObject to extract the values of
                        "mag","place","time","url"for every earth quake and create corresponding Earthquake objects with them
                        Add each earthquake object to the list(earthquakes) and return it.
                */
                //analayse json response
            try {
                JSONObject responseObj = new JSONObject(jsonResponse);
                JSONArray arr =(JSONArray) responseObj.get("features");
                //just taking only featues
                //for loop to iter in features vals
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject json = arr.getJSONObject(i).getJSONObject("properties");
                    Earthquake earth = new Earthquake
                            (Double.parseDouble(json.getString("mag")), json.getString("place"), Long.parseLong(json.getString("time")),
                                    json.getString("url"));
                    earthquakes.add(earth);
                    Log.d("My App", json.getString("mag"));
                }
            } catch (Throwable t) {
                Log.e("This Application", " JSON is not responding: \"" + jsonResponse + "\"");
            }

            // Return the list of earthquakes

        } catch (Exception e) {
            Log.e(LOG_TAG, "Found Exception:  ", e);
        }
        // Return the list of earthquakes
        return earthquakes;
    }
}