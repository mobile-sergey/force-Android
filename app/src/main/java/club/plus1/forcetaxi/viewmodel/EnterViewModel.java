package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import java.util.Objects;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterPinActivity;
import club.plus1.forcetaxi.view.EnterPinConfirmActivity;
import club.plus1.forcetaxi.view.EnterPinResultActivity;
import club.plus1.forcetaxi.view.EnterPinSetActivity;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;
import club.plus1.forcetaxi.view.RegistrationRecoveryActivity;

public class EnterViewModel extends BaseObservable {

    // Поле экранов "30.Установка ПИН", "31.Подтверждение ПИН",
    // "32.Установка ПИН. Результат", "33.Ввод ПИН"
    public ObservableField<String> pin = new ObservableField<>();
    private ObservableField<String> firstPin = new ObservableField<>();

    // Поля экрана "0.Заставка"
    public ObservableField<String> version = new ObservableField<>();

    // Поля экрана "1.Вход"
    public ObservableField<String> login = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    // Поле экрана "2.Результат входа"
    public ObservableField<String> result = new ObservableField<>();

    // Ссылки MVVM
    private static EnterViewModel mInstance;    // Ссылка для биндинга с View
    private Server server;                      // Ссылка на Model

    // Конструктор класса
    private EnterViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = Server.getInstance(context);
        version.set(getVersion(context));
        startEnterActivity(context);
        pin.set("");
    }

    // Получение единственного экземпляра класса
    public static EnterViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new EnterViewModel(context);
        }
        return mInstance;
    }

    // "0.Заставка" -> "1.Вход"
    // "0.Заставка" -> "33.Ввод ПИН"
    // Выполняется после завершения загрузки приложения
    // В зависимости от наличия пинкода. Если пинкод установлен ->33, если не установлен ->1
    private void startEnterActivity(final Context context) {
        ActiveLog.getInstance().log();
        final int SPLASH_TIME = 1000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // По истечении времени, запускаем главный активити или экран ввода пинкода
                Intent mainIntent;
                if (server.user.getPin().isEmpty()) {
                    mainIntent = new Intent(context, EnterActivity.class);
                } else {
                    mainIntent = new Intent(context, EnterPinActivity.class);
                }
                context.startActivity(mainIntent);
            }
        }, SPLASH_TIME);
    }

    // "1.Вход" -> "2.Результат входа"
    // Выполняется при нажатии кнопки "Войти"
    // Вызывает серверный метод login
    public void onEnter(Context context) {
        ActiveLog.getInstance().log();
        server.login(context, login.get(), password.get());
        result.set(server.getError().getText());
        if (server.isOk()) {
            Intent intent = new Intent(context, EnterResultActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, result.get(), Toast.LENGTH_LONG).show();
        }
    }

    // "1.Вход" -> "3.Регистрация"
    // Выполняется при нажатии ссылки "Зарегистрироваться" в экране "1.Вход"
    public void onRegister(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, RegistrationActivity.class);
        context.startActivity(intent);
    }

    // "1.Вход" -> "6.Восстановление пароля"
    // Выполняется при нажатии ссылки "Забыл пароль"
    public void onRecovery(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, RegistrationRecoveryActivity.class);
        intent.putExtra("login", login);
        context.startActivity(intent);
    }

    // "2.Результат входа" -> "1.Вход"
    // "2.Результат входа" -> "34.Закрытое меню"
    // В зависимости от результата входа: если успешен ->34, если не упешен ->1
    // Выполняется при нажатия на экране
    public void onResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent;
        if (server.isOk()) {
            intent = new Intent(context, MenuCheckActivity.class);
        } else {
            intent = new Intent(context, EnterActivity.class);
        }
        context.startActivity(intent);
    }

    // "30.Установка ПИН" -> "31.Подтверждение ПИН"
    // Выполняется при нажатии кнопки "Продолжить"
    public void onPinSet(Context context) {
        ActiveLog.getInstance().log();
        firstPin.set(pin.get());
        pin.set("");
        Intent intent = new Intent(context, EnterPinConfirmActivity.class);
        context.startActivity(intent);
    }

    // "31.Подтверждение ПИН" -> "32.Установка ПИН. Результат"
    // Выполняется при нажатии кнопки "Продолжить"
    public void onPinConfirm(Context context) {
        ActiveLog.getInstance().log();
        if (Objects.requireNonNull(firstPin.get()).equals(pin.get())) {
            server.user.setPin(pin.get());
            server.user.isPinSet = true;
            Intent intent = new Intent(context, EnterPinResultActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.text_pin_confirm_error), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, EnterPinSetActivity.class);
            context.startActivity(intent);
        }
    }

    // "32.Установка ПИН. Результат" -> "34.Закрытое меню"
    // Выполняется при нажатии на экран
    public void onPinResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }

    // "33.	Ввод ПИН" -> "34.Закрытое меню"
    // Выполняется при нажатии кнопки "Продолжить"
    public void onPinEnter(Context context) {
        ActiveLog.getInstance().log();
        if (Objects.requireNonNull(pin.get()).equals(server.user.getPin())) {
            Intent intent = new Intent(context, MenuCheckActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.text_pin_error), Toast.LENGTH_LONG).show();
        }
    }

    // Числовая клаввиатура для набора ПИНкода
    public void addNumber(String number) {
        ActiveLog.getInstance().log();
        pin.set(pin.get() + number);
    }

    // Получение номера версии из настроек проекта
    private String getVersion(Context context) {
        // Получаем информацию о пакете из которого получим версию и номер сборки
        // Если не удалось обратиться к пакету - создаём каркас с необходимыми нам полями
        PackageInfo pInfo = null;
        try {
            pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (pInfo == null) {
            pInfo = new PackageInfo();
            pInfo.versionName = "0.0";
            pInfo.versionCode = 0;
        }
        return pInfo.versionName + "." + pInfo.versionCode;
    }

}