package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name LocationMethods
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that Amandroid 3.2.0 (1) cannot report leak when
 * only getLatitude() or getLongitude() exists; (2) taints Location object when both getLatitude()
 * and getLongitude() are used together.
 *
 * @dataflow
 * Expected sources: 3
 * Expected sinks: 3
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle Location-related methods.
 *
 */

public class Activity1 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Log.d("TAG", "Latitude: " + loc.getLatitude()); // source, sink, leak
    }
}

