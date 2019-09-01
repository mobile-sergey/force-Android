package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.LocalSettings;
import club.plus1.forcetaxi.model.ResponseAuth;
import club.plus1.forcetaxi.model.ResponseRegistration;
import club.plus1.forcetaxi.model.ResponseUser;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.stub.ConstantStub;
import club.plus1.forcetaxi.stub.ResponseAuthStub;
import club.plus1.forcetaxi.stub.ResponseRegistrationStub;
import club.plus1.forcetaxi.stub.ResponseUserStub;
import club.plus1.forcetaxi.stub.ServerStub;
import club.plus1.forcetaxi.view.EnterActivity;
import club.plus1.forcetaxi.view.EnterPinSetActivity;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSetActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;
import club.plus1.forcetaxi.view.MenuInstructionActivity;
import club.plus1.forcetaxi.view.MenuInviteActivity;
import club.plus1.forcetaxi.view.RegistrationFinishedActivity;
import club.plus1.forcetaxi.view.RegistrationPasswordActivity;
import club.plus1.forcetaxi.view.RegistrationPasswordResultActivity;
import club.plus1.forcetaxi.view.RegistrationRecoveryEmailActivity;
import club.plus1.forcetaxi.view.RegistrationRecoveryPhoneActivity;
import club.plus1.forcetaxi.view.RegistrationVerificationActivity;

import static java.util.Objects.requireNonNull;

public class RegistrationViewModel extends BaseObservable {

    // Поля экранов "3.Регистрация" и "9.Смена пароля"
    public ObservableField<String> password = new ObservableField<>();

    // Поля экрана "3.Регистрация"
    public ObservableField<String> phone = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    // Поле экранов "4.Регистрация. Подтверждение телефона" и "8.Восстановление пароля по телефону"
    public ObservableField<String> code = new ObservableField<>();
    public ObservableField<String> confirm = new ObservableField<>();
    // Поле экрана "6.Восстановление пароля"
    public ObservableField<String> login = new ObservableField<>();

    // Поля экрана "5.Регистрация завершена"
    public ObservableField<Drawable> srcTighten = new ObservableField<>();
    public ObservableField<Drawable> srcInFns = new ObservableField<>();
    public ObservableField<Drawable> srcForceAccepted = new ObservableField<>();
    public ObservableField<Drawable> srcPinSet = new ObservableField<>();

    // Поле экрана "7.Восстановление пароля по e-mail"
    public ObservableField<String> result = new ObservableField<>();

    // Поле экрана "27.Профиль"
    public ObservableField<String> inn = new ObservableField<>();
    public ObservableField<String> fio = new ObservableField<>();
    public ObservableField<String> oktmo = new ObservableField<>();
    public ObservableField<String> dateFNS = new ObservableField<>();

    // Ссылки MVVM
    private static RegistrationViewModel mInstance; // Ссылка для биндинга с View
    private ServerStub server;                      // Ссылка на Model сервера
    private ResponseAuth responseAuth;                      // Ссылка на Model авторизации
    private ResponseRegistration responseReg;      // Ссылка на Model регистрации
    private ResponseUser responseUser;              // Ссылка на Model пользователя
    private LocalSettings settings;                 // Ссылка на Model локальных настроек

    // Конструктор класса
    private RegistrationViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = ServerStub.getInstance(ConstantStub.APP_TOKEN);
        responseAuth = new ResponseAuthStub(ConstantStub.APP_TOKEN);
        responseReg = new ResponseRegistrationStub(ConstantStub.APP_TOKEN);
        responseUser = new ResponseUserStub(ConstantStub.APP_TOKEN);
        settings = new LocalSettings();

        srcTighten.set(getDrawable(context, null));
        srcInFns.set(getDrawable(context, null));
        srcForceAccepted.set(getDrawable(context, null));
        srcPinSet.set(getDrawable(context, null));
    }

    // Получение единственного экземпляра класса
    public static RegistrationViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new RegistrationViewModel(context);
        }
        return mInstance;
    }

    // "3.Регистрация" -> "4.Регистрация. Подтверждение телефона"
    // Выполняется при нажатии кнопки "Регистрация"
    // Вызывает серверные методы signUp и sendSMS
    public void onRegistration(Context context) {
        ActiveLog.getInstance().log();
        if (requireNonNull(password.get()).equals(confirm.get())) {
            responseReg.createUser(phone.get(), email.get(),
                    "", "", "", password.get());
            result.set(responseReg.getErrorText());
            if (responseReg.ok) {
                server.smsCode = responseReg.requestSmsCode(phone.get());
                result.set(responseReg.getErrorText());
                Intent intent = new Intent(context, RegistrationVerificationActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, result.get(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, context.getString(R.string.text_signup_confirm_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    // "4.Регистрация. Подтверждение телефона" или "8.Восстановление пароля по телефону"
    // Выполняется при нажатии кнопки "Отправить СМС"
    // Вызывает серверный метод sendSMS
    public void onSendSMS(Context context) {
        ActiveLog.getInstance().log();
        server.smsCode = responseReg.requestSmsCode(phone.get());
        result.set(responseReg.getErrorText());
        Toast.makeText(context, result.get(), Toast.LENGTH_LONG).show();
    }

    // "4.Регистрация. Подтверждение телефона" -> "5.Регистрация завершена"
    // Выполняется при нажатии кнопки "Подтвердить"
    // Вызывает серверные методы acceptResetPass и getUser
    public void onVerification(Context context) {
        ActiveLog.getInstance().log();
        responseAuth.passwordReset(phone.get(), code.get(), password.get());
        server.user = responseUser.getUser();
        result.set(responseAuth.getErrorText() + "\n" + responseUser.getErrorText());
        Intent intent = new Intent(context, RegistrationFinishedActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "11.Указание ИНН"
    // "27.Профиль" -> "11.Указание ИНН"
    // Выполняется при нажатии ссылки "Привязать учет доходов и выбивание чеков"
    public void onTighten(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, InnSetActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "15.Мой налог. Просмотре инструкции"
    // "27.Профиль" -> "15.Мой налог. Просмотре инструкции"
    // Выполняется при нажатии ссылки "Зарегистрироваться в ФНС"
    public void onInFns(Context context) {
        ActiveLog.getInstance().log();
        settings.setInFns(true);
        srcInFns.set(getDrawable(context, true));
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "28.Инструкция"
    // "27.Профиль" -> "28.Инструкция"
    // Выполняется при нажатии ссылки "Предоставить права площадке ..."
    public void onForceAccepted(Context context) {
        ActiveLog.getInstance().log();
        settings.setForceAccepted(true);
        srcForceAccepted.set(getDrawable(context, true));
        Intent intent = new Intent(context, MenuInstructionActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "30.Установка ПИН"
    // "27.Профиль" -> "30.Установка ПИН"
    // Выполняется при нажатии ссылки "Установить ПИН"
    public void onPIN(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterPinSetActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "34.Закрытое меню"
    // Выполняется при нажатии кнопки "Настроить позже"
    public void onLater(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }

    // 6.Восстановление пароля" -> "7.Восстановление пароля по e-mail"
    // 6.Восстановление пароля" -> "8.Восстановление пароля по телефону"
    // В зависимости от введенного логина: если введена почта -> 7, если введен телефон -> 8
    // Выполняется нажатии кнопки "Восстановить"
    // Вызывает серверные методы reserPassword и (sendMail или sendSMS)
    public void onRecovery(Context context) {
        ActiveLog.getInstance().log();
        if ((login == null) || (login.get() == null) || requireNonNull(login.get()).isEmpty()) {
            Toast.makeText(context, context.getString(R.string.text_need_login), Toast.LENGTH_LONG).show();
        } else if (requireNonNull(login.get()).contains("@")) {  // Найдена @ - значит введен email
            email.set(login.get());
            responseAuth.sendMail(email.get());
            result.set(responseAuth.getErrorText());
            Intent intent = new Intent(context, RegistrationRecoveryEmailActivity.class);
            context.startActivity(intent);
        } else {                        // Не найдена @ - значит введен телефон
            phone.set(login.get());
            responseAuth.passwordRequestReset(phone.get());
            server.smsCode = responseReg.requestSmsCode(phone.get());
            result.set(responseAuth.getErrorText() + "\n" + responseReg.getErrorText());
            Intent intent = new Intent(context, RegistrationRecoveryPhoneActivity.class);
            context.startActivity(intent);
        }
    }

    // "7.Восстановление пароля по e-mail" -> "1.Вход"
    // Выполняется при нажатии на экране
    public void onEmailRecovery(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    // "8.Восстановление пароля по телефону" -> "9.Смена пароля"
    // Выполняется при нажатии кнопки "Подтвердить"
    // Вызывает серверный метод acceptResetPass
    public void onPhoneRecovery(Context context) {
        ActiveLog.getInstance().log();
        responseAuth.passwordReset(phone.get(), password.get(), code.get());
        result.set(responseAuth.getErrorText());
        Intent intent = new Intent(context, RegistrationPasswordActivity.class);
        context.startActivity(intent);
    }

    // "9.Смена пароля" -> "10.Смена пароля. Результат"
    // Выполняется при нажатии кнопки "Сменить пароль"
    // Вызывает серверный метод setPassword
    public void onPasswordChange(Context context) {
        ActiveLog.getInstance().log();
        if (requireNonNull(password.get()).equals(confirm.get())) {
            responseAuth.passwordReset(phone.get(), password.get(), code.get());
            result.set(responseAuth.getErrorText());
            Intent intent = new Intent(context, RegistrationPasswordResultActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.text_signup_confirm_error),
                    Toast.LENGTH_LONG).show();
        }
    }

    // "10.Смена пароля. Результат" -> "1.Вход"
    // Выполняется при нажатии на экране
    public void onResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    // "27.Профиль" -> "29.Пригласить друга"
    // Выполняется при нажатии на ссылке "Пригласить друга"
    public void onInvite(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuInviteActivity.class);
        context.startActivity(intent);
    }

    // "27.Профиль" -> "1.Вход"
    // Выполняется при нажатии на ссылке "Выйти из профиля"
    public void onExit(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена"
    // Выполняется при открытии экрана "5.Регистрация завершена"
    public void onRegistrationFinished(Context context) {
        ActiveLog.getInstance().log();
        srcTighten.set(getDrawable(context, server.user.tinConnected));
        srcInFns.set(getDrawable(context, settings.isInFns()));
        srcForceAccepted.set(getDrawable(context, settings.isForceAccepted()));
        srcPinSet.set(getDrawable(context, settings.isPinSet()));
    }

    // "27.Профиль"
    // Выполняется при открытии экрана "27.Профиль"
    public void onRegistrationProfile(Context context) {
        ActiveLog.getInstance().log();
        srcTighten.set(getDrawable(context, server.user.tinConnected));
        srcInFns.set(getDrawable(context, settings.isInFns()));
        srcForceAccepted.set(getDrawable(context, settings.isForceAccepted()));
        srcPinSet.set(getDrawable(context, settings.isPinSet()));
    }

    // Получение картинки в зависимости от булевого значения
    private Drawable getDrawable(Context context, Boolean value) {
        if (value == null) {
            return context.getDrawable(R.drawable.wait);
        } else if (value) {
            return context.getDrawable(R.drawable.yes);
        } else {
            return context.getDrawable(R.drawable.no);
        }
    }
}