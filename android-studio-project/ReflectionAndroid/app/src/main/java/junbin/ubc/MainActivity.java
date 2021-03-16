package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name ReflectionAndroid
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case to show that DroidRA is not able to instrument Android framework
 * methods
 *
 * @dataflow
 * Expected sources: 0
 * Expected sinks: 1
 *
 * @number_of_leaks 0
 *
 * @challenges The analysis must sucessfully handle Android framework methods invoked reflectively.
 *
 */

public class MainActivity extends Activity {
    Object o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Location loc = new Location(LocationManager.GPS_PROVIDER);
            Location t = new Location(LocationManager.GPS_PROVIDER);

            Class c = Class.forName("android.location.Location");
            Method method = c.getMethod("bearingTo", Location.class);
            Float bearing = (Float) method.invoke(loc, t);

            Log.v("TAG", "" + bearing); // sink, no leak
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
