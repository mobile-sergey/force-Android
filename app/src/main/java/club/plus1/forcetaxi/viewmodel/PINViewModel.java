package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PinConfirmActivity;
import club.plus1.forcetaxi.view.PinEnterActivity;
import club.plus1.forcetaxi.view.PinResultActivity;

public class PinViewModel {

    public PinViewModel(Context context) {
    }

    public void onSet(Context context) {
        Intent intent = new Intent(context, PinConfirmActivity.class);
        context.startActivity(intent);
    }

    public void onConfirm(Context context) {
        Intent intent = new Intent(context, PinResultActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
        Intent intent = new Intent(context, PinEnterActivity.class);
        context.startActivity(intent);
    }

    public void onEnter(Context context) {
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

}
