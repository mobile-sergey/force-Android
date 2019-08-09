package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterPinConfirmActivity;
import club.plus1.forcetaxi.view.EnterPinResultActivity;
import club.plus1.forcetaxi.view.EnterResultActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;
import club.plus1.forcetaxi.view.RegistrationActivity;
import club.plus1.forcetaxi.view.RegistrationRecoveryActivity;

public class EnterViewModel extends BaseObservable {

    private static EnterViewModel mInstance;    // Ссылка для биндинга с View
    // Поле экранов "30.Установка ПИН", "31.Подтверждение ПИН",
    // "32.Установка ПИН. Результат", "33.Ввод ПИН"
    public ObservableField<String> pin = new ObservableField<>();

    // Поля экрана "1.Вход"
    public ObservableField<String> login = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    // Поле экрана "2.Результат входа"
    public ObservableField<String> result = new ObservableField<>();
    private Server server;                      // Ссылка на Model

    // Конструктор класса
    private EnterViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = Server.getInstance(context);
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

    // "1.Вход" -> "2.Результат входа"
    // Выполняется при нажатии кнопки "Войти"
    // Вызывает серверный метод login
    public void onEnter(Context context) {
        ActiveLog.getInstance().log();
        server.login(context, login.get(), password.get());
        result.set(server.getError().getText());
        Intent intent = new Intent(context, EnterResultActivity.class);
        context.startActivity(intent);
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
        pin.set("");
        Intent intent = new Intent(context, EnterPinConfirmActivity.class);
        context.startActivity(intent);
    }

    // "31.Подтверждение ПИН" -> "32.Установка ПИН. Результат"
    // Выполняется при нажатии кнопки "Продолжить"
    public void onPinConfirm(Context context) {
        ActiveLog.getInstance().log();
        // TODO: Добавить сравнение пинкода с введённым на предыдущем экране и собщение при отличии
        Intent intent = new Intent(context, EnterPinResultActivity.class);
        context.startActivity(intent);
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
        // TODO: Добавить сравнение пинкода с оригинальным и собщение при отличии
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }

    // Числовая клаввиатура для набора ПИНкода
    public void addNumber(Context context, String number) {
        ActiveLog.getInstance().log();
        pin.set(pin.get() + number);
    }
}