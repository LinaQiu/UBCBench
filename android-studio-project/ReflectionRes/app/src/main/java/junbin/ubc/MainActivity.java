package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name ReflectionRes
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case with getMethod() uses a string from resources.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle getMethod(), when the method name is from resources.
 *
 */

public class MainActivity extends Activity {
    Object o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        try {
            String name = getString(R.string.method_name);
            Method m = getClass().getMethod(name, String.class);
            m.invoke(this, imei);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leak(String s) {
        Log.i("TAG", s); // sink, leak
    }
}
