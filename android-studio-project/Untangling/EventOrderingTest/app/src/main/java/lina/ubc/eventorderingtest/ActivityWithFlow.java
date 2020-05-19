package lina.ubc.eventorderingtest;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name EventOrderingTest
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case to explain that FlowDroid+IccTA, Amandroid, and DroidSafe can
 *              handle event ordering flows.
 *
 *              And FlowDroid+IccTA and Amandroid failed to detect the flow in EventOrdering1 test case
 *              in InterComponentCommunication from DroidBench due to other reasons, as explained below.
 *
 *              Taking this test case and our SharedPreference test cases (SharedPreference1, 2, 3)
 *              into consideration, the reason why FlowDroid+IccTA and Amandroid failed to detect the
 *              flow in EventOrdering1 (in ICC folder from DroidBench) is that those tools are not able
 *              to handle reading/writing data from/to SharedPreference.
 *
 * @dataflow
 * Expected sources: line 56: getDeviceId()
 * Expected sinks: line 49: Log.i("EventOrderingTest", deviceId)
 *              && line 14 (in MainActivity): startActivity(new Intent("lina.ubc.eventorderingtest.ACTION"))
 *
 * Flow path:
 * Launch ActivityWithFlow for the first time from MainActivity -->
 * line 51: assignSensitivityInfo() -->
 * line 56: deviceId = tpm.getDeviceId() -->
 * Launch ActivityWithFlow for the second time from MainActivity -->
 * line 49: Log.i("EventOrderingTest", deviceId) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis tool has to be able to take into account different runs of the app.
 *             In this case, the end of one run is the source and the benning of the next run is the sink.
 */
public class ActivityWithFlow extends Activity {

    private static String deviceId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_flow);

        Log.i("EventOrderingTest", deviceId);  //Sink: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_

        assignSensitivityInfo();
    }

    private void assignSensitivityInfo() {
        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        deviceId = tpm.getDeviceId(); // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
    }
}
