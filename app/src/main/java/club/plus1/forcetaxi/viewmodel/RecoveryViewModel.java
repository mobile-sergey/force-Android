package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.LoginType;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.PasswordActivity;
import club.plus1.forcetaxi.view.RecoveryEmailActivity;
import club.plus1.forcetaxi.view.RecoveryPhoneActivity;

public class RecoveryViewModel extends BaseObservable {

    private static RecoveryViewModel mInstance;
    private String result;
    public String login;
    public String password;
    public String code;
    public String textCode;

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

    // Запуск экрана "7.Восстановление пароля по e-mail" или "8.Восстановление пароля по телефону"
    // при нажатии кнопки "Восстановить" в экране "6.Восстановление пароля"
    public void onRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onRecovery()");
        Server server = Server.getInstance(context);
        if (login.contains("@")) {  // Найдена @ - значит введен email
            server.reserPassword(context, login, LoginType.email);
            server.sendMail(context, login);
            Intent intent = new Intent(context, RecoveryEmailActivity.class);
            context.startActivity(intent);
        } else {                        // Не найдена @ - значит введен телефон
            server.reserPassword(context, login, LoginType.phone);
            server.sendSMS(context, login);
            Intent intent = new Intent(context, RecoveryPhoneActivity.class);
            context.startActivity(intent);
        }
    }

    // Запуск экрана "1.Вход" при нажатии на экране "7.Восстановление пароля по e-mail"
    public void onEmailRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onEmailRecovery()");
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    // Отправка СМС при нажатии кнопки "Отправить СМС" в экране "8.Восстановление пароля по телефону"
    public void onSendSMS(Context context) {
        Log.d("Force", "RecoveryViewModel::onSendSMS()");
        Server server = Server.getInstance(context);
        server.sendSMS(context, login);
    }

    // Запуск экрана "9.Смена пароля" при нажатии кнопки "Подтвердить"
    // в экране "8.Восстановление пароля по телефону"
    public void onPhoneRecovery(Context context) {
        Log.d("Force", "RecoveryViewModel::onPhoneRecovery()");
        Server server = Server.getInstance(context);
        server.acceptResetPass(context, code, password);
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
