package junbin.ubc;

import android.app.Activity;
import android.app.ListFragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

public class MyFragment extends ListFragment {
    Activity activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;

        TelephonyManager mgr = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
        Log.i("TAG", mgr.getDeviceId()); // source, sink and leak
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TelephonyManager mgr = (TelephonyManager) activity.getSystemService(activity.TELEPHONY_SERVICE);
        Log.v("TAG", mgr.getDeviceId()); // source, sink and leak
    }
}
