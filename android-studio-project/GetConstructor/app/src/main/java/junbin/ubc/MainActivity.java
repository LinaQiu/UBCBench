package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @testcase_name GetConstructor
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case, combined with test case Reflection1, can prove if a static taint
 * analyzer can handle getConstructor().
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle getConstructor().
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Class<?> myClass = null;
        try {
            myClass = getClassLoader().loadClass("junbin.ubc.MyClass");
            Constructor<?> ctor = myClass.getConstructor(Context.class);
            ctor.newInstance(this); // The constructor of MyClass is invoked
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}