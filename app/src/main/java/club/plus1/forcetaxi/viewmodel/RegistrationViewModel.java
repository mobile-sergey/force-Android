package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.view.InnInfoActivity;
import club.plus1.forcetaxi.view.InnSetActivity;
import club.plus1.forcetaxi.view.PinSetActivity;
import club.plus1.forcetaxi.view.RegistrationFinishedActivity;
import club.plus1.forcetaxi.view.RegistrationVerificationActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class RegistrationViewModel extends BaseObservable {

    private static RegistrationViewModel mInstance;

    public String textCode;
    public String login;
    public String password;
    public String confirm;
    public String phone;
    public String email;
    public String code;

    @Nullable
    private Boolean isTighten;
    @Nullable
    private Boolean isInFns;
    @Nullable
    private Boolean isForceAccepted;
    @Nullable
    private Boolean isPinSet;

    private RegistrationViewModel(Context context) {
        Log.d("Force", "RegistrationViewModel::RegistrationViewModel()");
        this.textCode = context.getString(R.string.text_code);
        setIsTighten(null);
        setIsInFns(null);
        setIsForceAccepted(null);
        setIsPinSet(null);
    }

    public static RegistrationViewModel getInstance(Context context) {
        Log.d("Force", "RegistrationViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new RegistrationViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "4.Регистрация. Подтверждение телефона" при нажатии кнопки "Регистрация"
    // в экране "3.Регистрация"
    public void onRegistration(Context context) {
        Log.d("Force", "RegistrationViewModel::onRegistration()");
        Server server = Server.getInstance(context);
        server.signUp(context, phone, email, password);
        server.sendSMS(context, phone);
        this.textCode = context.getString(R.string.text_code, phone);
        Intent intent = new Intent(context, RegistrationVerificationActivity.class);
        context.startActivity(intent);
    }

    // "Отправка СМС" при нажатии кнопки "Отправить СМС" в экране "4.Регистрация. Подтверждение телефона"
    public void onSendSMS(Context context) {
        Log.d("Force", "RegistrationViewModel::onSendSMS()");
        Server server = Server.getInstance(context);
        server.sendSMS(context, phone);
    }

    // Запуск экрана "5.Регистрация завершена" при нажатии кнопки "Подтвердить"
    // в экране "4.Регистрация. Подтверждение телефона"
    public void onVerification(Context context) {
        Log.d("Force", "RegistrationViewModel::onVerification()");
        Server server = Server.getInstance(context);
        server.acceptResetPass(context, code, password);
        server.getUser(context);
        Intent intent = new Intent(context, RegistrationFinishedActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатии кнопки "Настроить позже"
    // в экране "5.Регистрация завершена"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onLater(Context context) {
        Log.d("Force", "RegistrationViewModel::onLater()");
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "11.Указание ИНН" при нажатии ссылки "Привязать учет доходов и выбивание чеков"
    // в экране "5.Регистрация завершена"
    public void onTighten(Context context) {
        Log.d("Force", "RegistrationViewModel::onTighten()");
        Intent intent = new Intent(context, InnSetActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "15.Мой налог. Просмотре инструкции" при нажатии ссылки "Зарегистрироваться в ФНС"
    // в экране "5.Регистрация завершена"
    public void onInFns(Context context) {
        Log.d("Force", "RegistrationViewModel::onInFns()");
        Intent intent = new Intent(context, InnInfoActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатии ссылки "Предоставить права площадке ..."
    // в экране "5.Регистрация завершена"
    // TODO: Когда появится экран "28.Инструкция" - нужно будет перенаправлять туда
    public void onForceAccepted(Context context) {
        Log.d("Force", "RegistrationViewModel::onForceAccepted()");
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "30.Установка ПИН" при нажатии ссылки "Установить ПИН"
    // в экране "5.Регистрация завершена"
    public void onPIN(Context context) {
        Log.d("Force", "RegistrationViewModel::onPIN()");
        Intent intent = new Intent(context, PinSetActivity.class);
        context.startActivity(intent);
    }

    @Bindable
    @Nullable
    public Boolean getIsTighten() {
        return isTighten;
    }

    public void setIsTighten(@Nullable Boolean is) {
        this.isTighten = is;
        notifyPropertyChanged(BR.isTighten);
    }

    @Bindable
    @Nullable
    public Boolean getIsInFns() {
        return isInFns;
    }

    public void setIsInFns(@Nullable Boolean is) {
        this.isInFns = is;
        notifyPropertyChanged(BR.isInFns);
    }

    @Bindable
    @Nullable
    public Boolean getIsForceAccepted() {
        return isForceAccepted;
    }

    public void setIsForceAccepted(@Nullable Boolean is) {
        this.isForceAccepted = is;
        notifyPropertyChanged(BR.isForceAccepted);
    }

    @Bindable
    @Nullable
    public Boolean getIsPinSet() {
        return isPinSet;
    }

    public void setIsPinSet(@Nullable Boolean is) {
        this.isPinSet = is;
        notifyPropertyChanged(BR.isPinSet);
    }
}
