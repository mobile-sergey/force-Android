package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableBoolean;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.CheckStornoActivity;
import club.plus1.forcetaxi.view.CheckStornoResultActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class CheckViewModel extends BaseObservable {

    private static CheckViewModel mInstance;
    public String reason;
    public ObservableBoolean showCheck;
    private String total;

    private CheckViewModel(Context context) {
        Log.d("Force", "EnterViewModel::EnterViewModel()");
        this.showCheck = new ObservableBoolean();
        this.showCheck.set(false);
        this.total = "";
    }

    public static CheckViewModel getInstance(Context context) {
        Log.d("Force", "EnterViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new CheckViewModel(context);
        }
        return mInstance;
    }

    public void addNumber(Context context, String number) {
        Log.d("Force", "EnterViewModel::addNumber() - " + number);
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
        Log.d("Force", "EnterViewModel::Clear()");
        total = "0";
        notifyPropertyChanged(BR.total);
    }

    public void Back(Context context) {
        Log.d("Force", "EnterViewModel::Back()");
        if (total.equals("0")) {
            total = "0";
        } else {
            total = total.substring(0, total.length() - 1);
        }
        notifyPropertyChanged(BR.total);
    }

    public void Print(Context context) {
        Log.d("Force", "EnterViewModel::Print() - " + showCheck.get());
        showCheck.set(!showCheck.get());
    }

    // Запуск экрана "19.История чеков" при нажатии ссылки "История чеков" в экране "18.Выбивание чека"
    public void onHistory(Context context) {
        Log.d("Force", "CheckViewModel::onHistory()");
        Intent intent = new Intent(context, CheckHistoryActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "20.Сторнирование чека" при нажатии ссылки "Отменить" в экране "19.История чеков"
    public void onCheckCancel(Context context) {
        Log.d("Force", "CheckViewModel::onCheckCancel()");
        Intent intent = new Intent(context, CheckStornoActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "21.Сторнирование чека. Результат" при нажатии кнопки "Сторнировать чек"
    // в экране "20.Сторнирование чека""
    public void onStorno(Context context) {
        Log.d("Force", "CheckViewModel::onStorno()");
        Intent intent = new Intent(context, CheckStornoResultActivity.class);
        context.startActivity(intent);
    }

    // Запуск экрана "0.Заставка" при нажатии на экране "21.Сторнирование чека. Результат"
    // TODO: Когда появится экран "34.Закрытое меню" - нужно будет перенаправлять туда
    public void onResult(Context context) {
        Log.d("Force", "CheckViewModel::onResult()");
        Intent intent = new Intent(context, SplashActivity.class);
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
