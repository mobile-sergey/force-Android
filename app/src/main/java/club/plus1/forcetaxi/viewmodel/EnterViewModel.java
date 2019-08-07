package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.ActiveLog;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.RecoveryActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class EnterViewModel extends BaseObservable {

    private static EnterViewModel mInstance;
    private String result;
    public String login;
    public String password;

    private EnterViewModel(Context context) {
        ActiveLog.getInstance().log();
        try {
            this.setResult(context.getString(R.string.text_enter_success));
        } catch (Exception e) {
            this.setResult(context.getString(R.string.text_enter_error, e.toString()));
        }
    }

    public static EnterViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new EnterViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "2.Результат входа" при нажатии кнопки "Войти" в экране "1.Вход"
    public void onEnter(Context context) {
        ActiveLog.getInstance().log();

        Server server = Server.getInstance(context);
        server.login(context, login, password);
        if (server.isOk()) {
            result = context.getString(R.string.text_enter_success);
        } else {
            result = context.getString(R.string.text_enter_error, server.getError().getText());
        }
        Toast.makeText(context, server.getError().getText(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(context, EnterResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "3.Регистрация" при нажатии ссылки "Зарегистрироваться" в экране "1.Вход"
    public void onRegister(Context context) {
        ActiveLog.getInstance().log();

        RegistrationViewModel registrationViewModel = RegistrationViewModel.getInstance(context);
        registrationViewModel.login = login;
        registrationViewModel.password = password;

        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "6.Восстановление пароля" при нажатии ссылки "Забыл пароль" в экране "1.Вход"
    public void onRecovery(Context context) {
        ActiveLog.getInstance().log();

        RecoveryViewModel recoveryViewModel = RecoveryViewModel.getInstance(context);
        recoveryViewModel.login = login;

        Intent intent = new Intent(context, RecoveryActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатия на экране "2.Результат входа"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onResult(Context context) {
        ActiveLog.getInstance().log();

        Intent intent = new Intent(context, SplashActivity.class);
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