package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

import java.util.Objects;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.LoginType;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.service.OldServer;
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
    private OldServer oldServer;                          // Ссылка на Model

    // Конструктор класса
    private RegistrationViewModel(Context context) {
        ActiveLog.getInstance().log();
        oldServer = OldServer.getInstance(context);
        fio.set(oldServer.oldUser.getFio());
        inn.set(oldServer.oldUser.inn);
        oktmo.set(oldServer.oldUser.oktmo);
        dateFNS.set(oldServer.oldUser.dateFNS);
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
        if (Objects.requireNonNull(password.get()).equals(confirm.get())) {
            oldServer.signUp(context, phone.get(), email.get(), password.get());
            result.set(oldServer.getError().getText());
            if (oldServer.isOk()) {
                oldServer.sendSMS(context, phone.get());
                result.set(oldServer.getError().getText());
                Intent intent = new Intent(context, RegistrationVerificationActivity.class);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, result.get(), Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(context, context.getString(R.string.text_signup_confirm_error), Toast.LENGTH_LONG).show();
        }
    }

    // "4.Регистрация. Подтверждение телефона" или "8.Восстановление пароля по телефону"
    // Выполняется при нажатии кнопки "Отправить СМС"
    // Вызывает серверный метод sendSMS
    public void onSendSMS(Context context) {
        ActiveLog.getInstance().log();
        oldServer.sendSMS(context, phone.get());
        result.set(oldServer.getError().getText());
        Toast.makeText(context, result.get(), Toast.LENGTH_LONG).show();
    }

    // "4.Регистрация. Подтверждение телефона" -> "5.Регистрация завершена"
    // Выполняется при нажатии кнопки "Подтвердить"
    // Вызывает серверные методы acceptResetPass и getUser
    public void onVerification(Context context) {
        ActiveLog.getInstance().log();
        oldServer.acceptResetPass(context, code.get(), password.get());
        oldServer.getUser(context);
        result.set(oldServer.getError().getText());
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
        oldServer.oldUser.isInFns = true;
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // "5.Регистрация завершена" -> "28.Инструкция"
    // "27.Профиль" -> "28.Инструкция"
    // Выполняется при нажатии ссылки "Предоставить права площадке ..."
    public void onForceAccepted(Context context) {
        ActiveLog.getInstance().log();
        oldServer.oldUser.isForceAccepted = true;
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
        if ((login == null) || (login.get() == null) || Objects.requireNonNull(login.get()).isEmpty()) {
            Toast.makeText(context, context.getString(R.string.text_need_login), Toast.LENGTH_LONG).show();
        } else if (Objects.requireNonNull(login.get()).contains("@")) {  // Найдена @ - значит введен email
            email.set(login.get());
            oldServer.reserPassword(context, email.get(), LoginType.email);
            oldServer.sendMail(context, email.get());
            result.set(oldServer.getError().getText());
            Intent intent = new Intent(context, RegistrationRecoveryEmailActivity.class);
            context.startActivity(intent);
        } else {                        // Не найдена @ - значит введен телефон
            phone.set(login.get());
            oldServer.reserPassword(context, phone.get(), LoginType.phone);
            oldServer.sendSMS(context, phone.get());
            result.set(oldServer.getError().getText());
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
        oldServer.acceptResetPass(context, code.get(), password.get());
        result.set(oldServer.getError().getText());
        Intent intent = new Intent(context, RegistrationPasswordActivity.class);
        context.startActivity(intent);
    }

    // "9.Смена пароля" -> "10.Смена пароля. Результат"
    // Выполняется при нажатии кнопки "Сменить пароль"
    // Вызывает серверный метод setPassword
    public void onPasswordChange(Context context) {
        ActiveLog.getInstance().log();
        if (Objects.requireNonNull(password.get()).equals(confirm.get())) {
            oldServer.setPassword(context, login.get(), password.get());
            result.set(oldServer.getError().getText());
            Intent intent = new Intent(context, RegistrationPasswordResultActivity.class);
            context.startActivity(intent);
        } else {
            Toast.makeText(context, context.getString(R.string.text_signup_confirm_error), Toast.LENGTH_LONG).show();
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

    public void onRegistrationFinished(Context context) {
        ActiveLog.getInstance().log();
        srcTighten.set(getDrawable(context, oldServer.oldUser.isTighten));
        srcInFns.set(getDrawable(context, oldServer.oldUser.isInFns));
        srcForceAccepted.set(getDrawable(context, oldServer.oldUser.isForceAccepted));
        srcPinSet.set(getDrawable(context, oldServer.oldUser.isPinSet));
    }

    public void onRegistrationProfile(Context context) {
        ActiveLog.getInstance().log();
        srcTighten.set(getDrawable(context, oldServer.oldUser.isTighten));
        srcInFns.set(getDrawable(context, oldServer.oldUser.isInFns));
        srcForceAccepted.set(getDrawable(context, oldServer.oldUser.isForceAccepted));
        srcPinSet.set(getDrawable(context, oldServer.oldUser.isPinSet));
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