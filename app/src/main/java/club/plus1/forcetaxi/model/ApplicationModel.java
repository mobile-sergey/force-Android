package club.plus1.forcetaxi.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class ApplicationModel extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public static Context getAppContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
