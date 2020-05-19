package lina.ubc.locationfieldsensitivity;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name LocationFieldSensitivity
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This app is used to explain that there is a tangled reason of field sensitivity for
 *              all benchmarks that test location-related flows in AmanDroid.
 *
 * @dataflow
 * Expected source: line 42: getLatitude()
 * Expected sink: line 44: Log.d(java.lang.String, java.lang.String)
 *
 * AmanDroid treats the whole location object "loc" as tainted, due to the sensitive source
 * getLatitude() specified by users. Thus, it falsely reports the following flow as a leakage:
 * line 42: double lat = loc.getLatitude() -->
 * line 44: Log.d("data", "Location time: " + loc.getTime())
 *
 * @number_of_leaks 0
 * @challenges The analysis must be able to handle field sensitivity.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);
        Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), true));
        double lat = loc.getLatitude(); // Source:  <android.location.Location: double getLatitude()> -> _SOURCE_

        Log.d("data", "Location time: " + loc.getTime()); 	// Sink, no leak: <android.util.Log: int d(java.lang.String,java.lang.String)> -> _SINK_
    }
}
