package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.PasswordActivity;
import club.plus1.forcetaxi.view.RecoveryEmailActivity;
import club.plus1.forcetaxi.view.RecoveryPhoneActivity;

public class RecoveryViewModel extends BaseObservable {

    private String result;

    public RecoveryViewModel(Context context) {
        try {
            this.setResult(context.getString(R.string.text_recovery_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_recovery_error, e.toString()));
        }
    }

    public void onRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryEmailActivity.class);
        context.startActivity(intent);
    }

    public void onEmailRecovery(Context context) {
        Intent intent = new Intent(context, RecoveryPhoneActivity.class);
        context.startActivity(intent);
    }

    public void onPhoneRecovery(Context context) {
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
