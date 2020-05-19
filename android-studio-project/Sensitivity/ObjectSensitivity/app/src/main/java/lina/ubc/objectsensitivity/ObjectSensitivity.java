package lina.ubc.objectsensitivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.LinkedList;

/**
 * @testcase_name ObjectSensitivity
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to check whether the tool is object sensitive. If the tool
 *              is object sensitive, it should only report one flow as mentioned below.
 *
 * @dataflow
 * Expected sources: line 43: getDeviceId()
 * Expected sinks: line 46: Log.i(java.lang.String, java.lang.String)
 *              && line 47: Log.i(java.lang.String, java.lang.String)
 *
 * Flow Path:
 * line 43: list1.add(tpm.getDeviceId()) -->
 * line 46: Log.i("ObjectSensitivity1", list1.get(0)) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be able to differentiate that the LinkedList list1 and list2 are two
 *              different objects, and "tpm.getDeviceId()" & "123" are stored in two different objects.
 */
public class ObjectSensitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_sensitivity);

        LinkedList<String> list1 = new LinkedList<String>();
        LinkedList<String> list2 = new LinkedList<String>();

        TelephonyManager tpm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        list1.add(tpm.getDeviceId());   // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
        list2.add("123");

        Log.i("ObjectSensitivity1", list1.get(0));  // Sink1, Leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
        Log.i("ObjectSensitivity2", list2.get(0));  // Sink2, No leak: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
    }
}
