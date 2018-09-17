package lina.ubc.sharedpreference1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name SharedPreference1
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to explain that Flowdroid+IccTA and Amandroid cannot handle
 *              flows related to SharedPreference.
 *
 * @dataflow
 * Expected source: line 41: getDeviceId()
 * Expected sink: line 50: Log.i(java.lang.String, java.lang.String)
 *
 * Flow path:
 * line 41: String deviceId = tpm.getDeviceId() -->
 * line 45: editor.putString("deviceId", deviceId) -->
 * line 49: String deviceIdFromSharedPreference = settings.getString("deviceId", "") -->
 * line 50: Log.i("SharedPreference", deviceIdFromSharedPreference) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle/record changes written into a SharedPreference object,
 *              then read the data out.
 */
public class MainActivity extends Activity {

    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String deviceId = tpm.getDeviceId();    // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("deviceId", deviceId);

        editor.apply();

        String deviceIdFromSharedPreference = settings.getString("deviceId", "");
        Log.i("SharedPreference", deviceIdFromSharedPreference);    // Sink: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
    }
}
