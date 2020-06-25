package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * @testcase_name ConservativeModel3
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case to prove that FlowDroid use the conservative model to handle
 * Context.getString(), leading to FPs.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 0
 *
 * @challenges The analysis should not conservatively handle any method.
 *
 */

public class MainActivity extends Activity {
    String imei = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        this.imei = telephonyManager.getDeviceId(); // source

        String name = this.getString(R.string.app_name);

        Log.v("TAG", name); // sink, no leak
    }

}
