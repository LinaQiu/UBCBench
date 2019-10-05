package junbin.ubc;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

public class MainActivity_ViewBinding {
    public MainActivity_ViewBinding(Context context) {
        TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
     Log.v("TAG", manager.getDeviceId()); // source, sink, leak
   }
}
