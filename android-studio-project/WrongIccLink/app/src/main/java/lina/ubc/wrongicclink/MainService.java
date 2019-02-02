package lina.ubc.wrongicclink;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

/**
 * @testcase_name WrongIccLink
 * @author Junbin ZHANG
 * @author_mail zjbthomas@ece.ubc.ca
 *
 * @description This is a test case used to prove a bug in IccTA of FlowDroid 2.7.1, which leads to exception of Wrong IccLink.
 * @dataflow
 * Expected sources: 0
 * Expected sinks: 0
 *
 * FlowDroid would report a RuntimeException of Wrong IccLink.
 *
 * @number_of_leaks 0
 * @challenges The analysis must sucessfully perform instrumentation of IccTA.
 *
 */
public class MainService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();

        Intent notification = new Intent(getApplicationContext(), NotificationListener.class);

        Data data = new Data();

        // invoke two startService, so IccTA will instrument two redirectors
        startService(new Intent(getApplicationContext(), DummyOne.class));
        startService(new Intent(getApplicationContext(), DummyTwo.class));

        // when there are two redirectors, the id pointer pointed to startService(notification) in ICC model is now pointed to data.condition
        if (data.condition) {
            startService(notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
