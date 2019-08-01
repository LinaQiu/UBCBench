package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ImplicitIntentNoAction
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that if there is an implicit Intent without Action,
 * it is not resolved in IccTA of FlowDroid 2.7.1.
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 4
 *
 * @number_of_leaks 4
 *
 * @challenges The analysis must sucessfully handle implicit intent without Action.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        Uri uri = Uri.parse("junbin://ubc:100");

        Intent i1 = new Intent(); // Intent without Action
        i1.setData(uri);
        i1.putExtra("imei", telephonyManager.getDeviceId()); // source
        startActivity(i1); // sink and leak

        Intent i2 = new Intent("junbin.ubc.INTENTFILTER2"); // Intent with Action
        i2.setData(uri);
        i2.putExtra("imei", telephonyManager.getDeviceId()); // source
        startActivity(i2); // sink and leak
    }
}
