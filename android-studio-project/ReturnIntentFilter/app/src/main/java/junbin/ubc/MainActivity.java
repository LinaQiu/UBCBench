package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name ReturnIntentFilter
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that IC3 is not able to resolve String returned
 * from a method.
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 4
 *
 * @number_of_leaks 4
 *
 * @challenges The analysis must sucessfully handle String returned from a method.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        Intent i1 = new Intent(getIntentFilter());
        i1.putExtra("imei", telephonyManager.getDeviceId()); // source
        startActivity(i1); // sink and leak

        Intent i2 = new Intent("junbin.ubc.INTENTFILTER2");
        i2.putExtra("imei", telephonyManager.getDeviceId()); // source
        startActivity(i2); // sink and leak
    }

    String getIntentFilter() {
        return "junbin.ubc.INTENTFILTER1";
    }
}
