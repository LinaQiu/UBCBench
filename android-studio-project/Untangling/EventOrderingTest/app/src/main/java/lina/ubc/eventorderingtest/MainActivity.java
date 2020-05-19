package lina.ubc.eventorderingtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startActivity(new Intent("lina.ubc.eventorderingtest.ACTION"));   //Sink: <android.app.Activity: void startActivity(android.content.Intent)> -> _SINK_

    }


}
