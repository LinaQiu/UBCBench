package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

import static java.lang.Class.forName;

/**
 * @testcase_name ForName
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case that combines with GetClass to show that DroidSafe can handle
 * forName() in reflection.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle forName().
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Class c = forName("junbin.ubc.MainActivity");
            Method m = c.getMethod("getImei");
            String imei = (String) m.invoke(this);
            Log.i("TAG", imei); // sink, leak
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getImei() {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getDeviceId(); // source
    }
}
