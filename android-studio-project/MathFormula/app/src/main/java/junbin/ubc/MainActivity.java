package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name MathFormula
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove whether an static analyzer can solve complicated
 * math formulas or not.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully solve math formulas.
 *
 */

public class MainActivity extends Activity {

    private String imei = null; // field of the outer class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        if (Math.sqrt(49) < 0) {
            Log.i("TAG", imei); // sink, should not leak
        } else {
            Log.d("TAG", imei); // sink and leak
        }
    }
}
