package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Method;

/**
 * @testcase_name ICCInXMLCallback
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case with an ICC method in callback defined in XML file.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully collect ICC method in callback defined in XML file.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // callback defined in XML file
    public void buttonClick(View view) {
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        Intent i = new Intent(this, AnotherActivity.class);
        i.putExtra("imei", imei);
        startActivity(i); // sink, leak
    }
}
