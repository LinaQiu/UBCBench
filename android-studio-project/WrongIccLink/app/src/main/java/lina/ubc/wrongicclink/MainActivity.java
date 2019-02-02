package lina.ubc.wrongicclink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Please refer to MainService.java for explanations of this benchmark.
 */

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent starterServiceIntent = new Intent(getApplicationContext(), MainService.class);
    }

}
