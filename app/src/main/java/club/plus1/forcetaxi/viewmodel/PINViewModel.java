package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PinConfirmActivity;
import club.plus1.forcetaxi.view.PinEnterActivity;
import club.plus1.forcetaxi.view.PinResultActivity;

public class PinViewModel {

    private static PinViewModel mInstance;

    private PinViewModel(Context context) {
        Log.d("Force", "PinViewModel::PinViewModel()");
    }

    public static PinViewModel getInstance(Context context) {
        Log.d("Force", "PinViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new PinViewModel(context);
        }
        return mInstance;
    }

    public void onSet(Context context) {
        Log.d("Force", "PinViewModel::onSet()");
        Intent intent = new Intent(context, PinConfirmActivity.class);
        context.startActivity(intent);
    }

    public void onConfirm(Context context) {
        Log.d("Force", "PinViewModel::onConfirm()");
        Intent intent = new Intent(context, PinResultActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
        Log.d("Force", "PinViewModel::onResult()");
        Intent intent = new Intent(context, PinEnterActivity.class);
        context.startActivity(intent);
    }

    public void onEnter(Context context) {
        Log.d("Force", "PinViewModel::onEnter()");
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

}
