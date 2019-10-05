package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ExtendIDDGForSinkApisLog
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that in Amandroid 3.2.0, its extendIDDGForSinkApis()
 * leads to false positives, when logging methods are used with contant parameters.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 1
 *
 */

public class MainActivity extends Activity {
    String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        Log.d(TAG, imei); // sink and leak
        Log.v(TAG, "DUMMY"); // sink, but no leak
    }
}
