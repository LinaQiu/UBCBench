package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * @testcase_name MissingCallback
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that FlowDroid does not collect callbacks in
 * classes, even added in AndroidCallbacks.txt.
 *
 * Please add android.app.ListFragment in AndroidCallbacks.txt
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully handle callbacks defined in class.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyFragment fragment = new MyFragment();
        getFragmentManager().beginTransaction().add(R.id.fragment_container, fragment).commit();
    }
}
