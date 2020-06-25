package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * @testcase_name ContextSensitivityDepth
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description A static taint analyzer will only report one flow on this test when it supports context
 * sensitive analysis with depth more than 1.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 3
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis should be able to handle consext sensitivity under higher depths.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId(); // source

        A a1 = m1(new A(imei));
        A a2 = m1(new A("dummy"));

        Log.v("TAG1", a1.s); // sink, leak
        Log.v("TAG2", a2.s); // sink, no leak
    }

    class A {
        String s;
        public A(String s) {
            this.s = s;
        }
    }

    public A m1(A a) {
        A a3 = m2(a);
        A a4 = m2(new A("dummy"));
        Log.v("TAG3", a4.s); // sink, no leak
        return a3;
    }

    public A m2(A a) {
        return a;
    }
}
