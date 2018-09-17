package lina.ubc.flowsensitivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name FlowSensitivity
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to check whether the tool is flow sensitive. If the tool is
 *              flow sensitive, it should only report one flow as mentioned below.
 * @dataflow
 * Expected sources: line 36: getDeviceId()
 * Expected sinks: line 37: Log.e(java.lang.String, java.lang.String)
 *                  && line 40: Log.e(java.lang.String, java.lang.String)
 *
 * Flow Path:
 * line 36: String deviceId = tpm.getDeviceId() -->
 * line 37: Log.e("FlowSensitivity1", deviceId) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be aware of the order of statements.
 */
public class FlowSensitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_sensitivity);

        TelephonyManager tpm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String deviceId = tpm.getDeviceId();    // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
        Log.e("FlowSensitivity1", deviceId);    // Sink1, Leak: <android.util.Log: int e(java.lang.String,java.lang.String)> -> _SINK_

        deviceId = "123";
        Log.e("FlowSensitivity2", deviceId);    // Sink2, No leak: <android.util.Log: int e(java.lang.String,java.lang.String)> -> _SINK_
    }

}