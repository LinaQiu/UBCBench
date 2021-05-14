package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name ReflectionDynamic
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case that dynamically constructs reflective calls.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle refletive calls with parameters constrcuted dynamically.
 *
 */

public class MainActivity extends Activity {
    Class c;
    Object o;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        try {
            c = Class.forName("junbin.ubc.MainActivity");
            o = c.newInstance();

            Object[] obj = {"TAG", imei};
            Class<?> params[] = new Class[obj.length];

            if (obj[0] instanceof String) {
                params[0] = String.class;
            }

            if (obj[1] instanceof String) {
                params[1] = String.class;
            }

            Method m = c.getMethod("leak", params);
            m.invoke(o, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leak(String t, String s) {
        Log.i(t, s); // sink, leak
    }

    public void leak() {
        Log.v("dummy", "dummy"); // sink, no leak
    }
}
