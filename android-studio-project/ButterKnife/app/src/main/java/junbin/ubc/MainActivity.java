package junbin.ubc;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Button;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @testcase_name ButterKnife
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case uses ButterKnife.
 *
 * @dataflow
 * Expected sources: 1
 * Expected sinks: 1
 *
 * @number_of_leaks 1
 *
 * @challenges The analysis must sucessfully handle ButterKnife.
 *
 */

public class MainActivity extends Activity {
    @BindView(R.id.button)
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void buttonClick() {
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        Log.v("TAG", manager.getDeviceId()); // source, sink, leak
    }
}