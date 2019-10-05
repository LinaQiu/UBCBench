package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @testcase_name ButterKnifeReflection
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case emulates how ButterKnife works using reflection.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle reflection used in ButterKnife.
 *
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bind();
    }

    public void bind() {
        Class<?> bindingClass = null;
        try {
            bindingClass = getClassLoader().loadClass("junbin.ubc.MainActivity_ViewBinding");
            Constructor<?> bindingCtor = bindingClass.getConstructor(Context.class);
            bindingCtor.newInstance(this); // The constructor of MainActivity_ViewBinding is invoked
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}