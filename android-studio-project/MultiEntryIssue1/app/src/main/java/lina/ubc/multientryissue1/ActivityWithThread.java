package lina.ubc.multientryissue1;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name MultiEntryIssue1
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to explain DroidSafe's multi entry issue. This case need to
 *              be considered together with another test case: MultiEntryIssue2.
 *              To be more specific, DroidSafe occasionally reported the same flow twice, while one
 *              flow was reported with source's entry point, and another flow was reported with sink's entry point.
 *
 *              In this test case, DroidSafe reports the correct entry point of the source for the
 *              detected flow, which is onCreate().
 *
 * @dataflow
 * Expected Sources: line 42: getDeviceId()
 * Expected Sinks: line 55: Log.i(java.lang.String, java.lang.String)
 *
 * Flow path:
 * line 42: new MyThread(tpm.getDeviceId()).start -->
 * line 50: this.deviceId = deviceId -->
 * line 55: Log.i("ActivityWithThread", deviceId) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be aware of the right entry point of a detected flow, meaning the method
 *              in which the source of the flow is defined.
 */
public class ActivityWithThread extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // entry point of the source getDeviceId()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_thread);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        new MyThread(tpm.getDeviceId()).start();    // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
    }

    private class MyThread extends Thread {

        private final String deviceId;

        public MyThread(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public void run() {
            Log.i("ActivityWithThread", deviceId);  // Sink, leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
        }
    }
}
