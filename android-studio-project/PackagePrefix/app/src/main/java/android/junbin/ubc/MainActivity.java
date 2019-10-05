package android.junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name PackagePrefix
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case to prove that FlowDroid filters out components from certain packages.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis should not filter out any user-defined components.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        Log.i("TAG", telephonyManager.getDeviceId()); // source, sink, leak
    }
}
