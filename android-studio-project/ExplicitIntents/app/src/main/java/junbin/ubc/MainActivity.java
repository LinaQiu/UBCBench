package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ExplicitIntents
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that Amandroid 3.2.0 does not report ICC method
 * sinks if they use an explicit intent as argument.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 4
 *
 * @number_of_leaks 4
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

        Intent i1 = new Intent(this, InFlowActivity1.class);
        i1.putExtra("imei", imei);
        startActivity(i1); // sink and leak

        Intent i2 = new Intent("junbin.ubc.INTENTFILTER");
        i2.putExtra("imei", imei);
        startActivity(i2); // sink and leak
    }
}
