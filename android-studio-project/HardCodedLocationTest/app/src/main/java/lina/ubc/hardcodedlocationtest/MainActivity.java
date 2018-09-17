package lina.ubc.hardcodedlocationtest;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name HardCodedLocationTest
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This app is used to explain that Amandroid uses a conservative strategy for handling
 *              location-related flows, meaning that it hard-codes the location parameter of
 *              onLocationChanged callback as a sensitive source, no matter what location sources
 *              the users specify.
 * @dataflow
 * Expected source: no source
 * Expected sink: line 50: Log.d(java.lang.String, java.lang.String)
 *
 * AmanDroid hard-codes the location parameter "loc" as a sensitive source, and falsely reports the
 * following flow as a leakage:
 * line 48: onLocationChanged(Location loc) -->
 * line 50: Log.d("data", "location time: "+loc.getTime())
 *
 * @number_of_leaks 0
 * @challenges The analysis must be aware of user-specified sources, and that the entire Location
 *              object is not necessary sensitive as a whole.
 */

public class MainActivity extends Activity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager)
            getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10, this);
    }

    @Override
    public void onLocationChanged(Location loc) {  // hard-coded source by AmanDroid

        Log.d("data", "location time: "+loc.getTime());  // Sink, no leak: <android.util.Log: int d(java.lang.String,java.lang.String)> -> _SINK_
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}

