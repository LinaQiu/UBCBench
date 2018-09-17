package lina.ubc.contexttaintissue;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;

/**
 * @testcase_name ContextTaintIssue
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to explain Amandroid's false positive related to $this taint
 *              issue, which we categorized to field sensitivity criterion.
 *              To be more specific, Amandroid taints $this for all api_source. In this case,
 *              AmanDroid taints the entire "tpm" object as sensitive.
 *
 * @dataflow
 * Expected source: line 37: getDeviceId()
 * Expected sink: line 40: sendTextMessage(java.lang.String,java.lang.String, java.lang.String,android.app.PendingIntent,android.app.PendingIntent)
 *
 * False positive flow that Amandroid reported:
 * tpm.getDeviceId() --> sms.sendTextMessage("+49 4444", null, "Telephony manager: "+tpm, null, null)
 *
 * @number_of_leaks 0
 * @challenges The analysis must be able to tell that a field of an object is sensitive does not mean
 *              the entire object is sensitive.
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager tpm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        tpm.getDeviceId();      // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("+49 4444", null, "Telephony manager: "+tpm, null, null);   // Sink: <android.telephony.SmsManager: void sendTextMessage(java.lang.String,java.lang.String,java.lang.String,android.app.PendingIntent,android.app.PendingIntent)> -> _SINK_

    }

}
