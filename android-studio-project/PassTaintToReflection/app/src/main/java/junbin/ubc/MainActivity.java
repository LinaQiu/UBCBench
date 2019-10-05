package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name PassTaintToReflection
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case to prove that FlowDroid cannot find leak if the tainted value is
 * passed into a reflective call.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully detect tainted variable that is passed into a reflective
 * call.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source
        try {
            Method m = getClass().getMethod("leak", String.class);
           m.invoke(this, imei);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leak(String imei) {
        Log.i("TAG", imei); // sink, leak
    }
}
