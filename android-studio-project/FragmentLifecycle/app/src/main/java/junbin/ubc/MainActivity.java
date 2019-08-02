package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name FragmentLifecycle
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that in Amandroid 3.2.0, for Fragment's lifecycles,
 * it creates a new activity instead of using the one should be passed.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully handle Fragments.
 *
 */

public class MainActivity extends Activity {
    String imei = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        setTitle(imei);

        MyFragment fragment = new MyFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();

        Log.i("TAG", "Title: " + getTitle());
    }
}
