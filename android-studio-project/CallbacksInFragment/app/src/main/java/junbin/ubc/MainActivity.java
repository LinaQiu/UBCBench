package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import java.lang.reflect.Method;

/**
 * @testcase_name CallbacksInFragment
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case that a onClick() callback is registered inside a fragment's
 * lifecycle.
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 2
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis should collect callback registered inside a fragment's lifecycle.
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
