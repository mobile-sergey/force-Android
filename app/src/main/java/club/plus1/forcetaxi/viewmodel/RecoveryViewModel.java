package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.PasswordActivity;
import club.plus1.forcetaxi.view.RecoveryEmailActivity;
import club.plus1.forcetaxi.view.RecoveryPhoneActivity;

public class RecoveryViewModel extends BaseObservable {

    private static RecoveryViewModel mInstance;
    private String result;

    private RecoveryViewModel(Context context) {
        Log.d("Force", "RecoveryViewModel::RecoveryViewModel()");
        try {
            this.setResult(context.getString(R.string.text_recovery_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_recovery_error, e.toString()));
        }
    }

    public static RecoveryViewModel getInstance(Context context) {
        Log.d("Force", "RecoveryViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new RecoveryViewModel(context);
        }
        return mInstance;
    }

    public void onRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onRecovery()");
        Intent intent = new Intent(context, RecoveryEmailActivity.class);
        context.startActivity(intent);
    }

    public void onEmailRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onEmailRecovery()");
        Intent intent = new Intent(context, RecoveryPhoneActivity.class);
        context.startActivity(intent);
    }

    public void onPhoneRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onPhoneRecovery()");
        Intent intent = new Intent(context, PasswordActivity.class);
        context.startActivity(intent);
    }

    @Bindable
    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
        notifyPropertyChanged(BR.result);
    }
}
