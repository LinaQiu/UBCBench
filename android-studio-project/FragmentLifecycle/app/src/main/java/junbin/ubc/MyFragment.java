package junbin.ubc;

import android.app.Activity;
import android.app.Fragment;
import android.util.Log;

public class MyFragment extends Fragment {
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.d("TAG", "Title: " + activity.getTitle());
    }
}
