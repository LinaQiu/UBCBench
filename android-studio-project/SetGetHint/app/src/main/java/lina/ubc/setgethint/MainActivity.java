package lina.ubc.setgethint;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * @testcase_name SetGetHint
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to confirm that FlowDroid+IccTA, AmanDroid cannot handle
 *              setHint() and getHint() not only for Buttons, but also for other Android widgets.
 *              This is a supplementary test case for /Callbacks/Button5 from DroidBench
 *              (https://github.com/secure-software-engineering/DroidBench/tree/master/eclipse-project/Callbacks/Button5).
 *
 * @dataflow
 * Expected Source: line 48: getDeviceId()
 * Expected Sink: line 53: Log.i(java.lang.String, java.lang.String)
 *
 * Flow Path:
 * call --> onCreate()
 * line 48: imei = tpm.getDeviceId() -->
 * click the EditText (R.id.et) --> leakInfoViaHint(View view) -->
 * line 52: ((EditText)view).setHint(imei) -->
 * line 53: Log.i("DroidBench", ((EditText)view).getHint().toString()) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be able to handle the getHint() and setHint() method associated with
 *              all Android widgets.
 */
public class MainActivity extends Activity {

    EditText et;
    String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        imei = tpm.getDeviceId();   // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
    }

    public void leakInfoViaHint(View view) {
        ((EditText)view).setHint(imei);
        Log.i("DroidBench", ((EditText)view).getHint().toString());  // Sink, leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
    }
}
