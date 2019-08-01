package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.util.Arrays;

/**
 * @testcase_name ArrayAndElements
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove that Amandroid 3.2.0 can (1) taint all elements
 * of an array if one element is tainted; (2) handle Arrays.toString; (3) the array is not tainted
 * when one of its element is tainted.
 *
 * Please notice that Arrays.copyOfRange() is added as a source to check if Arrays.toString() can
 * be handled.
 *
 * @dataflow
 * Expected sources: 2
 * Expected sinks: 3
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully handle array and its elements.
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

        String[] arr = new String[2];
        arr[0] = imei;
        arr[1] = "Non-taint";

        String[] taintedArr = Arrays.copyOfRange(new String[]{}, 0, 0); // source

        String arrToStr = Arrays.toString(arr);
        String taintedArrToStr = Arrays.toString(taintedArr);

        Log.i("TAG", arr[1]); // sink, should not leak
        Log.d("TAG", arrToStr); // sink and leak
        Log.v("TAG", taintedArrToStr); // sink and leak
    }
}
