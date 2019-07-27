package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PasswordResultActivity;

public class PasswordViewModel extends BaseObservable {

    private static PasswordViewModel mInstance;
    private String result;
    public String password;
    public String confirm;

    private PasswordViewModel(Context context) {
        Log.d("Force", "PasswordViewModel::PasswordViewModel()");
        try {
            this.setResult(context.getString(R.string.text_password_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_password_error, e.toString()));
        }
    }

    public static PasswordViewModel getInstance(Context context) {
        Log.d("Force", "PasswordViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new PasswordViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "10.Смена пароля. Результат" при нажатии кнопки "Сменить пароль"
    // в экране "9.Смена пароля"
    public void onPasswordChange(Context context) {
        Log.d("Force", "PasswordViewModel::onPasswordChange()");
        Server server = Server.getInstance(context);
        server.setPassword(context, password, confirm);
        Intent intent = new Intent(context, PasswordResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "1.Вход" при нажатии на экране "10.Смена пароля. Результат"
    public void onResult(Context context) {
        Log.d("Force", "PasswordViewModel::onResult()");
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
