package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        double lat = loc.getLatitude(); // source
        Log.i("TAG", loc.toString()); // sink, but no leak

        double lon = loc.getLongitude();
        Log.v("TAG", loc.toString()); // sink, but no leak
    }
}
