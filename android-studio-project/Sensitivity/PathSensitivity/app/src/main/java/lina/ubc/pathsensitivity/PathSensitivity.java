package lina.ubc.pathsensitivity;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name PathSensitivity
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to check whether the tool is path sensitive. If the tool is
 *              path sensitive, it should not report flow in this case.
 *
 *              If path insensitive, the tool would report a flow from the source to the sink.
 *
 *              If path sensitive, the tool would be able to record the conditions for each flow, then
 *              there would not be a flow from the source to the sink in this case.
 *
 * @dataflow
 * Expected sources: 47: getDeviceId()
 * Expected sinks: 51: Log.i(java.lang.String, java.lang.String)
 *
 * For tools that are path insensitive, they would report the following false positive flow:
 * Flow Path:
 * line 47: deviceId=tpm.getDeviceId() -->
 * line 51: Log.i("PathSensitivity", deviceId);
 *
 * @number_of_leaks 0
 * @challenges The analysis must be aware that the executions of statements inside if/else blocks
 *              correspond to different conditions.
 */
public class PathSensitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_sensitivity);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        String deviceId = "";

        double condition = Math.random();

        if (condition<0) {
            deviceId=tpm.getDeviceId();     // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
        }

        if (!(condition<0)){
            Log.i("PathSensitivity", deviceId);     // Sink: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
        }
    }
}