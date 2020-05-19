package lina.ubc.fieldsensitivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name FieldSensitivity
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to check whether the tool is field sensitive. If the tool is
 *              field sensitive, it should only report one flow as mentioned below.
 * @dataflow
 * Expected sources: line 39: getDeviceId()
 * Expected sinks: line 42: Log.e(java.lang.String, java.lang.String)
 *                  && line 43: Log.e(java.lang.String, java.lang.String)
 *
 * Flow Path:
 * line 39: a.info1 = tpm.getDeviceId() -->
 * line 42: Log.e("FieldSensitivity1", a.info1) --> leak
 *
 * @number_of_leaks 1
 * @challenges The analysis must be aware that info1 and info2 are two different fields of the same
 *              object a (class A object), and info1 gets tainted does not make info2 become tainted.
 */
public class FieldSensitivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_sensitivity);

        FieldSensitivity.A a = new FieldSensitivity.A();

        TelephonyManager tpm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        a.info1 = tpm.getDeviceId();    // Source: <android.telephony.TelephonyManager: java.lang.String getDeviceId()> -> _SOURCE_
        a.info2 = "123";

        Log.e("FieldSensitivity1", a.info1);    // Sink1, Leak: <android.util.Log: int e(java.lang.String,java.lang.String)> -> _SINK_
        Log.e("FieldSensitivity2", a.info2);    // Sink2, No leak: <android.util.Log: int e(java.lang.String,java.lang.String)> -> _SINK_

    }

    class A{
        String info1;
        String info2;
    }
}
