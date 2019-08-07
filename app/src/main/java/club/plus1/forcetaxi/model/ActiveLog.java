package club.plus1.forcetaxi.model;

import android.util.Log;

public class ActiveLog {

    private static final String TAG = "Force";
    private static ActiveLog mInstance;
    private static boolean active;

    private ActiveLog() {
        ActiveLog.setActive(true);
    }

    public static ActiveLog getInstance() {
        if (null == mInstance)
            mInstance = new ActiveLog();
        return mInstance;
    }

    private static boolean isActive() {
        return active;
    }

    private static void setActive(boolean active) {
        ActiveLog.active = active;
    }

    public void log() {
        if (isActive())
            Log.d(TAG, "" + (new Exception().getStackTrace()[1].getFileName()
                    .replaceAll(".java\\s*$", ""))
                    + "." + (new Exception().getStackTrace()[1].getMethodName()) + "()");
    }
}
