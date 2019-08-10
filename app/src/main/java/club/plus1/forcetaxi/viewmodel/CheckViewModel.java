package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.R;
import club.plus1.forcetaxi.model.Server;
import club.plus1.forcetaxi.service.ActiveLog;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.CheckStornoActivity;
import club.plus1.forcetaxi.view.CheckStornoResultActivity;
import club.plus1.forcetaxi.view.EnterSplashActivity;

public class CheckViewModel extends BaseObservable {

    private static CheckViewModel mInstance;
    private Server server;

    public String reason;
    public ObservableBoolean showCheck;
    private String total;
    private final String[] history;
    public ArrayAdapter<String> adapter;

    private CheckViewModel(Context context) {
        ActiveLog.getInstance().log();
        server = Server.getInstance(context);
        showCheck = new ObservableBoolean();
        showCheck.set(false);
        total = "";
        history = new String[]{
                context.getString(R.string.check_text, "1000"),
                context.getString(R.string.check_text, "100"),
                context.getString(R.string.check_text, "200"),
                context.getString(R.string.check_text, "555.55"),
                context.getString(R.string.check_text, "33.00"),
        };
        adapter = new ArrayAdapter<>(context, R.layout.check_item, R.id.textCheck, history);
    }

    public static CheckViewModel getInstance(Context context) {
        ActiveLog.getInstance().log();
        if (mInstance == null) {
            mInstance = new CheckViewModel(context);
        }
        return mInstance;
    }

    public void addNumber(Context context, String number) {
        ActiveLog.getInstance().log();
        if (!(number.equals(",") && total.contains(","))) {
            if (total.equals("0")) {
                total = number;
            } else {
                total = total + number;
            }
        }
        notifyPropertyChanged(BR.total);
    }

    public void Clear(Context context) {
        ActiveLog.getInstance().log();
        total = "0";
        notifyPropertyChanged(BR.total);
    }

    public void Back(Context context) {
        ActiveLog.getInstance().log();
        if (total.equals("0")) {
            total = "0";
        } else {
            total = total.substring(0, total.length() - 1);
        }
        notifyPropertyChanged(BR.total);
    }

    public void Print(Context context) {
        ActiveLog.getInstance().log();
        showCheck.set(!showCheck.get());
    }

    // Запуск экрана "19.История чеков" при нажатии ссылки "История чеков" в экране "18.Выбивание чека"
    public void onHistory(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, CheckHistoryActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "20.Сторнирование чека" при нажатии ссылки "Отменить" в экране "19.История чеков"
    public void onCheckCancel(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, CheckStornoActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "21.Сторнирование чека. Результат" при нажатии кнопки "Сторнировать чек"
    // в экране "20.Сторнирование чека""
    public void onStorno(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, CheckStornoResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатии на экране "21.Сторнирование чека. Результат"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onResult(Context context) {
        ActiveLog.getInstance().log();
        Intent intent = new Intent(context, EnterSplashActivity.class);
        context.startActivity(intent);
    }

    @Bindable
    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
        notifyPropertyChanged(BR.total);
    }
}
