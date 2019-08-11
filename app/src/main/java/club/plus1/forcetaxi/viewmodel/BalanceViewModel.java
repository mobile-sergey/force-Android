package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.view.BalanceRechargeActivity;
import club.plus1.forcetaxi.view.BalanceRechargeResultActivity;
import club.plus1.forcetaxi.view.BalanceSberbankActivity;
import club.plus1.forcetaxi.view.BalanceSberbankResultActivity;
import club.plus1.forcetaxi.view.MenuCheckActivity;

public class BalanceViewModel {

    // Ссылки MVVM
    private static BalanceViewModel mInstance;  // Ссылка для биндинга с View
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
    public ArrayAdapter<String> adapter;        // Ссылка на адаптер во View
    private Server server;                      // Ссылка на Model

    // Конструктор класса
    private BalanceViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = Server.getInstance(context);
        balance.set(0);
        adapter = new ArrayAdapter<>(context, R.layout.balance_item, R.id.textStatus, server.transactions);
        fio.set(server.user.getFio());
        inn.set(server.user.inn);
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
        server.sendSMS(context, phone.get());
        result.set(server.getError().getText());
        Intent intent = new Intent(context, BalanceSberbankActivity.class);
        context.startActivity(intent);
    }

    // "23.Пополнение баланса" -> "24.Пополнение баланса. Результат"
    // Выполняется при нажатии кнопки "Пополнить баланс"
    // Вызывает серверный метод refillBalance
    public void onRechargeSberbank(Context context) {
        ActiveLog.getInstance().log();
        server.refillBalance(context, amount.get(), gett.get());
        server.refillBalance(context, amount.get(), ytaxi.get());
        server.refillBalance(context, amount.get(), city.get());
        server.refillBalance(context, amount.get(), bolt.get());
        status.set(server.getArgs().get("status").toString());
        result.set(server.getError().getText());
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
        server.sendSMS(context, phone.get());
        result.set(server.getError().getText());
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
