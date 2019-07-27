package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PasswordResultActivity;

public class PasswordViewModel extends BaseObservable {

    private String result;

    public PasswordViewModel(Context context) {
        try {
            this.setResult(context.getString(R.string.text_password_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_password_error, e.toString()));
        }
    }

    public void onPasswordChange(Context context) {
        Intent intent = new Intent(context, PasswordResultActivity.class);
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
