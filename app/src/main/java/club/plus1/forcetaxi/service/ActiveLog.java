package club.plus1.forcetaxi.service;

import android.util.Log;

import club.plus1.forcetaxi.model.ServerError;

public class ActiveLog {

    private static final String TAG = "Force";
    private static final int STACK_TRACE_NUMBER = 3;
    private static ActiveLog mInstance;
    private static boolean active;

    String message;
    boolean isError;

    private ActiveLog() {
        ActiveLog.setActive(true);
        isError = false;
        message = "";
    }

    public static ActiveLog getInstance() {
        if (null == mInstance)
            mInstance = new ActiveLog();
        return mInstance;
    }

    static boolean isActive() {
        return active;
    }

    private static void setActive(boolean active) {
        ActiveLog.active = active;
    }

    public void log() {
        if (isActive()) {
            StackTraceElement element = Thread.currentThread().getStackTrace()[STACK_TRACE_NUMBER];
            String className = (element.getFileName().replaceAll(".java\\s*$", ""));
            String methodName = element.getMethodName();
            isError = false;
            message = "" + className + "." + methodName + "()";
            Log.d(TAG, message);
        }
    }

    void logError(boolean ok, ServerError error) {
        if (isActive()) {
            message = error.getText();
            isError = ok;
            if (isError) {
                Log.d(TAG, message);
            } else {
                Log.e(TAG, message);
            }
        }
    }
}
