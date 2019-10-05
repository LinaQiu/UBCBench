package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name MethodWithNonPrimitive
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case that uses reflection to find a method with non-primitive arguments.
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully find methods with non-primitive arguments using reflection.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Method m1 = getClass().getMethod("getImeiWithString", String.class);
            String imei1 = (String) m1.invoke(this, Context.TELEPHONY_SERVICE);
            Log.i("TAG", imei1); // sink, leak

            Method m2 = getClass().getMethod("getImeiWithContext", Context.class);
            String imei2 = (String) m2.invoke(this, this);
            Log.i("TAG", imei2); // sink, leak
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A method with primitive argument
    public String getImeiWithString(String str) {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(str);
        return telephonyManager.getDeviceId(); // source
    }

    // A method with non-primitive argument
    public String getImeiWithContext(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId(); // source
    }
}
