package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.RecoveryActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;

public class EnterViewModel extends BaseObservable {

    private static EnterViewModel mInstance;
    private String result;

    private EnterViewModel(Context context) {
        Log.d("Force", "EnterViewModel::EnterViewModel()");
        try {
            this.setResult(context.getString(R.string.text_enter_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_enter_error, e.toString()));
        }
    }

    public static EnterViewModel getInstance(Context context) {
        Log.d("Force", "EnterViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new EnterViewModel(context);
        }
        return mInstance;
    }

    public void onEnter(Context context) {
        Log.d("Force", "EnterViewModel::onEnter()");
        Intent intent = new Intent(context, EnterResultActivity.class);
        context.startActivity(intent);
    }

    public void onRegister(Context context) {
        Log.d("Force", "EnterViewModel::onRegister()");
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    public void onRecovery(Context context) {
        Log.d("Force", "EnterViewModel::onRecovery()");
        Intent intent = new Intent(context, RecoveryActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
        Log.d("Force", "EnterViewModel::onResult()");
        Intent intent = new Intent(context, EnterActivity.class);
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