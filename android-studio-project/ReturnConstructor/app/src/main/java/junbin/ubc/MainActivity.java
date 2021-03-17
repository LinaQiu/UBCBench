package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @testcase_name ReturnConstructor
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case, combined with test case GetContructor, can prove that DroidRA is
 * not able to handle Construtor returned by methods.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 2
 *
 * @challenges The analysis must sucessfully handle Constructor returned by methods.
 *
 */

public class MainActivity extends Activity {
    public String imei;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            Constructor cstr = findConstructor();
            MyClass obj = (MyClass) cstr.newInstance(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Constructor findConstructor() throws Exception {
        Class cls = this.getClassLoader().loadClass("junbin.ubc.MyClass");
        Constructor cstr = cls.getConstructor(Context.class);
        MyClass obj = (MyClass) cstr.newInstance(this);
        return cstr;
    }
}