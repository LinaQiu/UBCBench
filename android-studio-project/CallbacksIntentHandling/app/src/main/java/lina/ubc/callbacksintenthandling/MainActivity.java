package lina.ubc.callbacksintenthandling;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @testcase_name CallbacksIntentHandling
 * @author Lina Qiu
 * @author_mail lqiu@ece.ubc.ca
 *
 * @description This is a test case used to explain that Amandroid reports false positives because it
 *              considers the Intent parameters of a callback as source.
 * @dataflow
 * Expected sources: 0
 * Expected sinks: startActivityForResult(android.content.Intent intent, int requestCode)
 *              && Log.i(java.lang.String, java.lang.String)
 *
 * Amandroid would report false positive as shown in below:
 * line 41: onActivityResult(int requestCode, int resultCode, Intent resultData) -->
 * line 42: Log.i("CallbacksHandling", "resultData intent: "+resultData) --> FP
 *
 * @number_of_leaks 0
 * @challenges The analysis must be aware that the Intent parameter of a callback method is not
 *              necessary a source.
 *
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(Intent.ACTION_SEND);
        startActivityForResult(intent, 0);  // Sink1: <android.app.Activity: void startActivityForResult(android.content.Intent,int)> -> _SINK_
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        Log.i("CallbacksHandling", "resultData intent: "+resultData);   // Sink2: <android.util.Log: int i(java.lang.String,java.lang.String)> -> _SINK_
    }
}
