package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ICCSinks
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that Amandroid 3.2.0 does not report ICC method
 * sinks.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 3
 *
 * @number_of_leaks 3
 *
 * @challenges The analysis must report all user-defined ICC method sinks.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        Intent i = new Intent("junbin.ubc.INTENTFILTER");
        i.putExtra("imei", imei);
        startActivity(i); // sink and leak

        Log.i("TAG", i.toString()); // sink and leak
    }
}
