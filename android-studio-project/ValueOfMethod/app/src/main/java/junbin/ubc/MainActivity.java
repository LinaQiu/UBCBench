package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Random;

/**
 * @testcase_name ValueOfMethod
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to test whether a static analysis can resolve value returned
 * from a method.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully resolve value returned from methods.
 *
 */

public class MainActivity extends Activity {
    private String imei = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        if (getNum() == 1) {
            Log.i("TAG", imei); // sink, should not leak
        } else {
            Log.d("TAG", imei); // sink and leak
        }
    }

    public int getNum() {
        return 0;
    }
}
