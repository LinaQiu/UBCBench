package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * @testcase_name ViewCasting
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that Amandroid cannot successfully handle casting
 * of View objects.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 3
 *
 * @number_of_leaks 3
 *
 * @challenges The analysis must sucessfully handle casting of View objects.
 *
 */

public class MainActivity extends Activity {
    String imei = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TAG", imei); // sink and leak, to show that onClick() can be reached

                ((Button) v).setHint(imei);
                Log.v("TAG", ((Button) v).getHint().toString()); // sink and leak
            }
        });


        button.setHint(imei);
        Log.d("TAG", button.getHint().toString()); // sink and leak
    }

}
