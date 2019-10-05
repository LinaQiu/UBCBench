package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name AccessPrivateField
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove in FlowDroid 2.7.1, there is an issue in taint
 * propogation that tainted facts are not killed, when a private field of an outer class is accessed
 * within its inner class.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 0
 *
 * @challenges The analysis must sucessfully handle get/set of private fields from outer class in
 *  inner class.
 *
 */

public class MainActivity extends Activity {

    private String imei = null; // private field of the outer class

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source, but not leak

        IData data = new IData() {
            @Override
            public void accessField() {
                imei = null; // reset field
                Log.i("TAG", imei); // sink, should not leak
            }
        };

        data.accessField();
    }
}
