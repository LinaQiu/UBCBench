package junbin.ubc;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyFragment extends Fragment implements View.OnClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedinstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_layout, container, false);
        Button button = (Button) inflate.findViewById(R.id.button);
        button.setOnClickListener(this);

        TelephonyManager manager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String imei =  manager.getDeviceId(); // source
        Log.v("TAG", imei); // sink, leak

        return inflate;
    }

    @Override
    public void onClick(View v) {
        TelephonyManager manager = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        String imei = manager.getDeviceId(); // source
        Log.v("TAG", imei); // sink, leak
    }
}

