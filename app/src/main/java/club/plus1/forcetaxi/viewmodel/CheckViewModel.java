package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.format.DateFormat;
import android.widget.ArrayAdapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;

import club.plus1.forcetaxi.model.ResponseReceipt;
import club.plus1.forcetaxi.model.ServerReceipt;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.stub.ConstantStub;
import club.plus1.forcetaxi.stub.ResponseReceiptStub;
import club.plus1.forcetaxi.stub.ServerStub;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.CheckStornoActivity;
import club.plus1.forcetaxi.view.CheckStornoResultActivity;
import club.plus1.forcetaxi.view.EnterSplashActivity;

import static java.util.Objects.requireNonNull;

public class CheckViewModel extends BaseObservable {

    // Поля экрана "18.	Выбивание чека"
    public ObservableBoolean showCheck = new ObservableBoolean();
    public ObservableField<String> number = new ObservableField<>();
    public ObservableField<String> amount = new ObservableField<>();
    public ObservableField<String> serviceType = new ObservableField<>();
    public ObservableField<String> executor = new ObservableField<>();
    public ObservableField<String> fio = new ObservableField<>();
    public ObservableField<String> inn = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<Drawable> qr = new ObservableField<>();
    public ObservableField<String> client = new ObservableField<>();
    public ObservableField<String> result = new ObservableField<>();

    // Поле экрана "20.	Сторнирование чека"
    public ObservableField<String> reason = new ObservableField<>();

    // Ссылки MVVM
    private static CheckViewModel mInstance;    // Ссылка для биндинга с View
    public ArrayAdapter<String> adapter;        // Ссылка на адаптер во View
    private ServerStub server;                  // Ссылка на Model сервера
    private ResponseReceipt responseReceipt;    // Ссылка на Model чека

    // Конструктор класса
    private CheckViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = ServerStub.getInstance(ConstantStub.APP_TOKEN);
        responseReceipt = new ResponseReceiptStub(ConstantStub.APP_TOKEN);

        showCheck.set(false);
        amount.set("");
        //adapter = new ArrayAdapter<>(context, R.layout.check_item, R.id.textCheck, oldServer.history);
    }

    // Получение единственного экземпляра класса
    public static CheckViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new CheckViewModel(context);
        }
        return mInstance;
    }

    // "18.Выбивание чека"
    // Показ чека
    // Вызывает серверный метод generateCheck
    public void Print(Context context) {
        ActiveLog.getInstance().log();

        ServerReceipt receipt = responseReceipt.receipts(client.get(),
                Double.parseDouble(requireNonNull(amount.get())));
        number.set(Integer.toString(receipt.id));
        serviceType.set(receipt.serviceType);
        amount.set(Double.toString(receipt.amount));
        executor.set(receipt.executor);
        DateFormat dateFormat = new DateFormat();
        date.set(DateFormat.format("yyyy-MM-dd", receipt.date).toString());
        url.set(receipt.url);

        result.set(responseReceipt.getErrorText());
        showCheck.set(!showCheck.get());
    }

    // "18.Выбивание чека" -> "19.История чеков"
    // Выполняется при нажатии ссылки "История чеков"
    // Вызывает серверный метод getCheckHistory
    public void onHistory(Context context) {
        ActiveLog.getInstance().log();
        responseReceipt.getReceipts(50, 0);
        result.set(responseReceipt.getErrorText());
        Intent intent = new Intent(context, CheckHistoryActivity.class);
        context.startActivity(intent);
    }

    // "19.История чеков" -> "20.Сторнирование чека"
    // Выполняется при нажатии ссылки "Отменить"
    public void onCheckCancel(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, CheckStornoActivity.class);
        context.startActivity(intent);
    }

    // "20.Сторнирование чека" -> "21.Сторнирование чека. Результат"
    // Выполняется при нажатии кнопки "Сторнировать чек"
    // Вызывает серверный метод deleteCheck
    public void onStorno(Context context) {
        ActiveLog.getInstance().log();
        responseReceipt.cancelReceipt(Integer.parseInt(requireNonNull(number.get())),
                reason.get());
        result.set(responseReceipt.getErrorText());
        Intent intent = new Intent(context, CheckStornoResultActivity.class);
        context.startActivity(intent);
    }

    // "21.Сторнирование чека. Результат" -> "34.Закрытое меню"
    // Выполняется при нажатии на экране
    public void onResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterSplashActivity.class);
        context.startActivity(intent);
    }

    // Числовая клаввиатура для набора суммы
    public void addNumber(Context context, String number) {
        ActiveLog.getInstance().log();
        String total = amount.get();
        if (!(number.equals(",") && requireNonNull(total).contains(","))) {
            if (requireNonNull(total).equals("0")) {
                amount.set(number);
            } else {
                amount.set(total + number);
            }
        }
    }

    // Очистка суммы
    public void Clear(Context context) {
        ActiveLog.getInstance().log();
        amount.set("0");
    }

    // Стирание последнего символа в сумме
    public void Back(Context context) {
        ActiveLog.getInstance().log();
        String total = amount.get();
        if (requireNonNull(total).equals("0")) {
            amount.set("0");
        } else {
            amount.set(total.substring(0, total.length() - 1));
        }
    }
}
