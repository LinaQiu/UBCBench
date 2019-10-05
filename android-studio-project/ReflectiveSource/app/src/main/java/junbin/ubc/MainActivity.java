package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name ReflectiveSource
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case with a reflective source.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully recognize reflective source.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        Class c = null;
        try {
            c = Class.forName("android.telephony.TelephonyManager");
            Method m = c.getMethod("getDeviceId", c);
            String imei = (String) m.invoke(telephonyManager); // reflective source
            Log.i("TAG", imei); // sink, leak
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
