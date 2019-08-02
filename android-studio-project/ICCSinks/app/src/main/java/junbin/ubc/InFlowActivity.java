package junbin.ubc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class InFlowActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        String imei = i.getStringExtra("imei");
        Log.i("TAG", imei); // sink and leak
    }
}