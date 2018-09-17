package lina.ubc.multientryissue2;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.concurrent.Executors;

/**
 * @testcase_name MultiEntryIssue2
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to explain DroidSafe's multi entry issue. This case need to
 *              be considered together with another test case: MultiEntryIssue1.
 *              To be more specific, DroidSafe reported flow with sink's entry point, instead of source's entry point.
 *
 *              In this test case, DroidSafe reports "void run()" at line 42, as the entry point of
 *              the source for the detected flow, which is wrong.
 *
 * @dataflow
 * Expected Sources: line 43: getDeviceId()
 * Expected Sinks: line 55: Log.i(java.lang.String, java.lang.String)
 *
 * Flow path:
 * line 43: Executors.newCachedThreadPool().execute(new MyRunnable(tpm.getDeviceId())) -->
 * line 50: this.deviceId = deviceId;
 * line 55: Log.i("ActivityWithRunnable", deviceId) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be aware of the right entry point of a detected flow, meaning the method
 *              in which the source of the flow is defined.
 */
public class ActivityWithRunnable extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {    // entry point of the source getDeviceId()
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_runnable);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        Executors.newCachedThreadPool().execute(new MyRunnable(tpm.getDeviceId())); // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
    }

    private class MyRunnable implements Runnable {
        private final String deviceId;

        public MyRunnable(String deviceId) {
            this.deviceId = deviceId;
        }

        @Override
        public void run() {     // reported wrong entry point by DroidSafe
            Log.i("ActivityWithRunnable", deviceId);    // Sink, leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
        }
    }


}
