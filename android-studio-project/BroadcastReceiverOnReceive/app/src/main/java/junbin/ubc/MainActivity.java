package junbin.ubc;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name BroadcastReceiverOnReceive
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that FlowDroid does not properly handle onReceive()
 * of BroadcastReceiver.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 3
 *
 * @number_of_leaks 3
 *
 * @challenges The analysis must sucessfully handle onReceive() of BroadcastReceiver.
 *
 */

public class MainActivity extends Activity {
    String action = "junbin.ubc.ACTION";
    String imei = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.v("TAG", imei); // sink and leak

                Log.i("TAG", intent.getStringExtra("imei")); // sink and leak
            }
        };

        registerReceiver(receiver, new IntentFilter(action));

        Intent i = new Intent(action);
        i.putExtra("imei", imei);
        sendBroadcast(i); // sink and leak
    }
}
