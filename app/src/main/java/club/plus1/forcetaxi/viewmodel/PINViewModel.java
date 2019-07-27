package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.view.PinConfirmActivity;
import club.plus1.forcetaxi.view.PinEnterActivity;
import club.plus1.forcetaxi.view.PinResultActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class PinViewModel extends BaseObservable {

    private static PinViewModel mInstance;
    public String result;
    private String pin;

    private PinViewModel(Context context) {
        Log.d("Force", "PinViewModel::PinViewModel()");
        this.pin = "";
    }

    public static PinViewModel getInstance(Context context) {
        Log.d("Force", "PinViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new PinViewModel(context);
        }
        return mInstance;
    }

    // Запуск экрана "31.Подтверждение ПИН" при нажатии кнопки "Продолжить"
    // в экране "30.Установка ПИН"
    public void onSet(Context context) {
        Log.d("Force", "PinViewModel::onSet()");
        this.pin = "";
        Intent intent = new Intent(context, PinConfirmActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "31.Подтверждение ПИН" при нажатии кнопки "Продолжить"
    // в экране "32.Установка ПИН. Результат"
    public void onConfirm(Context context) {
        Log.d("Force", "PinViewModel::onConfirm()");
        this.pin = "";
        Intent intent = new Intent(context, PinResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "33.Ввод ПИН" при нажатии на экран "32.Установка ПИН. Результат"
    public void onResult(Context context) {
        Log.d("Force", "PinViewModel::onResult()");
        Intent intent = new Intent(context, PinEnterActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатии кнопки "Продолжить" в экране "33.Ввод ПИН"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onEnter(Context context) {
        Log.d("Force", "PinViewModel::onEnter()");
        Intent intent = new Intent(context, SplashActivity.class);
        context.startActivity(intent);
    }

    public void addNumber(Context context, String number) {
        setPin(getPin() + number);
        notifyPropertyChanged(BR.pin);
    }

    @Bindable
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
        notifyPropertyChanged(BR.pin);
    }
}
