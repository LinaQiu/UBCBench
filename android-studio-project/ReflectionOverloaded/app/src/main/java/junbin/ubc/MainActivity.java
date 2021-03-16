package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name ReflectionOverloaded
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case with overloaded methods.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle getMethod(), when there are overloaded methods.
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

    public void leak(String s) {
        Log.i("TAG", s); // sink, leak
    }

    public void leak(Object o) {
        Log.v("dynny", "dummy");
    }
}
