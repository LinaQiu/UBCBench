package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * @testcase_name WrongIccLink
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case to show how FlowDroid throws WrongIccLink exception.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 0
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i1 = new Intent();
        i1.setAction("junbin.action");
        startActivity(i1); // sink, no leak
        Intent i2 = new Intent(this, Activity1.class);
        startActivity(i2); // sink, no leak
    }
}
