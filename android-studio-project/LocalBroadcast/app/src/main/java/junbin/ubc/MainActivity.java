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
 * @testcase_name LocalBroadcast
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to show that for sendBradcast() that can be resolved in the
 * app, Droidsafe does not report it as sink.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 */

public class MainActivity extends Activity {
    String action1 = "junbin.ubc.ACTION1";
    String action2 = "junbin.ubc.ACTION2";

    String imei = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) { }
        };

        registerReceiver(receiver, new IntentFilter(action1));

        // Broadcast can be resolved locally
        Intent i1 = new Intent(action1);
        i1.putExtra("imei", imei);
        sendBroadcast(i1); // sink and leak

        // Broadcast cannot be resolved locally
        Intent i2 = new Intent(action2);
        i2.putExtra("imei", imei);
        sendBroadcast(i2); // sink and leak
    }
}
