package club.plus1.forcetaxi.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import club.plus1.forcetaxi.BR;
import club.plus1.forcetaxi.view.CheckHistoryActivity;
import club.plus1.forcetaxi.view.CheckStornoActivity;
import club.plus1.forcetaxi.view.SplashActivity;

public class CheckViewModel extends BaseObservable {

    private static CheckViewModel mInstance;
    public String reason;
    private String total;

    private CheckViewModel(Context context) {
        Log.d("Force", "EnterViewModel::EnterViewModel()");
    }

    public static CheckViewModel getInstance(Context context) {
        Log.d("Force", "EnterViewModel::getInstance()");
        if (mInstance == null) {
            mInstance = new CheckViewModel(context);
        }
        return mInstance;
    }

    public void addNumber(Context context, String number) {
        if (!(number.equals(",") && total.contains(","))) {
            total = total + number;
        }
        notifyPropertyChanged(BR.total);
    }

    public void Print(Context context) {
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
        Intent intent = new Intent(context, CheckStornoActivity.class);
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
