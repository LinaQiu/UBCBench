package lina.ubc.sharedpreference2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name SharedPrefernce2
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case (SharedPreference2), combined with test case SharedPreference3
 *              are used to help clarify that: for the /Lifecycle/SharedPreferenceChanged1/ test case
 *              in DroidBench:
 *
 *              (https://github.com/secure-software-engineering/DroidBench/tree/master/eclipse-project/Lifecycle/SharedPreferenceChanged1),
 *
 *              the reason why FD, IccTA, and AD failed to detect this flow is not about handling the
 *              lifecycle events of shared preferences. Those tools can successfully detect the changes
 *              in shared preferences, and call the callback listener OnSharedPreferenceChangeListener.
 *              The reason why the three tools failed to detect the flow is as described below:
 *
 *              FNs: Fails to model SharedPreference. Specifically, the tool fails to detect the value
 *              read/write from/to the shared preferences
 *
 * @dataflow
 * Expected Source: line 54: getDeviceId()
 * Expected Sink: line 68: Log.i(java.lang.String, java.lang.String)
 *
 * Flow Path:
 * line 54: String imei = mgr.getDeviceId() -->
 * line 57: settings.registerOnSharedPreferenceChangeListener(prefsListener) -->
 * line 60: editor.putString("imei", imei) -->
 * line 61: editor.apply() -->
 * call --> SharedPreferences.OnSharedPreferenceChangeListener prefsListener -->
 * line 67: String imei = sharedPreferences.getString(key, "") -->
 * line 68: Log.i("DroidBench", imei) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle/record changes written into a SharedPreference object,
 *              then read the data out; and handle the lifecycle events (data updates) related to SharedPreference.
 */

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager mgr = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();    // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_

        SharedPreferences settings = getSharedPreferences("settings", 0);
        settings.registerOnSharedPreferenceChangeListener(prefsListener);

        SharedPreferences.Editor editor = settings.edit();
        editor.putString("imei", imei);
        editor.apply();
    }

    SharedPreferences.OnSharedPreferenceChangeListener prefsListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            String imei = sharedPreferences.getString(key, "");
            Log.i("SharedPreference", imei);  // Sink, leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
        }
    };

}
