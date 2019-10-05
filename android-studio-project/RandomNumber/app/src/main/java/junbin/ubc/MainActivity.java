package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Random;

/**
 * @testcase_name RandomNumber
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove whether an static analyzer can resolve the range
 * or a random number generator or not.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully resolve the range of random number generator.
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

        Random rand = new Random();
        if (rand.nextInt(10) > 20) {
            Log.i("TAG", imei); // sink, should not leak
        } else {
            Log.d("TAG", imei); // sink and leak
        }
    }
}
