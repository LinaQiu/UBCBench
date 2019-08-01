package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name PassSourceSink
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that FlowDroid 2.7.1 skips sources and sinks in
 * taint propagation.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must inspect sources and sinks in taint propagation.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String taint = source(); // source
        String anotherTaint = sink(taint); // sink and leak
        Log.i("TAG", anotherTaint); // sink and leak
    }

    String source() {
        return "TAINT";
    }

    String sink(String str) {
        return str;
    }
}
