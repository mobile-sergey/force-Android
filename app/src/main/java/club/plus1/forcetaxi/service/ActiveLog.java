package club.plus1.forcetaxi.service;

import android.util.Log;

import club.plus1.forcetaxi.model.ServerError;

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
            Log.d(TAG, ""
                    + (Thread.currentThread().getStackTrace()[3].getFileName().replaceAll(".java\\s*$", ""))
                    + "." + (Thread.currentThread().getStackTrace()[3].getMethodName()) + "()");
    }

    public void logError(boolean ok, ServerError error) {
        if (isActive()) {
            if (ok) {
                Log.d(TAG, error.getText());
            } else {
                Log.e(TAG, error.getText());
            }
        }
    }

}
