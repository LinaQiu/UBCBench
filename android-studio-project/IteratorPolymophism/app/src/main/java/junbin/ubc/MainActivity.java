package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @testcase_name IteratorPolymophism
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description In this test case, there is an interface Fruit and two classes Apple and Banana
 * implemented it. A list of Fruit is created with an instance of Apple and an instance of Banana
 * stored in it. The list is iterated using iterator, and the leak() method of each instance is called.
 * Only when Apple.leak() is called, there is a leak.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle polymophism when iterator is used to access
 * instances stored in a list.
 *
 */

public class MainActivity extends Activity {
    public String imei = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        imei = telephonyManager.getDeviceId(); // source

        ArrayList<Fruit> fruits = new ArrayList<Fruit>();
        fruits.add(new Apple());
        fruits.add(new Banana());

        Iterator<Fruit> it = fruits.iterator();
        while (it.hasNext()) {
            it.next().leak();
        }
    }
    interface Fruit {
        void leak();
    }

    class Apple implements Fruit {
        public void leak() {
            Log.v("TAG", imei); // sink, leak
        }
    }

    class Banana implements Fruit {
        public void leak() {
            return;
        }
    }
}
