package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name SetResulsTaintsThis
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that in Amandroid 3.2.0, the base object is
 * tainted when handling setResult().
 *
 * Notice that this benchmark is also affected by extendIDDGForSinkApis() issue.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 3
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must correctly model setResult().
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        Intent i = getIntent();

        Log.d("TAG", this.toString()); // sink, but no leak, to show that getIntent() does not taint "this"

        i.putExtra("imei", imei);
        setResult(0, i); // sink and leak

        Log.v("TAG", this.toString()); // sink, but no leak
    }
}
