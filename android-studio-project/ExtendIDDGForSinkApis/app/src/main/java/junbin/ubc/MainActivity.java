package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ExtendIDDGForSinkApis
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that in Amandroid 3.2.0, its extendIDDGForSinkApis()
 * creates incorrect points-to info, leads to false positives.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 2
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

        Intent i = new Intent();
        i.putExtra("imei", imei);

        startActivity(i); // sink, leak
        this.setResult(RESULT_OK, i); // sink, leak
    }
}
