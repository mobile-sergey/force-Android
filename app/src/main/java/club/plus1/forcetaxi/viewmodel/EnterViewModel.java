package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.RecoveryActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;

public class EnterViewModel extends BaseObservable {

    private String result;

    public EnterViewModel(Context context) {
        try {
            this.setResult(context.getString(R.string.text_enter_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_enter_error, e.toString()));
        }
    }

    public void onEnter(Context context) {
        Intent intent = new Intent(context, EnterResultActivity.class);
        context.startActivity(intent);
    }

    public void onRegister(Context context) {
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    public void onRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryActivity.class);
        context.startActivity(intent);
    }

    public void onResult(Context context) {
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