package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import club.plus1.forcetaxi.model.ResponseBalance;
import club.plus1.forcetaxi.model.ResponseRegistration;
import club.plus1.forcetaxi.model.TransactionStatus;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.stub.ConstantStub;
import club.plus1.forcetaxi.stub.ResponseBalanceStub;
import club.plus1.forcetaxi.stub.ResponseRegistrationStub;
import club.plus1.forcetaxi.stub.ServerStub;
import club.plus1.forcetaxi.view.BalanceRechargeActivity;
import club.plus1.forcetaxi.view.BalanceRechargeResultActivity;
import club.plus1.forcetaxi.view.BalanceSberbankActivity;
import club.plus1.forcetaxi.view.BalanceSberbankResultActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;

public class BalanceViewModel {

    // Поля экрана "22.Баланс"
    public ObservableField<String> fio = new ObservableField<>();
    public ObservableField<String> inn = new ObservableField<>();
    public ObservableInt balance = new ObservableInt();

    // Поля экрана "23.Пополнение баланса"
    public ObservableField<String> amount = new ObservableField<>();
    public ObservableField<String> gett = new ObservableField<>();
    public ObservableField<String> ytaxi = new ObservableField<>();
    public ObservableField<String> city = new ObservableField<>();
    public ObservableField<String> bolt = new ObservableField<>();

    // Поле экрана "24.	Пополнение баланса. Результат"
    public ObservableField<String> status = new ObservableField<>();

    // Поля экрана "25.Пополнение баланса. Ссылка на Сбербанк"
    public ObservableField<String> phone = new ObservableField<>();

    // Поле экрана "26.	Пополнение баланса. Сбербанк. Результат"
    public ObservableField<String> result = new ObservableField<>();

    private static BalanceViewModel mInstance;  // Ссылка для биндинга с View
    // Ссылки MVVM
    public ArrayAdapter<String> adapter;        // Ссылка на адаптер во View
    private ServerStub server;                  // Ссылка на Model сервера
    private ResponseBalance responseBalance;    // Ссылка на Model баланса
    private ResponseRegistration responseReg;   // Ссылка на Model регистрации

    // Конструктор класса
    private BalanceViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = ServerStub.getInstance(ConstantStub.APP_TOKEN);
        responseBalance = new ResponseBalanceStub(ConstantStub.APP_TOKEN);
        responseReg = new ResponseRegistrationStub(ConstantStub.APP_TOKEN);
        balance.set(0);
        //adapter = new ArrayAdapter<>(context, R.layout.balance_item, R.id.textStatus, oldServer.transactions);
    }

    // Получение единственного экземпляра класса
    public static BalanceViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new BalanceViewModel(context);
        }
        return mInstance;
    }

    // "22.Баланс" -> "23.Пополнение баланса"
    // Выполняется при нажатии кнопки "Пополнить баланс"
    public void onRecharge(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, BalanceRechargeActivity.class);
        context.startActivity(intent);
    }

    // "23.Пополнение баланса" -> "25.Пополнение баланса. Ссылка на Сбербанк"
    // Выполняется при нажатии кнопки "Отправить ссылку для Сбербанка"
    // Вызывает серверный метод sendSMS
    public void onSendSberbank(Context context) {
        ActiveLog.getInstance().log();
        responseReg.requestSmsCode(phone.get());
        result.set(responseReg.getErrorText());
        Intent intent = new Intent(context, BalanceSberbankActivity.class);
        context.startActivity(intent);
    }

    // "23.Пополнение баланса" -> "24.Пополнение баланса. Результат"
    // Выполняется при нажатии кнопки "Пополнить баланс"
    // Вызывает серверный метод refillBalance
    public void onRechargeSberbank(Context context) {
        ActiveLog.getInstance().log();
        responseBalance.balance();
        /*
        oldServer.refillBalance(context, amount.get(), gett.get());
        oldServer.refillBalance(context, amount.get(), ytaxi.get());
        oldServer.refillBalance(context, amount.get(), city.get());
        oldServer.refillBalance(context, amount.get(), bolt.get());
         */
        status.set(TransactionStatus.processing.toString());
        result.set(responseBalance.getErrorText());
        Intent intent = new Intent(context, BalanceRechargeResultActivity.class);
        context.startActivity(intent);
    }

    // "24.Пополнение баланса. Результат" -> "34.Закрытое меню"
    // Выполняется при нажатии на экране
    public void onResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }

    // "25.Пополнение баланса. Ссылка на Сбербанк" -> "26.Пополнение баланса. Сбербанк. Результат"
    // Выполняется при нажатии на экране
    // Вызывает серверный метод sendSMS
    public void onSendLink(Context context) {
        ActiveLog.getInstance().log();
        responseReg.requestSmsCode(phone.get());
        result.set(responseReg.getErrorText());
        Intent intent = new Intent(context, BalanceSberbankResultActivity.class);
        context.startActivity(intent);
    }

    // "26.Пополнение баланса. Сбербанк. Результат" -> "26.Пополнение баланса. Сбербанк. Результат"
    // Выполняется при нажатии на экране
    public void onSberbankResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, MenuCheckActivity.class);
        context.startActivity(intent);
    }
}
